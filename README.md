# QA Automation

This repository uses Selenium, via a local Chrome Driver or a Remote Driver that connects to a Selenium Grid.

## Running using docker

### Pre-requisite

- (Optional but recommended) Install WSL2
- Install Docker
- Install Docker Compose
- Ensure you have nothing running on port 80

In a terminal window, run the following to start a local Selenium Grid.

```shell script
docker-compose up
```

Next, execute the test from the project root using:

```shell script
docker run -it --rm  \
  -v "$(pwd)":/ui-test-automation-tdd \
  -v ~/.m2/repository:/root/.m2/repository \
  -w /ui-test-automation-tdd maven:3.6.3-jdk-11 \
  mvn test -DhubUrl=http://host.docker.internal:4444 \
    -DbrowserName=chrome \
    -DconfigPath=./env/prod.properties \
    -DGroups=GroupName
```
- GroupName
    - To Run particular group of Test Cases: -DexcludedGroups=GroupName
    - To exclude test case: -DexcludedGroups=GroupName

- ConfigFile
    - To Run Test Cases on specific environment: -DconfigPath=./env/prod.properties

Note: Maven needs to download the internet so this will take some time on first run. The command will populate
your local Maven cache to speed things up on subsequent runs.

## Running Java and Maven on host

To run the test on host you'll need to install Java (11+) and Maven. For this reason it's not recommended
unless you know what you're doing. Once you've installed Java, Maven and possibly the Chrome Driver, run test as above.

## Running using your host Chrome installation

To run tests against your host Chrome Browser you'll need to download the latest
[Chrome Driver](https://chromedriver.chromium.org/downloads) for your platform. If you're running on Linux or
Mac, ensure the Driver is executable.

Specify the Chrome Dri`ver when running the test.

```shell script
mvn test -DconfigPath=./env/prod.properties -Dwebdriver.chrome.driver=/path/to/chromedriver -DGroups=GroupName
```

## Watching the tests run

You can watch tests run by connecting to the Grid via VNC on the appropriate port. On a Mac you can do this
through finder (Go > Connect to server).

The docker compose file exposes Chrome at `vnc://localhost:5900`. The password is `secret`.