# Revolut Technical Task
[![Build Status](https://travis-ci.com/Bartosz-D3V/revolut-technical-challenge.svg?token=tqZyPRhzSnop7iN2Y7Ug&branch=master)](https://travis-ci.com/Bartosz-D3V/revolut-technical-challenge)

This application has been written to resolve a recruitment task for a Backend Developer position at Revolut.

Description of the exercise can be found in docs/ folder with a filename "***Backend Developer Test – Revolut.pdf***".

The outcome of the exercise is a RESTful Web Application, with embedded Jetty that can be run on any system that fulfills the requirements.

# Pre-requisites

Software installed and added to a class path (as a system variable):
 1. Java 11
 2. Maven

In addition, to run the application you will need any console/terminal of your choice, or your favorite Java IDE.

## Building

In order to build an executable JAR file, please do the following:

 1. Open your terminal and navigate to the folder with the source code.
 2. Run `mvn install -DskipTests`

## Executing

To run the application file please navigate to the folder with the source code and run the following command:

    mvn exec:java
*Please make sure that port 4567 on localhost if free to use.*

## Creating account
After starting the application, please make a POST request to the following URL:

    localhost:4567/accounts
Example body:

> {
	  "owner": "owner name",
	  
	  "amount": 100
  }

## Listing available accounts
In order to list all available accounts please make a GET request to the following URL:

     localhost:4567/accounts

## Transferring money
In order to transfer money from one account to another please make a PUT request to the following URL:

     localhost:4567/accounts/transfer
Example body:

> {
	"sourceAccountId": 1,
	
	"destAccountId": 2,
	
	"amount": 50
}
## Running tests
In order to run tests, please execute the following command:

    mvn test

## Technologies used

 1. Java 11
 2. Spark
 3. ORMLite
 4. Maven
 5. JUnit
 6. Mockito
