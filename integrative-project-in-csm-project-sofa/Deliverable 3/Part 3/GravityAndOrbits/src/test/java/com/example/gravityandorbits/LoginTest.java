package com.example.gravityandorbits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    /*Add these VM options for running tests:

    --add-opens org.junit.platform.commons/org.junit.platform.commons.util=ALL-UNNAMED
    --add-opens org.junit.platform.commons/org.junit.platform.commons.logging=ALL-UNNAMED

     */

    @Test
    void validateLoginTest_BeforeLogin() {
        Login login = new Login();
        boolean result = login.validateLogin();
        assertFalse(result);
    }

}