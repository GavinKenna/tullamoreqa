**Style Guide**

Please see https://github.com/GavinKenna/tullamoreqa/wiki/Code-Style for the official Code Style guide.

This file will be updated as time goes on.

For the time being, the Style Guide of TQA isn't set in stone at the moment, but it is tested against Checkstyle - Sun / Oracle standards.
It uses 99% of the Sun style guide, with one change:

* Method parameters (say in Setters) can be the same name as the field they will be set to.

**Contributing**

Before creating a pull request, simply run the following command to verify your code changes 1) build, 2) conform to the Checkstyle standards and 3) tests (both unit and integration) run successfully:

```
$ mvn checkstyle:check clean install javadoc:javadoc -Ptest
```
