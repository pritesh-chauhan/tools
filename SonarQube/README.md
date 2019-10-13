# Title

SonarQube Automation

## Authors

Pritesh Chauhan

## Description

SonarQube is an open-source platform developed by SonarSource for continuous inspection of code quality to perform automatic reviews with static analysis of code to detect bugs, code smells, and security vulnerabilities on different programming languages. SonarQube offers reports on duplicated code, coding standards, unit tests, code coverage, code complexity, comments, bugs, and security vulnerabilities.

## Execution Command

python jenkins_git.py git_directory_path git_branch_name git_configured_user_name git_configured_user_password git_host_name git_system_username

### Input

Following are the input parameters required for the jenkins job:

1. data_main: server ip on which the sonar job needs to be run

2. jenkins_username: jenkins username

3. jenkins_password: jenkins password

4. jenkins_job_name: jenkins job name i.e. Sonar_master

5. jenkins_job_token: jenkins token value

6. ProjectName_py: sonar python project name 

7. ProjectName_js_web: sonar javascript web project name 

8. ProjectName_js_views: sonar javascript views project name 

9. language_py: default value should be py

10. language_js: default value should be js

11. git_directory: git directory where the full code has been clonned

12. branch_name: git branch name

13. git_username: git username

14. git_password: git user password

15. git_host_name: git hostname

16. system_username: linux system username

### Output

After, building the jenkins main job it trigger one python script on the master itself which will take the checkout and do git pull at the location specified. Now, the jenkins will trigger three different jobs on *jenkins_job_name* which is specified by the user. After all the jobs are triggered successfully report will be sent on the email.

## Environment and setup

This works on linux environment only on which the SonarQube setup is done.

## Requirements 

*Jenkins*:

	Jenkins can be of any version but for validation I have used v2.150.1.						








