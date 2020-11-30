from urllib.parse import urlencode
from baidu_config import *
from baidu_utils import *


class Index(object):

    def __init__(self, keywords, start_date, end_date, cookies, area=0):

        # 1. 参数校验
        print("校验参数...")
        assert is_cookies_valid(cookies), "cookies不可用!"  # 检查cookie是否可用
        print("  - cookies检测可以使用!")
        assert is_keywords_valid(keywords), "keywords集合不合规范!"  # 检查keywords集合是否符合规范
        print("  - keywords集合符合规范!")
        print("  - 检查每个keyword是否存在可查询的指数...")
        for i in range(len(keywords)):  # 检查每个keyword是否有效
            for j in range(len(keywords[i])):
                flag = is_keyword_existed(keywords[i][j], cookies)
                print("    + 关键词'{}'有效性: {}".format(keywords[i][j], flag))
                if not flag: keywords[i][j] = str()  # 置空无效关键词
        keywords = list(filter(None, map(lambda x: list(filter(None, x)), keywords)))  # 删除无效关键词与无效关键词组
        print("  - 删除无效关键词后的keywords集合: {}".format(keywords))
        assert days_duration(start_date, end_date) + 1 <= MAX_DATE_LENGTH, "start_date与end_date间隔不在可行范围内: 0~{}".format(
            MAX_DATE_LENGTH)
        print("  - 日期间隔符合规范!")
        assert int(area) == 0 or int(area) in CODE2PROVINCE, "未知的area!"  # 检查area是否在CODE2PROVINCE中, 0为全国
        print("  - area编号可以查询!")
        print("参数校验通过!")

        # 2. 类构造参数
        self.keywords = keywords  # 关键词
        self.start_date = start_date  # 起始日期: yyyy-MM-dd
        self.end_date = end_date  # 终止日期: yyyy-MM-dd
        self.cookies = cookies  # cookie信息
        self.area = area  # 地区编号: 整型数, 默认值全国(0)

        # 3. 类常用参数
        self.index_names = ["search", "news", "feedsearch"]  # 被加密的三种指数: 搜索指数, 媒体指数, 资讯指数

    def get_decrypt_key(self, uniqid):  # 根据uniqid请求获得解密密钥
        response = request_with_cookies(API_BAIDU_INDEX_KEY(uniqid), self.cookies)
        json_response = json.loads(response.text)
        decrypt_key = json_response["data"]
        return decrypt_key

    def get_encrypt_data(self,
                         api_name="search"):  # 三个API接口(搜索指数, 媒体指数, 资讯指数)获取加密响应的方式: api_name取值范围{"search","news","feedsearch"}
        word_list = [[{"name": keyword, "wordType": 1} for keyword in group] for group in self.keywords]
        assert api_name in self.index_names, "Expect param 'api_name' in {} but got {} .".format(self.index_names,
                                                                                                 api_name)
        if api_name == "search":  # 搜索指数相关参数
            field_name = "userIndexes"
            api = API_SEARCH_INDEX
            kwargs = KWARGS_SEARCH_INDEX
        if api_name == "news":  # 媒体指数相关参数
            field_name = "index"
            api = API_NEWS_INDEX
            kwargs = KWARGS_NEWS_INDEX
        if api_name == "feedsearch":  # 资讯指数相关参数
            field_name = "index"
            api = API_FEEDSEARCH_INDEX
            kwargs = KWARGS_FEEDSEARCH_INDEX

        query_string = kwargs.copy()
        query_string["word"] = json.dumps(word_list)
        query_string["startDate"] = self.start_date
        query_string["endDate"] = self.end_date
        query_string["area"] = self.area
        query_string = urlencode(query_string)

        response = request_with_cookies(api(query_string), self.cookies)
        json_response = json.loads(response.text)
        uniqid = json_response["data"]["uniqid"]
        encrypt_data = []
        for data in json_response["data"][field_name]: encrypt_data.append(data)
        return encrypt_data, uniqid

    def get_index(self, index_name="search"):  # 获取三大指数: 搜索指数, 媒体指数, 资讯指数
        index_name = index_name.lower().strip()
        assert index_name in self.index_names, "Expect param 'index_name' in {} but got {} .".format(self.index_names,
                                                                                                     index_name)
        encrypt_data, uniqid = self.get_encrypt_data(api_name=index_name)  # 获取加密数据与密钥请求参数
        decrypt_key = self.get_decrypt_key(uniqid)  # 获取解密密钥

        if index_name == "search":  # 搜索指数区别于媒体指数与资讯指数, 搜索指数分是分SEARCH_MODE的
            for data in encrypt_data:  # 遍历每个数据
                for mode in SEARCH_MODE:  # 遍历所有搜索方式解密: ["all","pc","wise"]
                    data[mode]["data"] = decrypt_index(decrypt_key, data[mode]["data"])
                keyword = str(data["word"])
                for mode in SEARCH_MODE:  # 遍历所有搜索方式格式化: ["all","pc","wise"]
                    _start_date = datetime.strptime(data[mode]["startDate"], "%Y-%m-%d")
                    _end_date = datetime.strptime(data[mode]["endDate"], "%Y-%m-%d")
                    dates = []  # _start_date到_end_date(包头包尾)的日期列表
                    while _start_date <= _end_date:  # 遍历_start_date到_end_date所有日期
                        dates.append(_start_date)
                        _start_date += timedelta(days=1)
                    index_list = data[mode]["data"]
                    if len(index_list) == len(dates):
                        print("指数数据长度与日期长度相同！")
                    else:
                        print(
                            "警告: 指数数据长度与日期长度不一致, 指数一共有{}个, 日期一共有{}个, 可能是因为日期跨度太大!".format(len(index_list), len(dates)))
                    for i in range(len(dates)):  # 遍历所有日期
                        try:
                            index_data = index_list[i]  # 存在可能日期数跟index数匹配不上的情况
                        except IndexError:
                            index_data = ""  # 匹配不上就置空好了
                        output_data = {
                            "keyword": [keyword_info["name"] for keyword_info in json.loads(keyword.replace("'", '"'))],
                            "type": mode,  # 标注是哪种设备的搜索指数
                            "date": dates[i].strftime('%Y-%m-%d'),
                            "index": index_data if index_data else "0",  # 指数信息
                        }
                        yield output_data

        if index_name in ["news", "feedsearch"]:
            for data in encrypt_data:
                data["data"] = decrypt_index(decrypt_key, data["data"])  # 将data字段替换成解密后的结果
                keyword = str(data["key"])
                _start_date = datetime.strptime(data["startDate"], "%Y-%m-%d")
                _end_date = datetime.strptime(data["endDate"], "%Y-%m-%d")
                dates = []  # _start_date到_end_date(包头包尾)的日期列表
                while _start_date <= _end_date:  # 遍历_start_date到_end_date所有日期
                    dates.append(_start_date)
                    _start_date += timedelta(days=1)
                index_list = data["data"]
                if len(index_list) == len(dates):
                    print("指数数据长度与日期长度相同！")
                else:
                    print("警告: 指数数据长度与日期长度不一致, 指数一共有{}个, 日期一共有{}个, 可能是因为日期跨度太大!".format(len(index_list), len(dates)))
                for i in range(len(dates)):  # 遍历所有日期
                    try:
                        index_data = index_list[i]  # 存在可能日期数跟index数匹配不上的情况
                    except IndexError:
                        index_data = ""  # 匹配不上就置空好了
                    output_data = {
                        "keyword": [keyword_info["name"] for keyword_info in json.loads(keyword.replace("'", '"'))],
                        "date": dates[i].strftime('%Y-%m-%d'),
                        "index": index_data if index_data else "0",  # 没有指数对应默认为0
                    }
                    yield output_data


if __name__ == "__main__":
    pass
