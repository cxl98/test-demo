from baidu_index import Index
from baidu_utils import *

cookies = 'xxx'
keywords = [["天津之眼"]]
start_date = "2020-9-24"
end_date = "2020-10-25"
area = 924

index = Index(
    keywords=keywords,
    start_date=start_date,
    end_date=end_date,
    cookies=cookies,
    area=0,
)
fw = open("1.json", "w+", encoding='utf-8')
fw.write("[")
for data in index.get_index(index_name="search"):
    # print("搜索指数", end="")
    # xx = json.dumps(data)
    # print(json.loads(xx))
    fw.writelines(json.dumps(data)+",\n")
    # print(data)
fw.write("]")
fw.close()
input("暂停...")

for data in index.get_index(index_name="news"):
    print(""+data)

input("暂停...")

for data in index.get_index(index_name="feedsearch"):
    print("资讯指数", end="")
    print(data)
