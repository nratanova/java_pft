package ru.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.pft.mantis.model.MailMessage;
import ru.pft.mantis.model.UserData;
import ru.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Natalya on 26.03.2017.
 */
public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException, ClassNotFoundException {
        app.login().login("administrator","root");
        Users users = app.db().users();
        UserData modifiedUser = users.iterator().next(); //Первый попавшийся из списка
        String username = modifiedUser.getUser();
       // String username = "usertest";//для теста
        String email = modifiedUser.getEmail();
        //String email = String.format("user@localhost.localdomain");//Для теста
        String password = "password";//
        app.login().resetUserPassword(modifiedUser.getId());
        //app.login().resetUserPassword();//Для теста
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.login().finish(confirmationLink,password);
        app.login().login(username,password);
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
