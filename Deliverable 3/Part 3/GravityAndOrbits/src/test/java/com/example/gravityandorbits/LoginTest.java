package com.example.gravityandorbits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    void validateLoginTest_BeforeLogin() {
        Login login = new Login();
        boolean result = login.validateLogin();
        assertFalse(result);
    }
    @Test
    void validateLoginTest_AfterLogin() {
        Login login = new Login();
        boolean result = login.validateLogin();
        assertTrue(result);
    }
    @Test
    void setOnLoginSuccessTest() {
        // Setup
        Login login = new Login();
        boolean[] callbackExecuted = {false};
        login.setOnLoginSuccess(() -> callbackExecuted[0] = true);

        // Trigger login via public method (ex:, button click handler)
        login.verifyLogin("test", "12345678"); // Public method that calls private verifyLogin()

        // Verify
        assertTrue(callbackExecuted[0], "Callback should run after successful login");
    }

}