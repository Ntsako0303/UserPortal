/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package userportal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Login class.
 *
 * Note on alignment with Login:
 * checkUsername(), checkPasswordComplexity(), checkCellphoneNumber()
 * and isLoginSuccessful() are all no-argument instance methods - they
 * check the fields the instance was already constructed with, not a
 * value passed in. So the "valid" cases below call those no-arg
 * methods directly on the registered instance. For the "invalid"
 * cases there's no second, badly-registered instance to call them on,
 * so those use the static Login.isUsernameValid(...) /
 * isPasswordValid(...) / isCellphoneNumberValid(...) helpers instead,
 * which take the value to check as a parameter and contain the exact
 * same validation rules.
 *
 * @author Student
 */
public class LoginTest {

    // Registered user used across the tests
    private Login instance;

    private static final String FIRST_NAME = "Kyle";
    private static final String SURNAME = "Smith";
    private static final String VALID_USERNAME = "kyl_1";
    private static final String INVALID_USERNAME = "kyle!!!!!!!";
    private static final String VALID_PASSWORD = "Ch&&sec@ke99!";
    private static final String INVALID_PASSWORD = "password";
    private static final String VALID_CELLPHONE = "+27838968976";
    private static final String INVALID_CELLPHONE = "08966553";

    public LoginTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new Login(FIRST_NAME, SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_CELLPHONE);
    }

    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of checkUsername method, of class Login.
     * Username correctly formatted: contains an underscore and is no
     * more than five characters long. checkUsername() takes no
     * argument - it checks the username the instance was built with.
     */
    @Test
    public void testCheckUsername() {
        System.out.println("checkUsername");
        boolean expResult = true;
        boolean result = instance.checkUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test an incorrectly formatted username via the static validator,
     * since there's no instance registered with invalid data.
     */
    @Test
    public void testCheckUsernameIncorrect() {
        System.out.println("checkUsername - incorrectly formatted");
        boolean expResult = false;
        boolean result = Login.isUsernameValid(INVALID_USERNAME);
        assertEquals(expResult, result);
    }

    /**
     * assertTrue - username correctly formatted.
     */
    @Test
    public void testCheckUsernameTrue() {
        System.out.println("checkUsername - assertTrue");
        assertTrue(instance.checkUsername());
    }

    /**
     * assertFalse - username incorrectly formatted.
     */
    @Test
    public void testCheckUsernameFalse() {
        System.out.println("checkUsername - assertFalse");
        assertFalse(Login.isUsernameValid(INVALID_USERNAME));
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     * Password meets the complexity requirements: at least eight
     * characters, a capital letter, a number and a special character.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity();
        assertEquals(expResult, result);
    }

    /**
     * Test a password that does not meet the complexity requirements,
     * via the static validator.
     */
    @Test
    public void testCheckPasswordComplexityIncorrect() {
        System.out.println("checkPasswordComplexity - does not meet requirements");
        boolean expResult = false;
        boolean result = Login.isPasswordValid(INVALID_PASSWORD);
        assertEquals(expResult, result);
    }

    /**
     * assertTrue - password meets complexity requirements.
     */
    @Test
    public void testCheckPasswordComplexityTrue() {
        System.out.println("checkPasswordComplexity - assertTrue");
        assertTrue(instance.checkPasswordComplexity());
    }

    /**
     * assertFalse - password does not meet complexity requirements.
     */
    @Test
    public void testCheckPasswordComplexityFalse() {
        System.out.println("checkPasswordComplexity - assertFalse");
        assertFalse(Login.isPasswordValid(INVALID_PASSWORD));
    }

    /**
     * Test of checkCellphoneNumber method, of class Login.
     * Cell phone number correctly formatted with an international
     * code.
     */
    @Test
    public void testCheckCellphoneNumber() {
        System.out.println("checkCellphoneNumber");
        boolean expResult = true;
        boolean result = instance.checkCellphoneNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test an incorrectly formatted number, via the static validator.
     */
    @Test
    public void testCheckCellphoneNumberIncorrect() {
        System.out.println("checkCellphoneNumber - incorrectly formatted");
        boolean expResult = false;
        boolean result = Login.isCellphoneNumberValid(INVALID_CELLPHONE);
        assertEquals(expResult, result);
    }

    /**
     * assertTrue - cell phone number correctly formatted.
     */
    @Test
    public void testCheckCellphoneNumberTrue() {
        System.out.println("checkCellphoneNumber - assertTrue");
        assertTrue(instance.checkCellphoneNumber());
    }

    /**
     * assertFalse - cell phone number incorrectly formatted.
     */
    @Test
    public void testCheckCellphoneNumberFalse() {
        System.out.println("checkCellphoneNumber - assertFalse");
        assertFalse(Login.isCellphoneNumberValid(INVALID_CELLPHONE));
    }

    /**
     * Test of loginUser method, of class Login.
     * Login successful - the system returns true.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser - successful");
        boolean expResult = true;
        boolean result = instance.loginUser(VALID_USERNAME, VALID_PASSWORD);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginUser method with incorrect details.
     * Login failed - the system returns false.
     */
    @Test
    public void testLoginUserFailed() {
        System.out.println("loginUser - failed");
        boolean expResult = false;
        boolean result = instance.loginUser(INVALID_USERNAME, INVALID_PASSWORD);
        assertEquals(expResult, result);
    }

    /**
     * assertTrue - login successful.
     */
    @Test
    public void testLoginUserTrue() {
        System.out.println("loginUser - assertTrue");
        assertTrue(instance.loginUser(VALID_USERNAME, VALID_PASSWORD));
    }

    /**
     * assertFalse - login failed.
     */
    @Test
    public void testLoginUserFalse() {
        System.out.println("loginUser - assertFalse");
        assertFalse(instance.loginUser(INVALID_USERNAME, INVALID_PASSWORD));
    }

    /**
     * Test of isLoginSuccessful method, of class Login.
     * A successful login returns true. isLoginSuccessful() takes no
     * argument - it reports the outcome of the last loginUser() call
     * made on this instance.
     */
    @Test
    public void testIsLoginSuccessful() {
        System.out.println("isLoginSuccessful - successful");
        instance.loginUser(VALID_USERNAME, VALID_PASSWORD);
        assertTrue(instance.isLoginSuccessful());
    }

    /**
     * Test of isLoginSuccessful method after a failed login.
     */
    @Test
    public void testIsLoginSuccessfulFailed() {
        System.out.println("isLoginSuccessful - failed");
        instance.loginUser(INVALID_USERNAME, INVALID_PASSWORD);
        assertFalse(instance.isLoginSuccessful());
    }

    /**
     * Test of getFirstName method, of class Login.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        String expResult = FIRST_NAME;
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSurname method, of class Login.
     */
    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        String expResult = SURNAME;
        String result = instance.getSurname();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsername method, of class Login.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        String expResult = VALID_USERNAME;
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCellphoneNumber method, of class Login.
     */
    @Test
    public void testGetCellphoneNumber() {
        System.out.println("getCellphoneNumber");
        String expResult = VALID_CELLPHONE;
        String result = instance.getCellphoneNumber();
        assertEquals(expResult, result);
    }
}