
# coding: utf-8

# In[154]:

import urllib.request
import json
import string
import re
import requests
from newspaper import Article
from bs4 import BeautifulSoup


# In[ ]:

# JOONGANG ILBO

# News 크롤링
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows; U; MSIE 9.0; WIndows NT 9.0; ko-KR))',
}

# 넘겨 받을거임
param = "아이폰"
# 넘겨 받을거임
page_size = 1
url = "http://search.joins.com/JoongangNews?Keyword=" + param + "&SortType=New&SearchCategoryType=JoongangNews&PeriodType=All&ScopeType=All&ImageType=All&JplusType=All&BlogType=All&ImageSearchType=Image&TotalCount=0&StartCount=0&IsChosung=False&IssueCategoryType=All&IsDuplicate=True&Page=1&PageSize=10&IsNeedTotalCount=True"


req = requests.get(url, headers=headers)

html = req.text
soup = BeautifulSoup(html, "lxml")

articles = []

list_default = soup.find('ul', {"class" : "list_default"})
li = list_default.findAll('li')

for i in range(page_size-1, page_size*3):
    article = {}
    link = li[i].find('a')['href']
    
    a = Article(link, language="ko")
    a.download()
    a.parse()
    
    article["title"] = a.title
    article["content"] = a.text
    article["image"] = a.top_image
    article["company"] = '중앙일보'
    article["date"] = a.publish_date
    
    regex = re.compile("[가-힣]{2,5} (기자|인터넷 저널리스트)")
    author = regex.search(a.text)
    article["author"] = author.group()
    articles.append(article)
    
    print(article)


# In[ ]:



