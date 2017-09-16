from django.shortcuts import render

# Create your views here.
import urllib.request
import json
import string
import re
import requests
from newspaper import Article
from bs4 import BeautifulSoup
from django.http import JsonResponse

# In[ ]:

# JOONGANG ILBO

from konlpy.tag import Twitter
from collections import Counter

def get_tags(text, ntags=50):
    spliter = Twitter()
    nouns = spliter.nouns(text)
    count = Counter(nouns)
    return_list = []
    for n, c in count.most_common(ntags):
        temp = {'tag': n, 'count': c}
        return_list.append(temp)
    return return_list
 
 
def hyungtaeso(text, ntags = 3):    
    spliter = Twitter()
    nouns = spliter.nouns(text)
    count = Counter(nouns)
    tags = []
    
    for n, c in count.most_common(ntags):
        temp = {'tag': n, 'count': c}
        tags.append(temp)
    
    keywords = ""
    for tag in tags:
        keywords = keywords + tag['tag']
    return keywords


def form_JSON(rs):
    json_list = []
    for row in rs:
        json_dict = {'title' : str(row['title']), 'name' : row['content'], 'image' : row['image'], 'company' : row['company'], 'keyword' : row['keyword'], 'date' : row['date'], 'author' : row['author']}
        json_list.append(json_dict)
    return json_list



def search(request):
    # News 크롤링
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows; U; MSIE 9.0; WIndows NT 9.0; ko-KR))',
    }

    # 넘겨 받을거임
#    param = 'key1' in request.GET and request.GET['key1']
    # 넘겨 받을거임
    param = request.GET.get('keyword','')
    page_size = int(request.GET.get('page_size',''))
    print("param: ", param, " ", page_size)
    url = "http://search.joins.com/JoongangNews?Keyword=" + param + "&SortType=New&SearchCategoryType=JoongangNews&PeriodType=All&ScopeType=All&ImageType=All&JplusType=All&BlogType=All&ImageSearchType=Image&TotalCount=0&StartCount=0&IsChosung=False&IssueCategoryType=All&IsDuplicate=True&Page=1&PageSize=10&IsNeedTotalCount=True"


    req = requests.get(url, headers=headers)

    html = req.text
    soup = BeautifulSoup(html, "lxml")

    articles = []

    list_default = soup.find('ul', {"class" : "list_default"})
    li = [] 
    if list_default is not None:
        li = list_default.findAll('li')
        if li is None:
            return
    
    for i in range(page_size-1, page_size*3):
        article = {}
        try:
            link = li[i].find('a')['href']
        except:
            link = " " 
            continue 
        a = Article(link, language="ko")
        a.download()
        a.parse()
        
        article["title"] = a.title
        article["content"] = a.text
        article["image"] = a.top_image
        article["company"] = '중앙일보'
        article["date"] = a.publish_date
        article['keyword'] = hyungtaeso(a.text)
        regex = re.compile("[가-힣]{2,5} (기자|인터넷 저널리스트)")
        author = regex.search(a.text)
        if author is not None:
            article["author"] = author.group()
            articles.append(article)
    json_list = form_JSON(articles)
    return JsonResponse(json_list, safe=False)

