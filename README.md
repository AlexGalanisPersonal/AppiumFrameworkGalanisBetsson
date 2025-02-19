# 🎯 Swag Labs Mobile Test Automation Framework

A comprehensive end-to-end mobile test automation framework for the Sauce Demo mobile application using Appium, Cucumber, and Java. The framework demonstrates modern testing practices and clean architecture principles.

## 📋 Project Overview

The framework is built for mobile testing with Android devices, featuring:

### Core Components
- **BDD Testing**: Cucumber feature files for clear test scenarios
- **Mobile Automation**: Appium for reliable Android testing
- **Page Objects**: Well-structured test code organization
- **Configuration**: Flexible environment and test settings

## 🏗️ Architecture Overview

### Project Structure
```text
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   ├── com.example.context/      # Test context & state
│   │   │   ├── com.example.runners/      # Test execution setup
│   │   │   ├── com.example.stepdefs/     # Test implementation
│   │   │   └── com.example.utils/        # Helper utilities
│   │   └── resources/
│   │       ├── features/                 # Test scenarios
│   │       └── config/                   # Configuration files
└── pom.xml                              # Project dependencies
```
## 🔍 Features Covered

### Authentication

- Successful login with standard user
- Failed login with invalid credentials
- Failed login with locked out user


### Product Management

- View product details
- Add products to cart
- Remove products from cart
- Update cart quantities


### Checkout Process

- Complete checkout with valid information
- Validate checkout form requirements
- Verify order summary
- Confirm order completion



## 🛠️ Setup Instructions

- Clone the repository
- Install prerequisites:

Java 11 or higher
Maven 3.8+
Android Studio with emulator
Appium Server
Node.js and npm


- Start Appium server:

bashCopyappium

- Setup Android emulator:

bashCopyemulator -avd Pixel_6_API_33

- Install dependencies:

bashCopymvn clean install
Dependencies
xmlCopy<dependencies>
    <dependency>
        <groupId>io.appium</groupId>
        <artifactId>java-client</artifactId>
        <version>7.6.0</version>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.2.3</version>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-testng</artifactId>
        <version>7.2.3</version>
    </dependency>
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.5</version>
    </dependency>
</dependencies>

## 🚀 Running Tests
- Command Line
- bashCopymvn clean test
- IDE (IntelliJ IDEA)

- Open the TestRunner class
- Click the green play button next to the class definition
- Select 'Run TestRunner'

- Running Specific Features
- bashCopymvn test -Dcucumber.filter.tags="@login"

## ⚙️ Configuration
### The framework uses two property files for configuration:

- env.properties
propertiesCopyappium.server.url=http://127.0.0.1:4723
device.name=emulator-5554
app.package=com.swaglabsmobileapp
app.activity=com.swaglabsmobileapp.MainActivity

- test.properties
propertiesCopystandard.username=standard_user
standard.password=secret_sauce
locked.username=locked_out_user
locked.password=secret_sauce

## 💻 Framework Features

### Page Object Pattern Implementation

- Clean separation of test logic and UI interactions
- Reusable mobile element interactions
- Centralized element locators


### BDD Approach

- Feature files written in Gherkin syntax
- Step definitions with clear mapping
- Scenario context sharing
- Tag-based test execution


### Robust Test Design

- Explicit waits for element synchronization
- Comprehensive error handling
- Clear assertions and verifications

### Test Context Management

- Shared context between step definitions
- Clean driver management
- Configuration management
- Resource cleanup

## 📄 Architecture Overview
Core Design Principles

### Separation of Concerns

- Clear separation between test specifications and implementations
- Each component has a single responsibility
- Modular design for easy maintenance
- Clean architecture patterns


### Mobile Automation Best Practices

- Reliable element locators
- Proper synchronization strategies
- Error handling for mobile-specific challenges
- Performance optimization


### Test Data Management

- Property file based configuration
- Environment-specific settings
- Secure credential management
- Data-driven test capabilities


## 🧩Framework Components

### Core Components

- DriverManager: Handles mobile driver lifecycle
- ConfigurationManager: Manages test configuration
- TestContext: Shares state between steps
- Hooks: Manages test lifecycle

## 🧪Test Layers

- Feature Files: Test specifications in Gherkin
- Step Definitions: Test implementation
- Utility Classes: Common functionality


## 🖌️Design Decisions

### Choice of Technologies

- Appium: Industry standard for mobile automation
- Cucumber: Popular BDD framework
- TestNG: Flexible test execution
- Maven: Robust build management

### Error Handling Strategy

- Retry mechanisms for flaky elements
- Detailed error logging
- Clean resource management


### Maintainability Features

- Clear project structure
- Consistent coding standards
- Comprehensive documentation
- Reusable components

## ✅ Best Practices Implemented

### Code Organization

- Logical package structure
- Clear naming conventions
- Proper encapsulation
- DRY principles

### Test Reliability

- Proper waits and synchronization
- Robust element locators
- Error recovery mechanisms
- Clean test data management

### Maintainability
- Comprehensive documentation
- Modular design
- Clear separation of concerns
- Consistent coding standards
