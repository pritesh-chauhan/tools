import os
import json

from jsondiff import diff

a_file=raw_input("Enter the file name with directory with which comparision needs to be done: \n")
b_file=raw_input("Enter the file name with directory whose comparision needs to be done\n")

with open(a_file, 'r') as json_data:
    Config1 = json.load(json_data)

with open(b_file, 'r') as json_data:
    Config2 = json.load(json_data)

a=[]
b=[]

for key1 in Config1.keys():
    a.append(key1)

for key2 in Config2.keys():
    b.append(key2)

c=list(set(a)-set(b))
print "----Add the below keys in b_file config---- \n"
for i in range(len(c)):
    print '"'+str(c[i])+'":"'+str(Config1[c[i]])+'"'
print "\n"
d=list(set(b)-set(a))
print "----Remove the below keys from b_file config---- \n" 

for i in range(len(d)):
    print '"'+str(d[i])+'":"'+str(Config2[d[i]])+'"'