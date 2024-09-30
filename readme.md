# Books API Automation Testing Suite
This project is an automated testing suite for the Books API, designed using Java, Cucumber BDD, and REST Assured. It covers all CRUD operations provided by the API, ensuring that the service functions as expected.

## Table of Contents
- Prerequisites
- Project Structure 
- Setup Instructions
- Configuration
- Running the Tests
- Test Reports

### Prerequisites
Before you begin, ensure you have the following installed on your machine:

- Java 11 or higher
- Maven 3.6 or higher

### Project Structure
```markdown
├── pom.xml
├── README.md
├── src
├── main
│   └── java
│       └── com.booksapi
│           ├── clients
│           │   └── BooksApiClient.java
│           ├── config
│           │   ├── Configuration.java
│           │   └── ConfigurationManager.java
│           ├── models
│           │   └── Book.java
│           └── specs
│               └── BooksApiSpec.java
│   
└── test
    ├── java
    │   └── com.booksapi
    │       ├── runners
    │       │   └── TestRunner.java
    │       └── steps
    │           └── BooksApiSteps.java
    └── resources
        ├── features
        │   └── books_api.feature
        └── junit-platform.properties
        └── api.properties
```


### Setup Instructions

Clone the Repository

```shell
git clone https://github.com/yourusername/books-api-automation.git
```
Navigate to the Project Directory

```shell
cd books-api-automation
```

Open the Project in Your IDE

Import the project as a Maven project to resolve all dependencies.

### Configuration
api.properties
Update the api.properties file located at src/test/resources/api.properties with your API credentials:


```declarative
api.baseUri=http://77.102.250.113:17354
api.basePath=/api/v1
api.username=your_username       # Replace with actual username
api.password=your_password       # Replace with actual password
log.all=true
```
Note: Replace your_username and your_password with the credentials provided for accessing the Books API.
junit-platform.properties
The junit-platform.properties file in src/test/resources configures Cucumber:


```
cucumber.glue=com.booksapi.steps
cucumber.plugin=pretty, html:target/cucumber-reports.html
cucumber.features=classpath:features
cucumber.publish.quiet=true
```
### Running the Tests
You can run the automated tests using Maven:

```shell
mvn clean test
```

### Test Reports
After running the tests, a report will be generated in HTML format:

Location: target/cucumber-reports.html
Viewing the Report: Open the cucumber-reports.html file in your web browser to view detailed test results.