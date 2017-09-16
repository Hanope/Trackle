
# coding: utf-8

# In[30]:

import urllib.request
import requests
import pymysql
from newspaper import Article
from bs4 import BeautifulSoup
from datetime import datetime


# In[56]:

# MariaDB Connect
db = pymysql.connect(host='211.253.11.80', port=3306, user='root', passwd='rudeofldk17!', db='trackle', charset='utf8',
                     autocommit=True)
cursor = db.cursor()

sql = "SELECT * FROM tracking WHERE flag = false"
cursor.execute(sql)

rows = cursor.fetchall()
article_push = []

for row in rows:
    id = row[0]
    date = row[1]
    keyword = row[2]
    company = row[3]
    user = row[4]
    author = row[5]

    param = keyword + " " + author

    url = "http://search.joins.com/JoongangNews?Keyword=" + param + "&SortType=New&SearchCategoryType=JoongangNews&PeriodType=All&ScopeType=All&ImageType=All&JplusType=All&BlogType=All&ImageSearchType=Image&TotalCount=0&StartCount=0&IsChosung=False&IssueCategoryType=All&IsDuplicate=True&Page=1&PageSize=10&IsNeedTotalCount=True"

    req = requests.get(url)

    html = req.text
    soup = BeautifulSoup(html, "lxml")

    list_default = soup.find('ul', {"class" : "list_default"})
    if list_default is None:
        break
        
    lis = list_default.findAll('li')

    flag = 0
    for li in lis:
        c_date = li.findAll('em')[1].text
        c_date = datetime.strptime(c_date, '%Y.%m.%d %H:%M')
        if date < c_date:
            headline = li.find('strong', {"class" : "headline"})
            _title = headline.find('a')['href']
            article = {"user":user, "link": _title, "title": headline.find('a').text}
            cursor.execute("INSERT INTO tracking (date, keyword, company, user, author) VALUES (%s, %s, %s, %s, %s)", (date, keyword, company, user, author))
            cursor.execute("UPDATE tracking SET flag=1 WHERE id=%s", (id))
            flag = 1
            article_push.append(article)
            break

    if flag == 0:
        url = "http://search.joins.com/JoongangNews?Keyword=" + keyword + "&SortType=New&SearchCategoryType=JoongangNews&PeriodType=All&ScopeType=All&ImageType=All&JplusType=All&BlogType=All&ImageSearchType=Image&TotalCount=0&StartCount=0&IsChosung=False&IssueCategoryType=All&IsDuplicate=True&Page=1&PageSize=10&IsNeedTotalCount=True"
        flag = false
        for li in lis:
            c_date = li.findAll('em')[1].text
            c_date = datetime.strptime(c_date, '%Y.%m.%d %H:%M')
            if date + timedelta(day=2) < c_date:
                headline = li.find('strong', {"class" : "headline"})
                _title = headline.find('a')['href']
                article = {"user":user, "link": _title, "title": headline.find('a').text}
                cursor.execute("INSERT INTO tracking (date, keyword, company, user, author) VALUES (%s, %s, %s, %s, %s)", (date, keyword, company, user, author))
                flag = 1
                article_push.append(article)
                break

    db.commit()
    
db.close()


# In[ ]:



