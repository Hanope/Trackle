
# coding: utf-8

# In[7]:

import requests
import re
import pymysql
from newspaper import Article
from bs4 import BeautifulSoup


# In[9]:

# MariaDB Connect
db = pymysql.connect(host='211.253.11.80', port=3306, user='root', passwd='rudeofldk17!', db='trackle', charset='utf8',
                     autocommit=True)
cursor = db.cursor()

# News 크롤링
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows; U; MSIE 9.0; WIndows NT 9.0; ko-KR))',
}

req = requests.get("https://search.naver.com/search.naver?where=news&sm=tab_jum&query=", headers=headers)

html = req.text
soup = BeautifulSoup(html, "lxml")

lst_realtime_src = soup.find('ol', {"class": "lst_realtime_srch _tab_area"})
lis = lst_realtime_src.findAll('li')

for li in lis:
    #   News Url
    key = li.find("span", {"class": "tit"})
    news_page_url = "https://search.naver.com/search.naver?where=news&sm=tab_jum&query=" + key.text
    req = requests.get(news_page_url, headers=headers)
    html = req.text
    soup = BeautifulSoup(html, "lxml")

    news_list = soup.find("ul", {"class": "type01"}).findAll('li')[0]
    source = news_list.find("span", {"class": "_sp_each_source"})
    link = news_list.find("a")["href"]

    req = requests.get(link, headers=headers)
    html = req.text

    a = Article(link, language="ko")
    a.download()
    a.parse()

    title = a.title
    content = a.text
    image = a.top_image
    company = source.text
    keyword = key.text
    date = a.publish_date

    regex = re.compile("[가-힣]{2,5} (기자|인터넷 저널리스트)")
    author = regex.search(a.text)
    if(author is not None):
        author = author.group()

    cursor.execute("INSERT INTO article (title, content, image, company, keyword, date, author) VALUES (%s, %s, %s, %s, %s, %s, %s)", (title, content, image, company, keyword, date, author))
    db.commit()
db.close()


# In[ ]:




# In[ ]:



