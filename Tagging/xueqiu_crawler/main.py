import requests
import time
from fake_useragent import UserAgent
import re
import pandas as pd
import sys

symbol = sys.argv[1]
page = sys.argv[2]

ua = UserAgent() #在工作中进行爬虫时，经常会需要提供User-Agent（请求头），如果不提供User-Agent，会导致爬虫在请求网页时，请求失败，所以需要大量User-Agent
headers = {'user-agent':ua.random} # 最好的，通过真实世界的浏览器使用统计随机：ua.random
data_list=[]

def get_comment(data): #Python 使用def 开始函数定义，紧接着是函数名，括号内部为函数的参数，内部为函数的 具体功能实现代码，如果想要函数有返回值, 在 expressions 中的逻辑代码中用return 返回。
    #data = json.load()
    pinglun_len = len(data["list"])
    i = 0
    print(pinglun_len)
    #data_list.clear()

    while i < pinglun_len: #对每一条数据匹配评论部分
        temp_data = data["list"][i]
        # url = base_url+temp_data["target"]
        pre = re.compile('>(.*?)<') #compile 函数用于编译正则表达式，生成一个 Pattern 对象，匹配>中间的内容<
        text = ''.join(pre.findall(temp_data['text'])) #compile()与findall()一起使用，返回一个列表。

        # text = temp_data['text']
        #timeBefore = temp_data['timeBefore']
        if len(text) > 0:
            text = text.replace('&nbsp;', '')#去除&nbsp
        if len(text) != 0:
            data_list.append([text])
        #print(text , timeBefore)
        i += 1

url='https://xueqiu.com/query/v1/symbol/search/status'

#这里取决于你要爬多少页，目前设定为9
for i in range(1,int(page)):
    params={
        "u": 381615786351364,
        "uuid": 1371340614335082496,
        "count": 10,
        "comment": 0,
        "symbol": symbol, #这里是股票代码，决定爬哪只股票下面的评论
        "hl": 0,
        "source": "user",
        "sort": "time",
        "page": i, #是这里做循环，爬第i页的评论
        "q": "",
        "type": 12,
        "last_id":174442514,
        "session_token":"null",
        "access_token": "a4b3e3e158cfe9745b677915691ecd794b4bf2f9"
    }
    flag = 0
    #访问的次数
    times = 0
    while flag == 0:
        response = requests.get(url,  params=params ,headers=headers) #用get方式爬取数据
        temp = response.json() #response.json()的作用就是将API页面的json转化为字典
        if 'code' in temp:
            time.sleep(0.5)
        else:
            flag = 1
        times += 1
        print(times)
    get_comment(temp)

data_csv = pd.DataFrame(data_list)
data_csv.to_csv("./data_comments.csv", encoding="gbk", index=False)
