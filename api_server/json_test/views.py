from django.shortcuts import render

# Create your views here.

from django.http import JsonResponse

def json_return(request):
    return JsonResponse({'foo':'bar'})
