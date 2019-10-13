# Title

Script for capturing errors and exception in jenkins logs

## Authors

Pritesh Chauhan

## Description

This script is used to capture errors and exception in jenkins logs. The output will be a csv file generated in the mantis format.

### Input

Following are the input parameters required for the script:

1. Number of days for which user wants to capture the errors and exceptins 

2. Server name

3. Current deployed version on that server.

### Output

Output file jenkins_errors.csv will be generated

## Environment and setup

This tool should be used in Linux environment only.

1. Copy the file to server at /u02/ 

2. Go to above directory and Run from the command prompt: python python Capture_jenkins_errors.py 

3. Enter the number of days for which you need to capture the logs (Eg: for Current date enter “1” so on) 

4. Enter the server name 

5. Enter the current version on that server 

6. Output file jenkins_errors.csv will be generated. 
 


