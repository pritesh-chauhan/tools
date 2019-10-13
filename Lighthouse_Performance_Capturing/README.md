# Title

Performance Evaluation Tool: Lighthouse with Page Load Time 

## Authors

Pritesh Chauhan

## Description

Lighthouse is an open-source, automated tool for improving the quality of web pages. You can run it against any web page, public or requiring authentication. It has audits for performance, accessibility, progressive web apps, and more. Additionally, this tool gets the page loading time of the web page using the chrome-dev tool.


### Input

Following are the input parameters required for the jenkins job:

1. cookies_connect_sid: This is obtained from the browser by logging into the website and getting the value from the *F12->Applications->Cookies->url->connect.sid*. connect.sid stores the cookie value of the login to particular web application the key name may differ from application to application.

2. location: This is the location where the code is present.

3. lighthouse_cmd: This is the location of the lighthouse module on the system. For getting this value open command prompt and type *"where lighthouse"* from the output of the command pass the location that has extension *".cmd"*.

4. url: This is the website whose evaluation needs to be done.

### Output

After, successfull execution of the jenkins job an excel would be created with the date and execution end time at the location specified in the jenkins parameter.

## Environment and setup

This tool can be used in windows as well as linux environment. Presently, the jenkins job created is compatible with only windows system.

For this tool below is the setup required:

1. The node should be registered to the master jenkins using service mechanism.

2. The node should contain a folder which has a python script, excel file, config.json file and chromdriver.exe(chrome for windows v75.*) file.

## Requirements 

*Jenkins*:

	Jenkins can be of any version but for validation I have used v2.150.1.

*Nodejs* and modules required:
		
	nodejs==10.16.0 

	lighthouse==5.1.0 							

*Python* and libraries required:

	Python==2.7.10 

	selenium==3.11.0

	xlrd==1.2.0

	pytest-shutil==1.7.0







