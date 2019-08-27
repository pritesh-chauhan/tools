import os
import demjson

def jsobj_pydict(val):
    val=val.read()
    obj=val[val.find('{'):val.rfind('}')+1]
    print obj
    py_obj=demjson.decode(val)

if __name__ == "__main__":
    a_file=raw_input("Enter File Name that needs to be compared: \n")
    data_file_a_file=open(a_file,'r')
    val=data_file_a_file.read()
    obj=val[val.find('{'):val.rfind('}')+1]
    py_obj=demjson.decode(obj)
    data_file_b_file=py_obj.keys()
    b_file=raw_input("Enter File Name with which comparision needs to be done: \n")
    data_file_a_file=open(b_file,'r')
    val=data_file_a_file.read()
    obj=val[val.find('{'):val.rfind('}')+1]
    py_obj=demjson.decode(obj)
    data_file_a_file=py_obj.keys()
    missing_val = list(set(data_file_a_file)-set(data_file_b_file))

    if len(missing_val) > 0:
    	print "\nFiles are not equal. Kindly add following keys:\n"
        for i in range(len(missing_val)):
            print missing_val[i]
    else:
	print "Files are Equal"
    
        
