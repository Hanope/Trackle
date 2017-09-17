from django.shortcuts import render


# Create your views here.
import requests
import json
import pymysql.cursors
from newspaper import Article
from bs4 import BeautifulSoup
from django.http import HttpResponse
from django.http import JsonResponse


'''
필요한거
	1. JSON전환
.title
    content = a.text
    image = a.top_image
    company = source.text
    keyword = key.text
    date = a.publish_date
'''
def remove_newline(param):   
    return_str = ""
    for char in param:
        if char != '\n':
            return_str = return_str + char    
    return return_str


def form_JSON(rs):
    json_list = []
    for row in rs:
        temp_str = row['content']
        temp_str = remove_newline(temp_str)
        json_dict = {'title' : str(row['title']), 'name' : temp_str, 'image' : row['image'], 'company' : row['company'], 'keyword' : row['keyword'], 'date' : row['date'], 'author' : row['author']}
        json_list.append(json_dict)
    return json_list


def feed(request):
    # MariaDB Connect
    db = pymysql.connect(host='211.253.11.80', port=3306, user='root', passwd='rudeofldk17!', db='trackle', charset='utf8',cursorclass=pymysql.cursors.SSDictCursor)
    
    json_list = []

    with db.cursor() as cursor:
        sql = "SELECT * FROM article"
        cursor.execute(sql)
        rs = cursor.fetchall()
        
        json_list = form_JSON(rs) 

     
    dic = {'articles' : json_list}
    return JsonResponse(dic)
#    return JsonResponse(json_list,safe=False)

