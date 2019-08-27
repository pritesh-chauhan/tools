import subprocess, os, sys 
#python vpn_git.py git_directory branch_name git_username git_password git_hostname system_username

cmd1 = '/opt/cisco/anyconnect/bin/vpn disconnect'
aa = subprocess.Popen(cmd1,executable="/bin/bash",stdout=subprocess.PIPE,stderr=subprocess.PIPE,shell=True).communicate()
actual_string = aa[0].split('\n')[5]
actual_string=actual_string.split()

git_user_pass = sys.argv[3]+"\n"+sys.argv[4]+"\ny"

cmd = "printf '"+sys.argv[3]+"\n"+sys.argv[4]+"\ny' | /opt/cisco/anyconnect/bin/vpn connect "+sys.argv[5]
a = subprocess.Popen(cmd,executable="/bin/bash",stdout=subprocess.PIPE,stderr=subprocess.PIPE,shell=True).communicate()

cmd1 = '/opt/cisco/anyconnect/bin/vpn stats'
aa = subprocess.Popen(cmd1,executable="/bin/bash",stdout=subprocess.PIPE,stderr=subprocess.PIPE,shell=True).communicate()

actual_string = aa[0].split('\n')[5]
actual_string=actual_string.split()

if actual_string[2] == 'Connected':
	os.chdir(sys.argv[1])
	cmd1 = 'sudo -Hu '+sys.argv[6]+' git checkout -f '+sys.argv[2]
	aa = subprocess.Popen(cmd1,executable="/bin/bash",stdout=subprocess.PIPE,stderr=subprocess.PIPE,shell=True).communicate()
	cmd1 = 'sudo -Hu '+sys.argv[6]+' git pull'
	aa1 = subprocess.Popen(cmd1,executable="/bin/bash",stdout=subprocess.PIPE,stderr=subprocess.PIPE,shell=True).communicate()

	cmd1 = '/opt/cisco/anyconnect/bin/vpn disconnect'
	aa = subprocess.Popen(cmd1,executable="/bin/bash",stdout=subprocess.PIPE,stderr=subprocess.PIPE,shell=True).communicate()
	aa = subprocess.Popen(cmd1,executable="/bin/bash",stdout=subprocess.PIPE,stderr=subprocess.PIPE,shell=True).communicate()
	actual_string = aa[0].split('\n')[5]
	actual_string=actual_string.split()

	aa2=str(aa1[0]).split('\n')
	print aa2[0]
	if aa2[0] == "Already up-to-date.":
		sys.exit("!!!!!!!!!!!!There are no new commits made!!!!!!!!!!!!")
	else:
		os.environ['git_logs']="!!!!!!!!!!!!Git Pull Logs:!!!!!!!!!!!!\n"+str(aa1)
		new=str(aa2[0]).split()
		new=str(new[1]).split("..")
		sys.exit(str(new))
else:
	print '!!!!!!!!!!Unable to connect!!!!!!!!!!!'
	sys.exit(1)




