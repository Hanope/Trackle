import urllib.request
import requests
import pymysql
from newspaper import Article
from bs4 import BeautifulSoup
from datetime import datetime
from django.http import JsonResponse




def form_JSON(rs):
    json_list = []
    for row in rs:
        json_dict = {'date' : str(row['date']), 'link' : row['content'], 'title' : row['title']}
        json_list.append(json_dict)
    return json_list


def track(request):
    db = pymysql.connect(host='211.253.11.80', port=3306, user='root', passwd='rudeofldk17!', db='trackle', charset='utf8',
                         autocommit=True)
    cursor = db.cursor()
    
    param = request.GET.get('keyword','')
    sql = "SELECT * FROM tracking WHERE keyword =" + param
    cursor.execute(sql)

    rows = cursor.fetchall()

    json_list = form_JSON(rows)
        
    db.close()
    return JsonResponse(json_list, safe =False)
    
