import csv,os 
import re
import MySQLdb,json,os
import re

#Below is the location where all the config.json file is placed which has the details of the database
with open(os.environ['PYTHONPATH'].split(":")[0]+'/config.json')as json_data:
		Config = json.load(json_data)

class Count():
	global logger
	def __init__(self, ip= Config['DATABSE_IP'], user = Config['DATABSE_USER'], password = Config['DATABASE_PASSWORD'], database= Config['SCHEDULER_DB_NAME']):
		self.ip=ip
		self.user=user
		self.password=password
		self.database=database
		self.db=MySQLdb.connect(ip, user, password, database)
		self.cursor=self.db.cursor()
	def execute(self, query, operation=None):
		try:
			if operation=="INSERT":
				insertionResult=self.cursor.execute(query)
				return insertionResult
			elif operation=="SELECT":
				self.cursor.execute(query)
				selectionResult=self.cursor.fetchall()
				return selectionResult
			elif operation==None:
				logger.log(1,"Query Operation Not Specified","D")
				return None
		except Exception, e:
			logger.log(1,"Exception In Execution: "+str(e),"E")
			return None

	def testrun_names(self,c,d,e):
		try:
                        #c: column name, d: table name 
			x=0
			Query="SELECT count(*) FROM information_schema.COLUMNS WHERE TABLE_NAME = '"+d+"' and TABLE_SCHEMA = '"+e+"';"
			tablecount=self.execute(Query,"SELECT")
			if str(tablecount[0][0]) == '0': 	
				return 0 #No table means no column also
			else:
				Query="SELECT count(*) FROM information_schema.COLUMNS WHERE COLUMN_NAME = '"+c+"' and TABLE_NAME = '"+d+"' and TABLE_SCHEMA = '"+e+"';"
				columncount=self.execute(Query,"SELECT")
				if str(columncount[0][0]) == '0':	
					return 1 #Table present No column
				else:
					return 2 #Table present column present
			self.db.commit()
			return x			 
		except Exception, e:
			print "Excpetion",e
def main(c,d,e):
	count=Count()
	val=count.testrun_names(c,d,e)
	return val

def check(t):
	outFile = open('update.sql', 'r')
	temp = outFile.read()
	outFile.close()
	if '\t' in t:
		b=t.split('\t')
	else:
		b=t.split(',')
	list = temp.split(';')
	val = "create table "+b[0]
	p=open('test.txt','w+')
	arr=[]
	for i in range(len(list)):
		j=0
		cc=1
		while((val in list[i+j]) and j < len(list)):
			j=j+1
		if str(j) > '0':
			arr.append(str(i))
	test_val=[]
	
	while cc < len(arr):
		test_val = list[int(arr[cc])].split()
		for k in range(len(test_val)):		
			if k == 0:
				test_val[k] = 'alter' 
			elif k == 3:
				test_val[k] = 'add column'
			elif k == (len(test_val)-1):
				if '))' in test_val[k]:
					test_val[k] = re.sub(r'\)$',"",test_val[k])				
		query_final = []
		for k in range(len(test_val)):
			temp = test_val[k]+" "
			query_final.append(temp)
		if ') ' == query_final[len(test_val)-1]:
			print query_final[len(test_val)-1]
			query_final[len(test_val)-1] = query_final[len(test_val)-1].lstrip(') ')
		elif (')' in query_final[len(test_val)-1]) and not('(' in query_final[len(test_val)-1]):
			query_final[len(test_val)-1] = query_final[len(test_val)-1].rstrip(') ')
			if "'" in query_final[len(test_val)-2]:
				query_final[len(test_val)-2] = query_final[len(test_val)-2].rstrip(' ')
		query_final.append(';')		
		query_final=(''.join(query_final))
		list[int(arr[cc])] = query_final
		cc=cc+1	
	p.write(str(list))
	outFile = open('update.sql', 'w')
	for i in range(len(list)):
		if str(list[i]) != '\n' :
			tt=str(list[i])+";"
			tt=tt.lstrip('\n')
			tt=tt.rstrip('\n')
			outFile.write(tt)
			outFile.write('\n')
	outFile.close()

if __name__=='__main__':
	acsv=str(raw_input("Enter main csv with which comparision needs to be done:\n"))
	bcsv=str(raw_input("Enter csv whose comparision needs to be done:\n"))
	database=str(raw_input("Enter the name of database that is being compared: \n"))
	select_database="use "+database+";"
	t1 = open(acsv, 'r') #main comparision csv
	t2 = open(bcsv, 'r') #csv which is getting compared
	fileone = t1.readlines()
	filetwo = t2.readlines()
	t3 = open('1.txt', 'w')
	t4 = open('2.txt', 'w')
	tt=set(fileone)
	tt1=set(filetwo)
	t3.write(str(tt))
	t4.write(str(tt1))
	test = set(fileone) - set(filetwo)
	test1 = set(filetwo) - set(fileone)
	t1.close()
	t2.close()
	outFile = open('update.csv', 'w')
	for line in test:
		outFile.write(line)
	outFile.close()
	outFile = open('update.csv', 'r')	# missing at comparision's end
	test_up = outFile.readlines()
	sqlFile = open('update.sql', 'w')
	i=0
	a=outFile.readlines()
	sqlFile.write(select_database)
	sqlFile.write('\n')
	#Below will give the sql queries that are missing at b end after comparing with a csv file
	for line in test_up:
		if '\t' in line:
			b=line.split('\t')
		else:
			b=line.split(',')
		v=main(b[1],b[0],database) # b[0] table b[1] column 
		#print v
		if (len(b)==6): # for len 6
			if (v == 0):
				if (b[4]=='\\N' and b[5]=='\n'): 
					test="create table "+b[0]+" ( "+b[1]+" "+b[3]+");" 
				elif (b[5] == '\n'):
					test="create table "+b[0]+" ( "+b[1]+" "+b[3]+" default '"+b[4]+"');"
				elif (b[5] == '\\N\n'):
					test="create table "+b[0]+" ( "+b[1]+" "+b[3]+","+b[4]+");"
				else:
					test="create table "+b[0]+" ( "+b[1]+" "+b[3]+","+b[4]+" default '"+b[5]+"');"
			elif (v == 1):
				if (b[4]=='\\N' and b[5]=='\n'): 
					test="alter table "+b[0]+" add column "+b[1]+" "+b[3]+";"
				elif (b[5] == '\n'):
					test="alter table "+b[0]+" add column "+b[1]+" "+b[3]+" default '"+b[4]+"';"
				elif (b[5] == '\\N\n'):
					test="alter table "+b[0]+" add column "+b[1]+" "+b[3]+","+b[4]+";"
				else:
					test="alter table "+b[0]+" add column "+b[1]+" "+b[3]+","+b[4]+" default '"+b[5]+"';"
			elif (v == 2):
				if (b[4]=='\\N' and b[5]=='\n'): 
					test="alter table "+b[0]+" modify column "+b[1]+" "+b[3]+";"
				elif (b[5] == '\n'):
					test="alter table "+b[0]+" modify column "+b[1]+" "+b[3]+" default '"+b[4]+"';"
				elif (b[5] == '\\N\n'):
					test="alter table "+b[0]+" modify column "+b[1]+" "+b[3]+","+b[4]+";"
				else:
					test="alter table "+b[0]+" modify column "+b[1]+" "+b[3]+","+b[4]+" default '"+b[5]+"';"
		elif (len(b)==7): # for len 7
			if (v == 0):
				if (b[4]=='\\N' and b[5]=='\n') or b[4]=='\\N' : 
					test="create table "+b[0]+" ( "+b[1]+" "+b[3]+");"
				elif (b[5]=='\\N' and b[6]=='\n') : 
					test="create table "+b[0]+" ( "+b[1]+" "+b[3]+","+b[4]+");"
				elif (b[6] == '\\N\n' or b[6]=='\n') and b[5]!='':
					test="create table "+b[0]+" ( "+b[1]+" "+b[3]+","+b[4]+" default '"+b[5]+"');"
				elif (b[5]=='\n' or b[5]==''):
					test="create table "+b[0]+" ( "+b[1]+" "+b[3]+" default '"+b[4]+"');"
			elif (v == 1):
				if (b[4]=='\\N' and b[5]=='\n') or b[4]=='\\N' : 
					test="alter table "+b[0]+" add column "+b[1]+" "+b[3]+";"
				elif (b[5]=='\\N' and b[6]=='\n') : 
					test="alter table "+b[0]+" add column "+b[1]+" "+b[3]+","+b[4]+";"
				elif (b[6] == '\\N\n' or b[6]=='\n') and b[5]!='':
					test="alter table "+b[0]+" add column "+b[1]+" "+b[3]+","+b[4]+" default '"+b[5]+"';"
				elif (b[5]=='\n' or b[5]==''):
					test="alter table "+b[0]+" add column "+b[1]+" "+b[3]+" default '"+b[4]+"';"
			elif (v == 2):
				if (b[4]=='\\N' and b[5]=='\n') or b[4]=='\\N' : 
					test="alter table "+b[0]+" modify column "+b[1]+" "+b[3]+";"
				elif (b[5]=='\\N' and b[6]=='\n') : 
					test="alter table "+b[0]+" modify column "+b[1]+" "+b[3]+","+b[4]+";"
				elif (b[6] == '\\N\n' or b[6]=='\n') and b[5]!='':
					test="alter table "+b[0]+" modify column "+b[1]+" "+b[3]+","+b[4]+" default '"+b[5]+"';"
				elif (b[5]=='\n' or b[5]==''):
					test="alter table "+b[0]+" modify column "+b[1]+" "+b[3]+" default '"+b[4]+"';"
		else:	# for len 5
			if (v == 0):
				if (b[4]=='\n' or b[4]=='\\N' or b[4]=='\\N\n'): 
					test="create table "+b[0]+" ( "+b[1]+" "+b[3]+");" 
				elif (b[4]!='\\N\n' or b[4]!='\n' or b[4]!=''):
					test="create table "+b[0]+" ( "+b[1]+" "+b[3]+" default '"+b[4]+"');" 
				else:
					test="create table "+b[0]+" ( "+b[1]+" "+b[3]+","+b[4]+" default '"+b[5]+"');" 
			elif (v == 1):
				if (b[4]=='\n' or b[4]=='\\N' or b[4]=='\\N\n'): 
					test="alter table "+b[0]+" add column "+b[1]+" "+b[3]+";" 
				#elif (b[5]!='\\N\n' or b[5]=='' or b[4]!='\n' or b[4]!=''):
				elif (b[4]!='\\N\n' or b[4]!='\n' or b[4]!=''):
					test="alter table "+b[0]+" add column "+b[1]+" "+b[3]+" default '"+b[4]+"';"
				else:
					test="alter table "+b[0]+" add column "+b[1]+" "+b[3]+","+b[4]+" default '"+b[5]+"';"
			elif (v == 2):
				if (b[4]=='\n' or b[4]=='\\N' or b[4]=='\\N\n'): 
					test="alter table "+b[0]+" modify column "+b[1]+" "+b[3]+";" 
				#elif (b[5]!='\\N\n' or b[4]=='' or b[4]!='\n' or b[4]!=''):
				elif (b[4]!='\\N\n' or b[4]!='\n' or b[4]!=''):
					test="alter table "+b[0]+" modify column "+b[1]+" "+b[3]+" default '"+b[4]+"';"
				else:
					test="alter table "+b[0]+" modify column "+b[1]+" "+b[3]+","+b[4]+" default '"+b[5]+"';"
		sqlFile.write(test)
		sqlFile.write('\n')
	sqlFile.close()
	outFile.close()
	for line1 in test_up:
		check(line1)
	os.remove('1.txt')
	os.remove('2.txt')
	os.remove('test.txt')
	



