package ru.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by Natalya on 24.03.2017.
 */
public class RegistrationTests extends TestBase {

    @Test
 public void testRegistration() {
    app.registration().start("user1","user1@localhost.localdomain");
    }
}
