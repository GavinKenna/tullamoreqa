# TullamoreQA

[![Build Status](https://travis-ci.org/GavinKenna/tullamoreqa.svg?branch=master)](https://travis-ci.org/GavinKenna/tullamoreqa)
[![codecov](https://codecov.io/gh/GavinKenna/tullamoreqa/branch/master/graph/badge.svg)](https://codecov.io/gh/GavinKenna/tullamoreqa)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/0baecd4b142b4fe4980bf422520ee918)](https://www.codacy.com/project/gavin.kenna/tullamoreqa/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=GavinKenna/tullamoreqa&amp;utm_campaign=Badge_Grade_Dashboard)
[![BCH compliance](https://bettercodehub.com/edge/badge/GavinKenna/tullamoreqa?branch=master)](https://bettercodehub.com/)

An open-source Q&A System (In the works). Currently on a pre-release version that doesn't do a whole lot yet.

Table of Contents
=================

* [Components of TQA](#components-of-tqa)
* [What will version 1.0.0 look like?](#what-will-version-100-look-like)
* [Building](#building)
* [Testing](#testing)

# Components of TQA

* tullamore-ui
    * Web end UI for creating Questions, adding Answers, logging in and creating user accounts.
    * Not yet written
    * Angular 3
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

# Building

  Building TQA does not require any particular tools, other than Java 8 and Maven.
  
  **Building for Linux/MacOS**

  `$ ./build.sh`
  
  **Building for Windows + Linux/MacOS**

  `$ mvn clean install`

# Testing

  TQA utilizes Docker to initialize a Postgres DB to connect to. 

  For this you will need Docker installed.
  
  **Running full test suite for Linux/MacOS**
  
  To run the full Integration Test suite (and bring up the Postgres Docker Container), Checkstyle, Cobertura and Javadocs, execute the validate.sh script:
  
  `$ ./validate.sh`
  
  **Running full test suite for Windows + Linux/MacOS**
  
  To run the full Integration Test suite (and bring up the Postgres Docker Container), Checkstyle, Cobertura and Javadocs, execute the following in a terminal :
  
  `$ mvn clean install checkstyle:check javadoc:javadoc -Ptest verify`
