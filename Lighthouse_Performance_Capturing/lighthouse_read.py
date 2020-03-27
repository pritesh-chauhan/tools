import time,os
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.firefox.options import Options
import xlrd, openpyxl
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
import shutil,datetime


def update_data(url, cookie_value, loc, page_val,i):
    '''
    options = Options()
    options.set_headless(headless=True)
    browser = webdriver.Chrome()
    browser.get('https://nytimes.com/'+str(page_val))
    browser.add_cookie({'name': 'connect.sid', 'value': 's%3AW6Bzk2jkfYOokvHoYX1usdfRGyZ7EjWy.mxxL8ceq64A3Q1GeHZfZEVF3S8NE11GC%2FSNLkXJmBgc'})
    browser.get('https://nytimes.com/'+str(page_val))
    print browser.execute_script("return (performance.timing.loadEventEnd - performance.timing.navigationStart) / 1000;") #--disable-network-throttling
    '''
    cmd = 'lighthouse --chrome-flags="--disable-gpu --headless --no-sandbox" '+str(url)+'/'+str(page_val)+' --emulated-form-factor=none --throttling-method=provided --quiet --extra-headers=header.json --output html --output-path test.html %1 %2'
    print str(url)+'/'+str(page_val)
    
    os.system(cmd)

    options = Options()
    options.set_headless(headless=True)
    browser = webdriver.Chrome(options=options)
    
    browser.get('file:\\\C:\\Users\\20080346\\Desktop\\lighthouse\\test.html')
    time.sleep(4)
    wb_wr = openpyxl.load_workbook(loc)
    sheet_wr = wb_wr.active
    #print browser.get_log("$('.lh-gauge__percentage').innerHTML")

    new_py_version = browser.find_element_by_xpath('//div[@class="lh-container"]/div[1]/a[1]/div[2]')
    print 'Performance: '+new_py_version.get_attribute('innerHTML')

    if (new_py_version.get_attribute('innerHTML')) == '?':
        sheet_wr.cell(row=i+1, column=3).value = 'NA'
    #sheet_wr['C4']= new_py_version.get_attribute('innerHTML')
    else:
        sheet_wr.cell(row=i+1, column=3).value = int(new_py_version.get_attribute('innerHTML'))
        
    new_py_version = browser.find_element_by_xpath('//div[@class="lh-container"]/div[1]/a[2]/div[2]')
    print 'Accessibility: '+new_py_version.get_attribute('innerHTML')
    #sheet_wr['D4']= new_py_version.get_attribute('innerHTML')

        
    new_py_version = browser.find_element_by_xpath('//div[@class="lh-container"]/div[1]/a[3]/div[2]')
    print 'Best Practices: '+new_py_version.get_attribute('innerHTML')
    if (new_py_version.get_attribute('innerHTML')) == '?':
        sheet_wr.cell(row=i+1, column=4).value = 'NA'
    else:
        sheet_wr.cell(row=i+1, column=4).value = int(new_py_version.get_attribute('innerHTML'))
    new_py_version = browser.find_element_by_xpath('//div[@class="lh-container"]/div[1]/a[4]/div[2]')
    print 'SEO: '+new_py_version.get_attribute('innerHTML')
    #browser.navigate().refresh()
    browser.refresh()
    
    browser.get('https://nytimes.com/'+str(page_val))
    browser.add_cookie({'name': 'connect.sid', 'value': cookie_value})
    browser.refresh()
    #browser.get('https://nytimes.com/'+str(page_val))
    print "Loading Time: "+str(browser.execute_script("return (performance.timing.loadEventEnd - performance.timing.navigationStart) / 1000;"))
    sheet_wr.cell(row=i+1, column=5).value = browser.execute_script("return (performance.timing.loadEventEnd - performance.timing.navigationStart) / 1000;")
    browser.close()
    wb_wr.save(loc)
    os.remove('test.html')
    
    
if __name__ == '__main__':
    
    url = 'https://nytimes.com'
    cookie_value = 's%3AA6K-rGnB4UGEhlSS2sNYOiQGHyl_-VMj.iKt2jrRmhVtdxUfILpuaAJ0NjpsTdlGKiRzHJKdtAGY'
    loc = "C:\\Users\\20080346\\Desktop\\lighthouse\\CLM_UI_Performance.xlsx"
    file_name="C:\\Users\\20080346\\Desktop\\lighthouse\\nytimes_"+str(datetime.datetime.now().day)+"_"+str(datetime.datetime.now().month)+"_"+str(datetime.datetime.now().year)+"_"+str(datetime.datetime.now().hour)+"_"+str(datetime.datetime.now().minute)+".xlsx"
    wb = xlrd.open_workbook(loc)
    sheet = wb.sheet_by_index(0)
    for i in range(4,sheet.nrows):
        #print sheet.cell_value(i,1)
        update_data(url, cookie_value, loc, sheet.cell_value(i,1),i)
    shutil.copy(loc,file_name)
    
