# Automation Framework
Creating My own Automation Framework for Web Automation of Amazon site using Java as the core programming language and Selenium as Automation Tool.

# Objective
Visit Amazon Website and test the flow of navigating to a particular product based on specific category & filters, then viewing Product details from the webpage.

# Libraries and Frameworks
This sample using the following libraries and frameworks:
- Java JDK-1.8 as programming language
- Selenium as web browser automation tool
- Maven as build tool and package management
- Extent Report as the testing report strategy
- TestNG as a testing framework to support the test creation, execution and reporting

# Features
- Cross Browser Testing through different browsers like Firefox, Chrome.
- Cross Platform Testing on any of the popular operating systems like Windows, Mac or linux.
- Parallel testing.
  - Thread safe driver (using thread local) support for parallel testing.
  - DataDriven Test support for execution in parallel.
- POM Design Pattern.
- Factory classes.
- Web page interaction classes.
- Test utility classes.
- Custom Exceptions.
- Log test actions to extent report.
- Provision to run the test in local or selenoid.
- Provision to build a Docker image for the automated test framework.

# Parallel testing
- In this framework, parallel execution is achieved through below settings:
  - In testng.xml, at test level, the attribute is set as `<test thread-count="5" name="AmazonTest" parallel="methods">` to drive the parallel execution for each method run
- To support for parallel testing, we must share WebDriver in more than one thread. 
  - Thread safety of Driver instances is achieved using `ThreadLocal`
  - File `DriverManager.java` in folder src/main/java/com/amazon/driver has 3 methods - get, set and unload driver.

# Data Driven Test
- `test-data-supplier` library is used for the feature of Data Driven testing in Framework.
- Test data used in the Framework is JSON file format.
- Data provider method has been appended with parallel attribute as `@DataSupplier(runInParallel = true)` to support for parallel testing.
- Path of the Test data JSON file is setup as a final static variable in `FrameworkConstants` class.
- POJO class for the Test Data JSON file is created under the same folder where `DataProviderTest` class is available.

# Page Object Model - Design Pattern
- Classes are created for each page used in the Test 
   - WebElements required from the page are located using Locators and stored as Variables
   - Methods are created for all the actions to be performed on the specific page

# Factory Classes
- WebDriver initialization is achieved through `DriverFactory` class
  - Thread Local instance of WebDriver in `Driver.java` is loaded with Driver from `DriverFactory` class based on the Configuration of `runmode` in properties file
   - If runmode is `local`, then WebDriver is initialized using `WebDriverManager` 
   - If runmode is `remote`, then WebDriver is initialized using `RemoteWebDriver` 
- Explicit Wait Implementation is achieved through `ExplicitWaitFactory` class
  - Explicit wait is setup to wait for elements satisfying different conditions such as CLICKABLE, PRESENCE, VISIBLE.
  - Wait Conditions are configured via Custom Enum `WaitStrategy`.
  - Timeout Duration for the wait is setup as a final static variable in `FrameworkConstants` class

# Utility classes
- Page Actions class with the required helper methods for page interactions like click, sendkeys.
- Dynamic XPath Generator class to generate the XPath dynamically in the run using the Input passed.
- Property Reader to read the Config.properties file using the `Owner` library
  - All properties in config.properties file are put in Enum Map as key-value pair, the key being a Custom Enum `ConfigProperties` loaded with name of all properties 
- Screenshot Util to capture the screenshot from the web page

# Custom Exceptions
- To catch all the custom Exceptions occuring in Framework, class named `FrameworkException` is created and used across the Framework.
- To catch browser invocation related Exceptions, class named `BrowserInvocationFailureException` is created and used, which inherits parent from `FrameworkException`.
- To catch Web Element related Exceptions, class named `WebElementNotFoundException` is created and used, which inherits parent from `FrameworkException`.

# Reporting
- Extent Report is used in Framework for logging and reporting the test results in HTML format with the name as `index.html` under `extentreports` folder.
  - Using Configuration Properties file, flag is set to decide if report needs to be overridden for every test run.
- Path where the report needs to be generated is set up as a static variable in `FrameworkConstants` class, which is dynamically updated based on the flag.
- Extent Report initialization and creation of tests in Extent Report is done from `Listener` class under src/main/java/com/amazon/listeners.
- `FrameworkLogger` class is used for logging status to the tests in Extent Report, the logging being achieved using `Consumer` interface.
  - All Possible Log statuses are configured in a Custom Enum `LogType`.
- On finish of the Test Suite run, Framework is configured to open the report automatically in default browser.

# Remote run using Selenoid
- Running the tests in remote machine is achieved through `Selenoid` in Framework.
- `Docker compose` file in Framework is updated with all the docker commands to pull & run all the required Docker images for the remote run.
- `Selenoid UI` is mapped to the port 8090 in local host
  - Live execution can be viewed if set to true while initializing Remote WebDriver
  - Video will be recorded for the tests run if set to true while initializing Remote WebDriver
  - Docker compose is configured to save the video recordings in local machine under the folder `c/selenoid/video` with file name format as `browserName + "_" + version + ".mp4"`

# Docker image
- `Docker file` in Framework is updated with all the commands to be run for creating `Docker image` of the Framework.

# Usage Instructions
- Clone the project from the GitHub url : `https://github.com/SuganyaSundar/SeleniumJavaFramework.git`
- Update all the properties in `config.properties` file under src/test/resources/config folder as per the test requirements

- Update the `AmazonTestData.json` file under src/test/resources folder with the test data as per the test requirements
- If the runmode is configured to `local` in properties file, do the following steps: 
  - Open command prompt from the folder where Project is available 
  - Run the maven command `mvn clean test`
- If the runmode is configured to `remote` in properties file, do the following steps:
  - Update the required browsers' configuration in `browsers.json` file under the Project folder
  - Open command prompt from the folder where Project is available
  - Run the below docker compose command  
        `docker compose -f docker-compose-selenoid.yaml up -d`
  - Post successful finish of the docker compose, open Docker Desktop and see all the images downloaded are running in Docker Containers under a single network. 
  - Now, run the maven command `mvn clean test`
  - Navigate to `http://localhost:8090`, to see the Selenoid UI sessions
  - Videos of the Sessions will be available in `c/selenoid/video`
- On Test completion, Extent Report with status of all tests opens automatically in default browser. 
- In command prompt, run the below docker command to create a docker image for the framework with the name `amazontestframeworkdockerimage`  
      `docker build -t amazontestframeworkdockerimage .`
- Open Docker desktop application to see the Docker image created under Images section.
