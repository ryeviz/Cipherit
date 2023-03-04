# Password-Generator

Here's what this code does:

1.Defines a class PasswordGenerator with a main method that prompts the user to specify the password's length and complexity options, generates a random password based on those options, and prints the password.

2.Defines a method generatePassword that takes five boolean inputs indicating whether to include uppercase letters, lowercase letters, digits, and special characters in the password, and generates a random password of specified length using the selected character types.

3.Defines four strings uppercaseChars, lowercaseChars, digitChars, and specialChars that contain all possible characters for each character type.

4.Creates a StringBuilder validChars to build a string containing all valid characters based on the selected character types.

5.Adds each selected character type to validChars.

6.Throws an IllegalArgumentException if no character type is selected.

7.Creates a SecureRandom object to generate random numbers.

8.Iterates length times and adds a random character from validChars to a StringBuilder.

9.Returns the generated password as a string.
