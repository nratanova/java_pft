package ru.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by Natalya on 24.03.2017.
 */
public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        //Создаем новую сессию
        HttpSession session = app.newSession();
        //Проверка, что пользователь залогинился
        assertTrue(session.login("administrator","root"));
        //Проверка, что зашел именно нужный пользователь
        assertTrue(session.isLoggedInAs("administrator"));
    }
}


