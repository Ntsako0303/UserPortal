/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userportal;

/**
 * Represents a registered user and holds the validation rules used
 * during registration, plus the logic used to log a user back in.
 *
 * Most checks are available in two forms:
 *  - a no-argument form, which checks the value this instance was
 *    already constructed with (e.g. checkUsername() checks the
 *    username this Login was built with)
 *  - a one-argument form, which checks whatever value is passed in,
 *    regardless of what this instance holds (e.g.
 *    checkUsername(someOtherUsername))
 * Both forms share the same underlying static rule, so the two never
 * disagree with each other.
 *
 * @author Student
 */
public class Login {

    // ==========================
    // Declare variables
    // ==========================

    private final String firstName;
    private final String surname;
    private final String username;
    private final String password;
    private final String cellphoneNumber;

    private boolean loginSuccessful;

    // ==========================
    // Constructor
    // ==========================

    public Login(String firstName, String surname, String username,
                 String password, String cellphoneNumber) {

        this.firstName = firstName;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;
        this.loginSuccessful = false;
    }

    // ==========================
    // Static validation rules
    // ----------------------------------------------------------------
    // The single source of truth for each rule. Every other check
    // method (instance-level, no-arg or parameterized) delegates to
    // one of these, so the logic only ever lives in one place.
    // ==========================

    public static boolean isUsernameValid(String username) {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    public static boolean isPasswordValid(String password) {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;

        // Check every character in the password
        for (int i = 0; i < password.length(); i++) {

            char character = password.charAt(i);

            if (Character.isUpperCase(character)) {
                hasCapitalLetter = true;
            }
            if (Character.isDigit(character)) {
                hasNumber = true;
            }
            if (!Character.isLetterOrDigit(character)) {
                hasSpecialChar = true;
            }
        }

        return hasCapitalLetter && hasNumber && hasSpecialChar;
    }

    public static boolean isCellphoneNumberValid(String cellphoneNumber) {

        if (cellphoneNumber == null) {
            return false;
        }

        // South African format: +27 followed by exactly 9 digits
        return cellphoneNumber.matches("\\+27[0-9]{9}");
    }

    // ==========================
    // Instance-level checks - no argument
    // ----------------------------------------------------------------
    // Check the value this instance was constructed with.
    // ==========================

    public boolean checkUsername() {
        return isUsernameValid(username);
    }

    public boolean checkPasswordComplexity() {
        return isPasswordValid(password);
    }

    public boolean checkCellphoneNumber() {
        return isCellphoneNumberValid(cellphoneNumber);
    }

    // ==========================
    // Instance-level checks - parameterized
    // ----------------------------------------------------------------
    // Check an arbitrary value instead of this instance's own field.
    // Useful for tests, or for validating input before it's known to
    // belong to this particular user.
    // ==========================

    public boolean checkUsername(String usernameToCheck) {
        return isUsernameValid(usernameToCheck);
    }

    public boolean checkPasswordComplexity(String passwordToCheck) {
        return isPasswordValid(passwordToCheck);
    }

    public boolean checkCellphoneNumber(String cellphoneNumberToCheck) {
        return isCellphoneNumberValid(cellphoneNumberToCheck);
    }

    // ==========================
    // Login User
    // ==========================

    public boolean loginUser(String enteredUsername, String enteredPassword) {

        if (enteredUsername == null || enteredPassword == null) {
            loginSuccessful = false;
            return false;
        }

        if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
            loginSuccessful = true;
            return true;
        } else {
            loginSuccessful = false;
            return false;
        }
    }

    // ==========================
    // Getters
    // ==========================

    /**
     * No-argument form: reports the outcome of the last loginUser()
     * call made on this instance.
     */
    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    /**
     * Parameterized form: true only if the last login attempt on this
     * instance succeeded AND was for the given username. Since a
     * Login instance represents a single registered user, this is
     * mainly useful when a caller has a username in hand and wants to
     * confirm both that a login happened and that it was for that
     * specific user.
     */
    public boolean isLoginSuccessful(String usernameToCheck) {
        return loginSuccessful && username.equals(usernameToCheck);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }
}