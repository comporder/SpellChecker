# SpellChecker

This project involves a Spell Checker application. The aim of this application is to check and correct spelling errors in a text. The application provides an interactive checking environment to the user through a Graphical User Interface (GUI).

The main component of the project is the `SpellChecker` class. This class is derived from the JFrame class and contains the GUI components. It includes the following important methods:

- `controlOfPunctuation()`: This method checks and corrects punctuation and capitalization errors in the text area. It displays the incorrect words in a separate text area.

- `controlOfSpelling()`: This method checks the spelling of words in the text area. It identifies words with spelling errors and displays them in a separate text area.

- `addStringsToTextArea()`: This method takes text strings in an ArrayList and adds them to a JTextArea.

- `suggest()`: This method checks if a word is misspelled. If the word is misspelled, it suggests corrections based on the Levenshtein distance algorithm using a Binary Search Tree data structure.

- `complete()`: This method enables the completion of the last word in the text area. It finds all the words starting with a specified prefix in the Binary Search Tree and updates the text area with the completed word.

The `BinarySearchTree` class is used to represent the word dictionary. The `readFromFile()` method reads words from a text file named "dictionary.txt" and loads them into a binary search tree. The `search()` method checks if a word is present in the binary search tree. The `suggestion()` method finds correction suggestions for a word using the Levenshtein distance algorithm.

The application provides an interactive checking process between the GUI components and the dictionary operations. After the text entered in the text area is checked, the incorrect words are displayed in a separate text area, and correction suggestions are provided to the user.

This project offers a tool to assist users in detecting and correcting spelling errors while writing text.
