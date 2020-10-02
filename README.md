 The project is intended to execute the following tests on https://jira.hillel.it/secure/Dashboard.jspa:
 1) login successful and unsuccessful
 2) create issue
 3) dismiss and accept alert when cancelling create issue
 
 <h2>Before you start</h2>
 
1. Required soft:

 * Java JDK 8 
 * Git 
 * Maven
 * Intellij Idea IDE.
 
2. Import the project: 

* Navigate to File => New => Project from Version Control... in Intellij Idea IDE. 

[![idea-file-new-project.png](https://i.postimg.cc/KjqQmTbX/idea-file-new-project.png)](https://postimg.cc/T5bn9phQ)


*  Enter repository URL https://github.com/springdryad/jira_tests.git , select local directory and click Clone button.

[![idea-get-from-version-control.png](https://i.postimg.cc/wxGrN1Zf/idea-get-from-version-control.png)](https://postimg.cc/G9vzwmcG)

 <h2>Test Execution</h2>
 
To run the tests expand the project structure and right click on 'TestCases' class => click Run 'TestCases'.

 
 [![idea-run-tests.png](https://i.postimg.cc/T1vSSFxy/idea-run-tests.png)](https://postimg.cc/phkq50px)
 
 
 
 <h2>Build status:</h2>
 <div>
 </div>
 
 [![springdryad](https://circleci.com/gh/springdryad/jira_tests.svg?style=shield)](https://app.circleci.com/pipelines/github/springdryad/jira_tests)
