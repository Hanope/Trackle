from django.shortcuts import render

# Create your views here

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
        json_dict = {'date' : row[1], 'link' : row[7], 'title' : row[8]}
        json_list.append(json_dict)
    return json_list


def track(request):
    db = pymysql.connect(host='211.253.11.80', port=3306, user='root', passwd='rudeofldk17!', db='trackle', charset='utf8',
                         autocommit=True)
    cursor = db.cursor()
    
    param = request.GET.get('keyword','')
    print("parameter: ",param)
    sql = "SELECT * FROM tracking WHERE keyword = '" + param + "' ORDER BY date DESC"
    cursor.execute(sql)

    rows = cursor.fetchall()

    json_list = form_JSON(rows)
    dic = {'histories' :json_list}
    db.close()
    return JsonResponse(dic)
 
