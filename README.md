# TullamoreQA

[![Build Status](https://travis-ci.org/GavinKenna/tullamoreqa.svg?branch=master)](https://travis-ci.org/GavinKenna/tullamoreqa)
[![codecov](https://codecov.io/gh/GavinKenna/tullamoreqa/branch/master/graph/badge.svg)](https://codecov.io/gh/GavinKenna/tullamoreqa)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/0baecd4b142b4fe4980bf422520ee918)](https://www.codacy.com/project/gavin.kenna/tullamoreqa/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=GavinKenna/tullamoreqa&amp;utm_campaign=Badge_Grade_Dashboard)
[![BCH compliance](https://bettercodehub.com/edge/badge/GavinKenna/tullamoreqa?branch=master)](https://bettercodehub.com/)

An open-source Q&A System (In the works). Currently on a pre-release version that doesn't do a whole lot yet.

Table of Contents
=================

   * [TullamoreQA](#tullamoreqa)
   * [Table of Contents](#table-of-contents)
   * [Components of TQA](#components-of-tqa)
   * [What will version 1.0.0 look like?](#what-will-version-100-look-like)
   * [Building](#building)
   * [Integration Tests](#integration-tests)
      * [Using a Local DB](#using-a-local-db)
         * [Example](#example)
      * [Using Docker](#using-docker)
         * [Example](#example-1)
   * [Before You Commit](#before-you-commit)
   * [Windows Issues](#windows-issues)

# Components of TQA

* tullamore-ui
    * Web end UI for creating Questions, adding Answers, logging in and creating user accounts.
    * Not yet written
    * Angular 3 or React
* tullamoreqa-core-api
    * Public API of TQA
    * Service, Repository and Controller APIs
* tullamoreqa-core-impl
    * Implementation of TQA APIs
* tullamoreqa-domain
    * Location of all TQA Domain entites, i.e.
        * Answer
        * Question
        * User
        * etc
* tullamoreqa-it
    * Location of Integration Tests for TQA. These tests connect to a Postgres DB 


# What will version 1.0.0 look like?

  My plans for TQA 1.0 will be:
 
 * Ability to add Questions, Answers, Users etc via UI
 * A usable UI (perhaps Angular 3)
 * A CLI client to interact with TQA
 * Swagger integration for the TQA API
 * OAuth authentication
 * User roles (Admin, Regular User, etc)
 * Implement HATEOAS / HAL support

# Building

  Building TQA does not require any particular tools, other than Java 8 and Maven.
  
  **Building for Linux/MacOS**

  `$ ./build.sh`
  
  **Building for Windows + Linux/MacOS**

  `$ mvn clean install`

# Integration Tests

Currently TQA officially supports three Database implementations :
* Postgres 9.6.10
* MySQL 8
* H2 1.4

As TQA uses Hibernate it is entirely possible to use another DB implementation, but currently they are not tested in TQA integration tests.

Running Integration Tests can be done in two ways:
1. Run the tests against an already-running DB implementation. 
2. Run the tests against a Docker DB container.

In both cases Postgres is chosen as the default DB, so if you don't specify the DB implementation Postgres is assumed.

## Using a Local DB
To run the tests against an already-running database you must first check the db properties file in the TQA Integration Tests resource and confirm the settings are correct, or modify them to suit your database settings.
The properties file lives at `tullamoreqa-it/src/test/resources/application-{DB}LocalIT.properties`

Local DB tests are enabled by specifying the Maven Profile `-PlocalIT` and (optionally) passing in the DB implementation via `-Ddb=h2`

**Note - if you don't specify `-Ddb=` then `postgres` is assumed**

### Example
Let's take an example, let's say you have a MySQL Database running at `http://2.4.6.01:3306/my-numberwang-db`

Direct yourself to `tullamoreqa-it/src/test/resources/application-mysqlLocalIT.properties`

Modify the JDBC URL from:

`spring.datasource.url = jdbc:mysql://localhost:3306/mysql`

to

`spring.datasource.url = jdbc:mysql://2.4.6.01:3306/my-numberwang-db`

Run the tests by executing :

`$ mvn clean install -PlocalIT -Ddb=mysql`

## Using Docker
TQA utilizes Docker to initialize a container DB to connect to. 

For this you will need Docker installed.

Docker DB tests are enabled by specifying the Maven Profile `-PdockerIT` and (optionally) passing in the DB implementation via `-Ddb=h2`

**Note - if you don't specify `-Ddb=` then `postgres` is assumed**

### Example
Let's take an example, let's say you want to have the integration tests run against a fresh H@ Database.

Run the tests by executing :

`$ mvn clean install -PdockerIT -Ddb=h2`

# Before You Commit

Did you check out the handy  TullamoreQA Coding Style? [https://github.com/GavinKenna/tullamoreqa/wiki/Code-Style](https://github.com/GavinKenna/tullamoreqa/wiki/Code-Style)

After you have made all of your changes, simply run the validate script which will run checkstyle against your code and kick off the Integration Tests. The validate script is handy for quickly finding out if your changes will break the build or not.

For Linux/MacOS users you can simply call the script:

`$ ./validate.sh`

For Windows users I don't have a `.bat` or `.cmd` script yet so you will have to manually execute :

`$ mvn clean install checkstyle:check javadoc:javadoc -PdockerIT verify`
  
# Windows Issues
  
  Sometimes when running the Integration Tests with Windows and 'Docker for Windows', you may see some Connection Timeout issues. To solve this simply execute the following (as admin) in a command prompt:
  
  ``route add 172.17.0.0 MASK 255.255.0.0 10.0.75.2``
