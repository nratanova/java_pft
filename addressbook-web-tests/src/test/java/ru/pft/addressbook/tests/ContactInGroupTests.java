package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.Contacts;

/**
 * Created by Natalya on 24.03.2017.
 */
public class ContactInGroupTests extends TestBase {
    @Test
    public void testContactInGroup() {

        Contacts before = app.db().contacts();
        ContactData contact = before.iterator().next(); //Первый попавшийся из списка

        app.goTo().gotoHomePage();
       // app.contact().addInGroup(contact); //доделать
    }
}
