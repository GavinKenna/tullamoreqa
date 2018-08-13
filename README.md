# TullamoreQA

[![Build Status](https://travis-ci.org/GavinKenna/tullamoreqa.svg?branch=master)](https://travis-ci.org/GavinKenna/tullamoreqa)
[![Coverage Status](https://coveralls.io/repos/github/GavinKenna/tullamoreqa/badge.svg?branch=master)](https://coveralls.io/github/GavinKenna/tullamoreqa?branch=master)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FGavinKenna%2Ftullamoreqa.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2FGavinKenna%2Ftullamoreqa?ref=badge_shield)

An open-source Q&A System (In the works). Currently on a pre-release version that doesn't do a whole lot yet.

**Components of TQA**

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


**What will version 1.0.0 look like?**

My plans for TQA 1.0 will be:
* Ability to add Questions, Answers, Users etc via UI
* A usable UI (perhaps Angular 3)
* A CLI client to interact with TQA
* Swagger integration for the TQA API
* OAuth authentication
* User roles (Admin, Regular User, etc)

**Building**

Building TQA does not require any particular tools, other than Java 8 and Maven.

`$ mvn clean install`

**Testing**

TQA utilizes Docker to initialize a Postgres DB to connect to. 

For this you will need Docker installed.

To run the full Integration Test suite (and bring up the Postgres Docker Container) execute maven with the `test` 
profile, as follows:

`$ mvn clean install -Ptest`


## License
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FGavinKenna%2Ftullamoreqa.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2FGavinKenna%2Ftullamoreqa?ref=badge_large)