package ru.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by Natalya on 26.03.2017.
 */
public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver(); //Ленивая инициализация
    }

    public void login(String username, String password) throws ClassNotFoundException {
        wd.get(app.getProperty("web.baseUrl")+"login_page.php");
        type(By.name("username"),username);
        type(By.name("password"),password);
        click(By.cssSelector("input[value='Войти']"));
    }

    public void resetUserPassword(int id) { //Для теста без параметра
        click(By.cssSelector("a[href='/mantisbt-2.2.1/manage_overview_page.php']"));
        click(By.cssSelector("a[href='/mantisbt-2.2.1/manage_user_page.php']"));
       // click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=14']")); //для теста мой юзер
        selecUserById(id);
        //click(By.cssSelector("input[value='Сбросить пароль']"));
        resetPass();
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button"));
    }

    private void selecUserById(int id) {
        wd.findElement(By.cssSelector("a[href='manage_user_edit_page.php?user_id='" + id + "']")).click();
    }

    private void resetPass() {
        wd.findElement(By.cssSelector("input[value='Сбросить пароль']")).click();
    }
}
