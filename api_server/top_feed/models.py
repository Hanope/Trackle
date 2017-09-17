from django.db import models

# Create your models here.

class News(models.Model):
    title =  models.TextField()
    content = models.TextField()
    image = models.TextField()
    company = models.TextField()
    author = models.TextField()
    keyword = models.TextField()
    date = models.TextField()

