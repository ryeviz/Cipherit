import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password length: ");
        int length = scanner.nextInt();
        scanner.nextLine(); // consume newline character
        System.out.print("Include uppercase letters? (y/n): ");
        boolean includeUppercase = scanner.nextLine().equalsIgnoreCase("y");
        System.out.print("Include lowercase letters? (y/n): ");
        boolean includeLowercase = scanner.nextLine().equalsIgnoreCase("y");
        System.out.print("Include digits? (y/n): ");
        boolean includeDigits = scanner.nextLine().equalsIgnoreCase("y");
        System.out.print("Include special characters? (y/n): ");
        boolean includeSpecialChars = scanner.nextLine().equalsIgnoreCase("y");
        String password = generatePassword(length, includeUppercase, includeLowercase, includeDigits, includeSpecialChars);
        System.out.println("Your password is: " + password);
        scanner.close();
    }
    
    public static String generatePassword(int length, boolean includeUppercase, boolean includeLowercase, boolean includeDigits, boolean includeSpecialChars) {
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String digitChars = "0123456789";
        String specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        StringBuilder validChars = new StringBuilder();
        if (includeUppercase) {
            validChars.append(uppercaseChars);
        }
        if (includeLowercase) {
            validChars.append(lowercaseChars);
        }
        if (includeDigits) {
            validChars.append(digitChars);
        }
        if (includeSpecialChars) {
            validChars.append(specialChars);
        }
        if (validChars.length() == 0) {
            throw new IllegalArgumentException("At least one character type must be selected.");
        }
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(validChars.length());
            sb.append(validChars.charAt(index));
        }
        return sb.toString();
    }
}
