## TL;DR
| Option        | Decision           
| ------------- |:-------------:
| Tabs vs Spaces      | Spaces 
| Spacing      | 4      
| Encoding | UTF-8     
| Line Separator  | LF (Unix and OS X) \n 
| Line Limit | 80
| Hard Limit? | YES

## Introduction
_This Code Style Wiki is based on the Spring Code Framework CodeStyle_

This document defines the coding standards for source files in the TullamoreQA framework (TQA onwards). 

The structure of this document is based on the [Sun/Oracle Java Standards](https://www.oracle.com/technetwork/java/codeconventions-150003.pdf) reference and is _work in progress_.

## Source File Basics

### File encoding: UTF-8

Source files must be encoded using `UTF-8`.

### Indentation

* Indentation uses _spaces_ (4 spaces)
* Unix (LF), not DOS (CRLF) line endings
* Eliminate all trailing whitespace, except for the last line in each Java file. The last line must be an empty line.

## Source file structure

A source file consists of the following, in this exact order:

* License
* Package statement
* Import statements
* Exactly one top-level class

Exactly one blank line separates each of the above sections.

### License

Each source file must specify the following license at the very top of the file:

```
	/**
	 * Copyright 2018 the original author or authors.
        * This program is free software.
        * You should have received a copy of the GNU Affero General Public License along with this program.
        *
        * You can be released from the requirements of the license by requesting a commercial license.
	 *
	 * This program is free software: you can redistribute it and/or modify
        * it under the terms of the GNU Affero General Public License as
        * published by the Free Software Foundation, either version 3 of the
        * License, or (at your option) any later version.
        *
        * This program is distributed in the hope that it will be useful,
        * but WITHOUT ANY WARRANTY; without even the implied warranty of
        * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        * GNU Affero General Public License for more details.

        * You should have received a copy of the GNU Affero General Public License
        * along with this program.  If not, see <https://www.gnu.org/licenses/>.
        **/
```

Always check the date range in the license header. For example, if you've modified a file in 2019 whose header still reads:
```   
* Copyright 2018 the original author or authors.
```
Then be sure to update it to 2019 accordingly:
```
* Copyright 2018-2019 the original author or authors.
```

### Import statements

The import statements are structured as follow:

* import `com.gkenna.`
* import all other imports
* `blank line`
* import `java.net`
* import `java.util`
* import `javax.`
* `blank line`
* import static all other imports

**NOTE** - Please do not use 'import package.*'. Explicitly state each class being imported.

Also, static imports should not be used in production code. They should be used in test code, especially for things like `org.junit.Assert`.

### Java source file organization

The following governs how the elements of a source file are organized:

1. static fields
* Including mandatory LOGGER.
1. normal fields
1. constructors
1. (private) methods called from constructors
1. static factory methods
1. JavaBean properties (i.e., getters and setters)
1. method implementations coming from interfaces
1. private or protected templates that get called from method implementations coming from interfaces
1. other methods
1. `equals`, `hashCode`, and `toString`

Note that private or protected methods called from method implementations should be placed immediately below the methods where they're used. In other words if there 3 interface method implementations with 3 private methods (one used from each), then the order of methods should include 1 interface and 1 private method in sequence, not 3 interface and then 3 private methods at the bottom.

Above all, the organization of the code should feel _natural_.

## Logging

If you are writing a Java class, please include a LOGGER that is used adequately. As a user and developer, I would rather see too many log messages than not many.

LOGGER objects should be all upper case, `final`, `private` and `static`.

### Defining a Logger

Define a LOGGER immediately under the class definition as follows:

```
public class AnswerServiceImpl extends EntryServiceImpl   
        implements AnswerService {                        
                                                          
    /**                                                   
     * Answer Service Logger.                             
     */                                                   
    private static final Logger LOGGER =                  
            LogManager.getLogger(AnswerServiceImpl.class);

```

Lovely, isn't it?

### Logger Usage

As previously mentioned, it is better to log **too much** than **too little**. Using the correct LOG Level is key. You should choose between `ERROR, WARNING, DEBUG` and `INFO` as effectively as possible. 

If you are logging an object, make use of `{}` within the log message - Try not use `+`. 
See the examples below for reference:

Good:

```
LOGGER.info("New Question {} was added successfully by User ID {}.", question, user.getId()); 
```

BAD:

```
LOGGER.info("New Question " + question + " was added successfully by User ID " + user.getId() + "."); 
```

It's longer and harder to read.

## Formatting

### Braces

#### Block-like constructs: K&R style

Braces mostly follow the _Kernighan and Ritchie style_ (a.k.a., "Egyptian brackets") for nonempty blocks and block-like constructs:

* No line break before the opening brace but prefixed by a single space
* Line break after the opening brace
* Line break before the closing brace
* Line break after the closing brace if that brace terminates a statement or the body of a method, constructor, or named class
* Line break before else, catch and finally statements
* If a method name (include parameters) goes beyond 80 characters and you must wrap, place the first line of code after an empty line.

Example 1:

```
public final ResponseEntity<?> addTag(@RequestBody final Tag input) {
        LOGGER.debug("Adding Tag {}", input);

        try {
            tagService.addTag(input);
        } catch (TagAlreadyExistsException e) {
            LOGGER.error(e.getMessage());
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }

        if (tagService != null) {
            LOGGER.info("All good");
        }
}
```

Example 2 (Wrapping method name):

```
public final ResponseEntity<Tag> getTag(
            @PathVariable("id") final String tagId) {

       LOGGER.debug("Getting Tag {}", input);
       ..........
}
       
```
Take note of the first empty line after the opening method bracket for the second example.

### Line wrapping

80 characters is the *preferred* line length we aim for. In some cases the preferred length can be achieved by refactoring code slightly. In other cases it's  just not possible.

When wrapping a lengthy expression, 80 characters is the length at which we aim to wrap. Put the separator symbols at the start of the next line. For instance:

```
if (thisLengthyMethodCall(param1, param2) && anotherCheck()
        && yetAnotherCheck()) {

}
```

### Blank Lines

Add one blank line before the following elements:

* `static {}` block
* Fields
* Constructors
* Inner classes

Add one blank line after a method signature that is multiline, i.e.

```
@Override
protected Object invoke(FooBarOperationContext context, 
        AnotherSuperLongName name) {

    // code here
}
```

## Class declaration

Try as much as possible to put the `implements`, `extends` section of a class declaration on the same line as the class itself. 

Order the classes so that the most important comes first.

## Naming

### Constant names

Constant names use `CONSTANT_CASE`: all uppercase letters, with words separated by underscores. 

Every constant is a `static final` field, but not all `static final` fields are constants. Constant case should therefore be chosen only if the field **is really** a constant.

Example:

```
// Constants
private static final Object NULL_HOLDER = new NullHolder();
public static final int DEFAULT_PORT = -1;

// Not constants
private static final ThreadLocal<Executor> executorHolder = new ThreadLocal<Executor>();
private static final Set<String> internalAnnotationAttributes = new HashSet<String>();
```

### variable names

Avoid using single characters as variable names. For instance prefer `Method method` to `Method m`. 

## Programming Practices

### File history

* A file should look like it was crafted by a single author, not like a history of changes
* Don't artificially spread things out that belong together

### Organization of setter methods

Choose wisely where to add a new setter method; it should not be simply added at the end of the list. Perhaps the setter is related to another setter or relates to a group. In that case it should be placed near related methods.

* Setter order should reflect order of importance, not historical order
* Ordering of _fields_ and _setters_ should be **consistent**

### Ternary Operator

Wrap the ternary operator within parentheses, i.e. `return (foo != null ? foo : "default");`

Also make sure that the _not null_ condition comes first.

### Use of @Override

Always add `@Override` on methods overriding or implementing a method declared in a super type.

### Use of @since

* `@since` should be added to every new class with the version of the framework in which it was introduced
* `@since` should be added to any *new* **public** and **protected** methods of an existing class

### Utility classes

A class that is only a collection of static utility methods must be named with a `Utils` suffix, must have a `private` default constructor, and must be `abstract`. Making the class `abstract` and providing a `private` _default_ constructor prevent anyone from instantiating it. For example:

```
public abstract MyUtils {

    private MyUtils() {
        /* prevent instantiation */
    }

    // static utility methods
}
```

### Field and method references

A field of a class should *always* be referenced using `this`. A method of class, however, should never be referenced using `this`.

## Javadoc

### Javadoc formatting

The following template summarizes a typical use for the Javadoc of a method.

```
 /**
     * Return a list of all {@link Answer}s associated with a particular
     * {@link com.gkenna.tullamoreqa.domain.Question}.
     *
     * @param questionId Filter all  {@link Answer}s based on this
     *                   {@link com.gkenna.tullamoreqa.domain.Question} ID
     * @param pageable   Potentially add Pagination.
     * @return List of {@link Answer}s in Page form.
     */
Page<Answer> findByQuestionId(Long questionId, Pageable pageable);
```

In particular, please note:

* Use an imperative style (i.e. _Return_ and not _Returns_) for the first sentence.
* One blank line between the description and the parameter descriptions.
* If the description is defined with multiple paragraphs, start each of them with `<p>`.
* If a parameter description needs to be wrapped, do not indent subsequent lines (see `parserContext`).

The Javadoc of a class has some extra rules that are illustrated by the sample below:

```
/**
 * Repository for containing {@link Answer}s. Will be called by the responsible
 * Service, in this case it will be
 * {@link com.gkenna.tullamoreqa.core.api.services.AnswerService}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 * @see com.gkenna.tullamoreqa.core.api.services.AnswerService
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
```

* Each class must have a `@since` tag with the version in which the class was introduced.
* The order of tags for class-level Javadoc is `@author`, `@since` and `@see`.

The following are additional general rules to apply when writing Javadoc:

* Use `{@code}` to wrap code statements or values such as `null`.
* If a type is only referenced by a `{@link}` element, use the fully qualified name in order to avoid an unnecessary `import` declaration.

## Tests

I like to use JUnit for Unit tests.

### Naming

I like to follow the : 
```shouldDoXandYSuccessfully()``` and ```shouldCallExceptionXWhenPerformingY()``` naming convention. Makes it easier to see what the tests are doing when run on the command line. For example, which of the following tests are easier to figure out what they do:

```
Ran the following 2 tests:
1) shouldThrowQuestionDoesNotExistExceptionWhenRetrievingInvalidQuestion()
2) questionTest()
```

### Mocking

I like to use Mockito for mocking.
