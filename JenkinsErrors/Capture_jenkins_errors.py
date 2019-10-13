import os, sys, csv, json
from subprocess import call
import datetime 
print "Enter number of days to capture error"
no_of_days = int(raw_input())
print "Enter server name"
server_name = raw_input()
date_str=[]
for i in range(no_of_days):
    yest1 = datetime.datetime.now() - datetime.timedelta(i)
    month= (yest1.strftime("%B"))
    month= month[:3]
    day= (yest1.strftime("%d"))
    day0= day[0]
    if day0== '0':
    	day=" "+day[1]
    monthDate= month+" "+day
    date_str.append(monthDate)

with open('/u02/jenkins_errors.csv', 'w+') as outfile:
    mywriter = csv.writer(outfile)
    mywriter.writerow(["Report of last "+str(no_of_days)+" days"])
    mywriter.writerow(['Category','Reproducibility','Severity','Priority','Product Version','Summary','Description','Request Type'])

#os.chdir('/home/pcadmin/jenkins/jobs')
os.chdir('/var/lib/jenkins/jobs')
call('ls -l > /u02/data.txt',shell=True)
with open('/u02/data.txt', 'r') as fin:
    data = fin.read().splitlines(True)
with open('/u02/data.txt', 'w') as fout:
    fout.writelines(data[1:])
a=open("/u02/data.txt","r") 
line=a.readlines()
b=[]
c=[]
d=[]
templist=[]
jobnames=[]
lists=[]
listoflists=[]
error_file=open("/u02/err_file.txt","a+") 
err_f=error_file.readlines()
for i in line:
	b.append(i)
for i in range(len(b)):
	b[i]=b[i].rstrip('\n')
	x=b[i].find('.')
	if x == -1:
		for j in range(len(date_str)):
			y=b[i].find(date_str[j])
			if y != -1:
				dummy= b[i].split()
				file_name= dummy[8]
				d.append(file_name)

for i in range(len(d)):
	val=os.path.isfile(str(d[i])+"/lastSuccessful/log")
	vale=os.path.isfile(str(d[i])+"/builds/1/log")
	if val == True:
		temp1=str(d[i])+"/lastSuccessful/log"
		log_file=open(temp1,"r")
		log_file_line=log_file.readlines()
		for j in log_file_line:
			if 'ERROR_' in j:
				val_er1=j.find('ERROR_')
				val = "status.txt"
				val_code = "ERROR_CODE"
				if not val in j:
					if not val_code in j:
						header =d[i]+"\n"					
						j=j.strip('\n')
						j=j[val_er1:]
						templist.append(j)
						error_file.write(j+"\t"+d[i])
						error_file.write('\n')
			if '[Errno' in j:
				val_er2=j.find('[Errno')
				val = "status.txt"
				if not val in j:
					header =d[i]+"\n"					
					j=j.strip('\n')
					j=j[val_er2:]
					templist.append(j)
					error_file.write(j+"\t"+d[i])
					error_file.write('\n')	
					
			if 'Exception' in j:
				res = j.split()
				for k in range(len(res)):
					if 'Exception' in res[k]:					
					#val_er2=j.find('Exception')
					#val = "status.txt"
					#if not val in j:
						header =d[i]+"\n"					
						j=j.strip('\n')
						#j=j[val_er2:]
						templist.append(res[k])
						error_file.write(res[k]+"\t"+d[i])
						error_file.write('\n')
	if vale == True:
		temp1=str(d[i])+"/builds/1/log"
		log_file=open(temp1,"r")
		log_file_line=log_file.readlines()
		for j in log_file_line:
			if 'Exception' in j:
				res = j.split()
				for k in range(len(res)):
					if 'Exception' in res[k]:					
					#val_er2=j.find('Exception')
					#val = "status.txt"
					#if not val in j:
						header =d[i]+"\n"					
						j=j.strip('\n')
						#j=j[val_er2:]
						templist.append(res[k])
						error_file.write(res[k]+"\t"+d[i])
						error_file.write('\n')
error_file.close()
tempset=set(templist)
templist=list(tempset)		
		
file_size=os.stat("/u02/err_file.txt").st_size == 0
if file_size == False:
	lines_seen = set()  # holds lines already seen
	outfile1 = open('/u02/jenkins_errors.txt', "w+")
	infile1 = open('/u02/err_file.txt', "r+")
	for line in infile1:
		if line not in lines_seen:  # not a duplicate
			outfile1.write(line)
			lines_seen.add(line)	
	outfile1.close()
	
for ax in range(len(templist)):
	errors1 = open('/u02/jenkins_errors.txt', "r+")
	for aa in errors1:
		if templist[ax] in aa:
			wordlist=aa.split()
			jobnames.append(wordlist[-1])
	occurance = str(len(jobnames))
	
	with open('/u02/jenkins_errors.csv', 'a+') as outfile:
		mywriter = csv.writer(outfile)
		mywriter.writerow(['General','N/A','major','normal','7.2.1',server_name+": "+templist[ax]+"  Occurence: "+occurance,jobnames,"Defect"])
	
	del jobnames[:]
	errors1.close()
a.close()
log_file.close()

os.remove("/u02/jenkins_errors.txt")
os.remove("/u02/err_file.txt")
os.remove("/u02/data.txt")

