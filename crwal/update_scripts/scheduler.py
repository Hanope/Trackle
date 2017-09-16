import schedule
import os
import time


while True:
    os.system('python tracking.py')
    os.system('python main_news.py')
    print('database updated')
    time.sleep(100) # wait one minute
