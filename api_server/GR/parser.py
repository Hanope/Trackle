# parser.py
import requests
from bs4 import BeautifulSoup
from datetime import datetime

import json
import os


# Time format
f = '%Y.%m.%d %H:%M'


# Start time 
h_s = '2017.09.15 01:13'
h_datetime = datetime.strptime(h_s,f)

# author name
h_name = '중앙일보(집배신)'


req = requests.get('http://search.joins.com/totalnews?StartSearchDate=&EndSearchDate=&Keyword=%EB%B6%80%EC%82%B0+%EC%97%AC%EC%A4%91%EC%83%9D+%ED%8F%AD%ED%96%89&SortType=New&SearchCategoryType=TotalNews&PeriodType=All&ScopeType=All&ServiceCode=&SourceGroupType=Joongang&ReporterCode=&ImageType=All&JplusType=All&BlogType=All&ImageSearchType=Image&MatchKeyword=&IncludeKeyword=&ExcluedeKeyword=')
html = req.text
soup = BeautifulSoup(html, 'html.parser')

# div > div > ul > li > div > span > em
# div > div > ul > li > div ,this selects single article section 
# div > div > ul > li > div > strong > a ,link

my_titles = soup.select("div > div > ul > li > div > span > em")
my_links = soup.select("div > div > ul > li > div > strong > a")
# my_titles는 list 객체


author_names = []
pub_dates = []
news_links = []

for itr in range(len(my_titles)):
    if itr % 2 == 0:
        author_names.append(my_titles[itr].text)
    else:
        l_datetime = datetime.strptime(my_titles[itr].text,f)
        pub_dates.append(l_datetime)

for itr in range(len(my_titles)):
    if itr % 2 != 0:
        pub_dates.append(my_titles[itr].text)

for link in my_links:
    news_links.append(link.get('href'))

article_texts = []

# crawl article text from links
for news in news_links:
    print("news link size: ",len(news_links))
    req = requests.get(news)
    html = req.text
    soup = BeautifulSoup(html, 'html.parser')
    texts = soup.find(id = 'article_body')
    temp = ''
    print("news: ",news)
    for i in range(len(texts)): 
       print("texts size: ",len(texts))  
   
