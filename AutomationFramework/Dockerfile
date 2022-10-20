FROM maven:3.6.3-openjdk-11

#copying src of test framework
COPY src /home/SeleniumTestFramework/src

#copying pom.xml of test framework
COPY pom.xml /home/SeleniumTestFramework

#copying testng.xml of test framework
COPY testng.xml /home/SeleniumTestFramework

#Running the actual command
RUN mvn -f /home/SeleniumTestFramework/pom.xml clean test -e -DskipTests=true