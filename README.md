<h1 style="text-align:center;">
	Washington State University<br />
	CPT_S 582 Software Testing<br />
	Spring 2024
</h1>

<table align=center>
<tr><td>Team Name</td><td colspan=4>Bean_582_2024_aptFinder</td></tr>
<tr><td>Repository</td><td colspan=4><a href="https://github.com/ejep520/bean_582_2024" target=_blank>https://github.com/ejep520/bean_582_2024</a></td></tr>
<tr><td>Project topic</td><td colspan=4> A java spring web application to enable users to locate an apartment available for rent.</td></tr>
<tr><td>Team members</td><td>Name</td><td>EMail</td><td>WSU ID</td><td>Contact</td></tr>
<tr><td></td><td>Bryan Lipscy</td><td><a href="mailto:bryan.lipscy@wsu.edu">bryan.lipscy@wsu.edu</a></td><td>011860966</td><td>Yes</td></tr>
<tr><td></td><td>Erik Jepsen</td><td><a href="mailto:erik.jepsen@wsu.edu">erik.jepsen@wsu.edu</a></td><td>011868711</td><td></td></tr>
<tr><td></td><td>Antonio Barber</td><td><a href="mailto:antonio.barber@wsu.edu">antonio.barber@wsu.edu</a></td><td>011551036</td><td></td></tr>
<tr><td></td><td>Nico Williams</td><td><a href="mailto:nico.williams@wsu.edu">nico.williams@wsu.edu</a></td><td>011878369</td><td></td></tr>
</table>

## Pre-requisites
* An IDE - [Eclipse](https://eclipseide.org/), [Visual Studio Code](https://code.visualstudio.com/), [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* [Gradle](https://gradle.org/install/)
* [BellSoft Liberica JDK version 17](https://bell-sw.com/pages/downloads/#jdk-17-lts)
* [JUnit 5](https://junit.org/junit5/) 

## Running this project
* Install docker desktop
* Start Docker Desktop
* Install [Java 17 liberica](https://bell-sw.com/pages/downloads/#jdk-17-lts)
* Set environment variables (Windows)
  * Name: `bean_mysql_admin_pass` Value: `MyPassword`
* Run: `dockerbuild.bat <any password>`
* Run: `docker compose up`
* Navigate to http://localhost:8080



##Configuring Java 17

Configure JDK: Since the project requires BellSoft Liberica JDK version 17 (IntelliJ IDEA)

Go to File > Project Structure.
Under Project, ensure that the Project SDK is set to JDK 17. If not, click New... and navigate to the directory where JDK 17 is installed.
Click Apply and then OK.
Set Environment Variables (Windows): Set the environment variables as mentioned in the project README:







  ##Environment Variables
  Using System Settings:
Right-click on "This PC" or "My Computer" and select "Properties".
Click on "Advanced system settings" on the left-hand side.
In the System Properties window, click on the "Environment Variables" button.
In the "System Variables" section, click "New" to add a new environment variable.
Enter the variable name as bean_mysql_admin_pass and the value as MyPassword.
Click "OK" to save the changes.


Using Command Prompt:
Open Command Prompt.
To set a new environment variable for the current session, use the set command:
  
set bean_mysql_admin_pass=MyPassword
To make the environment variable persist across sessions, use the setx command:
setx bean_mysql_admin_pass MyPassword
Note: setx command requires administrative privileges.
  
## Introduction
This is a demostration project of an apartment finder web portal.

## Test
[Junit 5](https://junit.org/junit5/) and [Selenium](https://selenium.dev) will be used for automated test cases.
* Unit testing. Individual units of testable code must have a corresponding unit test. Mocking may be used appropriate
* Functional tests
  * API Testing
    * APIs may be needed to facilitate testing. These must be such that authorization is required with a fixed token
  * Integration Testing
  * UI Integration Testing
    * UI Testing will be accomplished using [Selenium](https://selenium.dev) to provide programmatic control of the UI to simulate the end user's behaviors.
    * UI Test scripts should not be one monolithic script. Each script should accomplish one use case
* Tests
  * Are "paid to promote" prioritization listed correctly?
  * Do searches return the proper properties?
    * I want all apartments with 3+ bedrooms. Do all the appropriate apartments get returned
  * The correct floorplan image is returned. This may just be a URL that can be made to the src attribute of an <image> element
  * Are the correct interior images returned?
  * Are favorited properties maintained?

// TODO:

* Show graphs, tables, and UML


![image (1)](https://github.com/ejep520/bean_582_2024/assets/134741051/7db40450-d45b-4104-ad10-1f758b93d141)

#LOAD TESTING
Load Testing with k6
k6 is an open-source load testing tool that allows you to test the performance of your web applications. Follow these steps to incorporate k6 load testing into your project:

Install k6: If you haven't already installed k6, you can download it from the official website and follow the installation instructions for your operating system.
https://k6.io/docs/get-started/installation/

Write Test Scripts: Create test scripts using JavaScript to define the load testing scenarios. These scripts will simulate user behavior and interactions with your web application. You can create multiple scripts to cover different use cases and scenarios.

Run Load Tests: Run the k6 load tests from the command line using the k6 run command followed by the path to your test script. For example:

k6 run path/to/test/script.js


Analyze Results: After running the load tests, analyze the results to identify any performance bottlenecks or issues in your application. k6 provides detailed metrics and reports to help you understand the performance of your application under load.

Incorporate Tests into CI/CD Pipeline: Consider integrating k6 load tests into your continuous integration and delivery pipeline to automatically run tests and monitor the performance of the application with each deployment.

### Additional links
* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.1/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.1/gradle-plugin/reference/html/#build-image)
* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
* [Spring](https://spring.io/)
* [Spring Initializer](https://start.spring.io/)
