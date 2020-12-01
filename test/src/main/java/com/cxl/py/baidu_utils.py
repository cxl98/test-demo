# -*- coding: UTF-8 -*-
# @author: caoyang
# @email: lzwcy110@163.com
# 百度指数: 工具函数

import math
import json
import requests

from bs4 import BeautifulSoup
from datetime import datetime, timedelta

from baidu_config import *


def request_with_cookies(url, cookies, timeout=30):  # 重写requests.get方法: 百度指数爬虫中大部分request请求都需要设置cookies后访问
    headers = HEADERS.copy()  # 使用config中默认的伪装头
    headers["Cookie"] = cookies  # 设置cookies
    response = requests.get(url, headers=headers, timeout=timeout)  # 发起请求
    if response.status_code != 200: raise requests.Timeout  # 请求异常: 一般为超时异常
    return response  # 返回响应内容


def is_cookies_valid(cookies):  # 检查cookies是否可用: 通过访问百度首页并检查相关元素是否存在
    response = request_with_cookies(URL_BAIDU, cookies)  # 使用cookie请求百度首页, 获取响应内容
    html = response.text  # 获取响应的页面源代码
    soup = BeautifulSoup(html, "lxml")  # 解析页面源代码
    flag1 = soup.find("a", class_="quit") is not None  # "退出登录"按钮(a标签)是否存在
    flag2 = soup.find("span", class_="user-name") is not None  # 用户名(span标签)是否存在
    flag3 = soup.find("a", attrs={"name": "tj_login"}) is None  # "登录"按钮(a标签)是否存在
    return flag3 and (flag1 or flag2)  # cookies可用判定条件: 登录按钮不存在且用户名与退出登录按钮至少存在一个


def is_keywords_valid(keywords):  # 检查关键词集合是否合法: 满足最大的关键词数量限制, keywords为二维列表
    flag1 = len(keywords) <= MAX_KEYWORD_GROUP_NUMBER  # MAX_KEYWORD_GROUP_NUMBER
    flag2 = max(map(len, keywords)) <= MAX_KEYWORD_GROUP_SIZE  # MAX_KEYWORD_GROUP_SIZE
    return flag1 and flag2


def is_keyword_existed(keyword, cookies):  # 检查需要查询百度指数的关键词是否存在: keyword为单个关键词字符串
    response = request_with_cookies(API_BAIDU_INDEX_EXIST(keyword), cookies)
    json_response = json.loads(response.text)
    if json_response["message"] == "not login":  # message=="not login"表明cookies已经失效
        raise Exception("Cookies has been expired !")
    flag1 = len(json_response["data"]) == 1  # 正常关键词响应结果的data字段里只有一个字段值: result
    flag2 = len(json_response["data"]["result"]) == 0  # 且result字段值应该是空列表
    # print(flag1)
    # print(flag2)
    return flag1 or flag2  # 二者满足其一即认为关键词有效


def days_duration(start_date, end_date, format="%Y-%m-%d"):  # 计算两个日期之间的间隔: 包头不包尾
    _start_date = datetime.strptime(start_date, format)
    _end_date = datetime.strptime(end_date, format)
    return (_end_date - _start_date).days


def split_datetime_block(start_date, end_date, format="%Y-%m-%d", size=360):  # 给定起止日期按照size进行划分成一个个单独的块: 包头包尾
    blocks = []
    _start_date = datetime.strptime(start_date, format)
    _end_date = datetime.strptime(end_date, format)
    while True:
        temp_date = _start_date + timedelta(days=size)
        if temp_date > _end_date:
            blocks.append((_start_date, _end_date))
            break
        blocks.append((_start_date, temp_date))
        _start_date = temp_date + timedelta(days=1)
    return blocks


def decrypt_index(key, data):  # 指数数据解密
    cipher2plain = {}  # 密文明文映射字典
    plain_chars = []  # 解密得到的明文字符
    for i in range(len(key) // 2): cipher2plain[key[i]] = key[len(key) // 2 + i]
    for i in range(len(data)): plain_chars.append(cipher2plain[data[i]])
    index_list = "".join(plain_chars).split(",")  # 所有明文字符拼接后按照逗号分隔
    return index_list

