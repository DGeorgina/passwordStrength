import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Checker {

    public static final HashSet<String> COMMON_PASSWORDS = new HashSet<>(Arrays.asList("password", "12345", "qwerty", "admin,"));
    public static final int MIN_LENGTH = 8;
    public static final int MIN_LENGTH_SATISFIED_PTS = 2;

    public static final int IS_NOT_COMMON_PTS = 2;
    public static final int UNIQUE_PTS = 2;
    public static final int CONTAINS_LOWERCASE_PTS = 1;
    public static final int CONTAINS_UPPERCASE_PTS = 1;
    public static final int CONTAINS_DIGIT_PTS = 1;
    public static final int CONTAINS_SPECIAL_CHAR_PTS = 1;

    public PasswordStrength checkStrength(String password, String username) {

        PasswordStrength passwordStrength = new PasswordStrength();

        if (password.length() >= MIN_LENGTH) {
            passwordStrength.addPts(MIN_LENGTH_SATISFIED_PTS);
        } else {
            passwordStrength.addMessage(String.format("The password should be at least %d characters long.", MIN_LENGTH));
        }

        passwordStrength.merge(checkComplexity(password));

        if (COMMON_PASSWORDS.contains(password)) {
            passwordStrength.addMessage("The password is very common and therefore easy to guess.");
        } else {
            passwordStrength.addPts(IS_NOT_COMMON_PTS);
        }


        if (password.equalsIgnoreCase(username)) {
            passwordStrength.addMessage("The password can not be the same as the username.");
        } else {
            passwordStrength.addPts(UNIQUE_PTS);
        }

        return passwordStrength;
    }

    private PasswordStrength checkComplexity(String password) {
        PasswordStrength passwordStrength = new PasswordStrength();


        if (password.matches(".*[a-z]+.*")) {
            passwordStrength.addPts(CONTAINS_LOWERCASE_PTS);
        } else {
            passwordStrength.addMessage("The password should contain at least 1 lowercase letter.");
        }


        if (password.matches(".*[A-Z]+.*")) {
            passwordStrength.addPts(CONTAINS_UPPERCASE_PTS);
        } else {
            passwordStrength.addMessage("The password should contain at least 1 uppercase letter.");
        }

        if (password.matches(".*[0-9]+.*")) {
            passwordStrength.addPts(CONTAINS_DIGIT_PTS);
        } else {
            passwordStrength.addMessage("The password should contain at least 1 digit.");
        }

        if (password.matches(".*[^a-zA-Z0-9]+.*")) {
            passwordStrength.addPts(CONTAINS_SPECIAL_CHAR_PTS);
        } else {
            passwordStrength.addMessage("The password should contain at least 1 special character.");
        }
        return passwordStrength;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your username or press enter, please:");
        String username = scanner.nextLine();


        System.out.println("Enter your password, please:");
        String password = scanner.nextLine();


        while (password.isEmpty()) {
            System.out.println("The password can not be empty");
            System.out.println("Enter password:");
            password = scanner.nextLine();
        }


        Checker checker = new Checker();
        PasswordStrength ps = checker.checkStrength(password, username);

        System.out.println("Password strength: " + ps.calculateStrength());
        System.out.println("Score: " + ps.getScore() + "/10");


        ps.getFeedbackMessages().stream().forEach(x -> System.out.println(x));

    }
}
