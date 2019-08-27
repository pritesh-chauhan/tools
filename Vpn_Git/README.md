# Title

VPN and GIT Pull Automation

## Authors

Pritesh Chauhan

## Description

This script can be used to connect to the particular secured remote network and then take the latest GIT pull.

### Command for execution

python vpn_git.py git_directory branch_name git_username git_password git_hostname system_username

### Input

Following are the inline input parameters required to the script:

1. git_directory: git directory where the full code has been clonned

2. branch_name: git branch name

3. git_username: git username

4. git_password: git user password

5. git_host_name: git hostname

6. system_username: linux system username

### Output

After, executing the python script on the master server itself which will connect to the vpn and take the latest git checkout and do git pull at the location specified. 

## Environment and setup

This works on linux environment only on which the VPN and GIT setup is done.

## Requirements 

*Python*: 2.7.6
					








