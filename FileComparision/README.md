# Title

Files Comparision

## Authors

Pritesh Chauhan

## Description

This script can be used for comparing database schema, json and js files.
This scripts prompts the missing keys and for database schema it generates the a *.sql* file with the queries that needs to be executed for making the schema similar.


#### Execution Command

*python <location of file>/<file name>.py*

### Input

User has to keep all the files at a particular location or needs to pass the filename along with the location.

Following are the input parameters required for the script execution:

1. A setup filename and B setup filename.

### Output

After, passing the filename the delta will be generated if there is anything missing or have additional data at b setup than that will be prompted to the user which needs to be updated in the repective file.

For database compare it will compare both the csv and than generate a new csv which will have delta in it from which a *.sql* file will be created containing the sql queries.

## Requirements 
	
*Python* and libraries required:

	Python==2.7.6
	
	demjson==2.2.4
	
	jsondiff==1.2.0
	
## Environment and setup

This script runs on platform independent.

For js and json just filename is required.
	
Following for database compare are the steps that needs to be followed for using the script:

	Precondition:

		The script should be executed on the server whose dump is being compared.

	For example:

		We need to compare the dump of the demployment server and local server; so, this script must be executed on the deployment server system.

	How the script works?

		1. It takes database csv files as input i.e. of the servers of which the comparisons are to be done.
		
		Command to get the database schema in csv file:

		select TABLE_NAME, COLUMN_NAME, IS_NULLABLE, COLUMN_TYPE, COLUMN_DEFAULT from INFORMATION_SCHEMA.COLUMNS where TABLE_SCHEMA="db_name" INTO OUTFILE "file_name.csv";

		Where TABLE_SCHEMA is the database name
		
		if you face any issue in creating the csv please follow the below steps:
			1.  a. sudo service mysql stop
			    b. Now edit */etc/mysql/my.cnf*
					[mysqld_safe]
					[mysqld]
					secure_file_priv = ""
				c. sudo service mysql start
				
				OR
				
			2. sudo mysqld_safe --secure_file_priv=""
			   sudo service mysql restart

		2. After the execution of the Step 1 we need to get the csv file which is generated in ~/mysql folder.
			Command to get the csv file from the mysql folder:

			“Sudo cp /var/lib/mysql/file_name.csv destination-folder”

		3. Now after taking the csv, place the python script along with another csv with which comparison needs to be done
		4. Run the python script on the server which is being compared
		5. The script will ask for 2 inputs from user
			a. The A csv (the file with which the comparison will be done)
			b. The B csv (the file which will needs to be compared)
		6. Also, it will prompt for the database name input i.e. the TABLE_SCHEMA of Step 1
		7. After, the script is executed one “update.csv” and “update.sql” file will be created
			a. Update.csv: This file contains the difference of the 2 csv’s
			b. Update.sql: This file contains all the mismatch of column/ table of which the sql queries are generated which needs to be executed on the server
	



	







