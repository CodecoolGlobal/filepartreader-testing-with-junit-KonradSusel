# FilePartReader testing with JUnit tests (also parametrized tests and mocks).

Class FilePartReader reads input txt file. It can read whole file or specified range of lines.

Class FileWordAnalyzer contains methods allowing to:
- order all words alphabetically and return list of all words
- find words containing specified substring and return them as a list
- get words that are palindromes, and return them as list
- check if a given word is a palindrome and return true/false


TESTS:
Class FilePartReaderTest checks if FilePartReader is reading correct lines as specified by user. 
Checks if exception is thrown if user asks to read incorrect line number (e.g. lower then 0).
Checks if exception is thrown when txt file is not found.

Class FileWordAnalyzerTest tests all methods in FileWordAnalyzer and checks:
- if words are returned in alphabetical order 
- if all words containing specified substring were found
- if returned list of palindromes is correct 
- if a word that is a palindrome is correctly recognized as palindrome
