# commons

A repository of commonly used methods, either unavailable in other repositories,
but more often with improved apis.

### assortments package

An experimental alternative to the Collections' library that enforces the usage of an
Equals interface to prevent the problem of using a collection without defining a custom
equals and hashcode method when you ought to have.

More work is needed here

- wrapper classes for the existing Collections base types,
  i.e. new assortment.List(new ArrayList());
- Javadoc is missing on key abstract classes
- Mutable interfaces for all base type assortments, i.e. MutableList, MutableSet, MutableMap, etc

### util package

Utility classes that I find useful, typically with improved apis.