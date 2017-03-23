package ru.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.Contacts;
import ru.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by Наташа on 03.02.2017.
 */
public class ContactModificationTests extends TestBase {

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
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next(); //Первый попавшийся из списка
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Test").
                withLastName("Testov").withNickName("Testik").withHomePhone("85555555555").
                withMobPhone("79051111111").withEmail("qwerty3@gmail.com");
        app.goTo().gotoHomePage();
        app.contact().modify(contact);
        assertEquals(app.contact().count(), before.size()); //Хэширование
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }


}
