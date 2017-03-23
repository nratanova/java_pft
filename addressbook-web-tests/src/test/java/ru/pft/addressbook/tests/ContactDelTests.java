package ru.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.Contacts;
import ru.pft.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Наташа on 03.02.2017.
 */
public class ContactDelTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        String groupName = "Test";

        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName(groupName));
            }
            app.goTo().gotoHomePage();
            app.contact().create(new ContactData().withFirstName("Nat").withLastName("Rat").
                    withMobPhone("888888888").withEmail("qwert3@gmail.com").withGroup(groupName), true);
        }
    }

    @Test
    public void testContactDel() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next(); //Первый попавшийся из списка
        app.goTo().gotoHomePage();
        app.contact().delete(deletedContact);
        assertEquals(app.contact().count(), before.size() - 1); //Хэширование
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
