# TullamoreQA Domain

Module containing all Entities that are used within TQA.

**Answer**

Answer entity, which is written by a `User` and in response to a `Question`.

**Comment**

Comment entity, which can be in response to either a `Question` or an `Answer`. Written by a `User`.

**Entry**

Abstract class that `Comment`, `Answer` and `Question` derive from. Contains the `Body` object and contains 
information about the `User` that wrote it.

**Question**

Question entity, that is written by a `User` and contains `Tag`s relating to it.

**User**

User entity, that contains information about a User account. 