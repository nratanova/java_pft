package ru.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by Наташа on 03.02.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Nat").withLastName("Rat").
              withMobPhone("888888888").withEmail("qwert3@gmail.com").withGroup("Test"), true);
    }
  }

  @Test
  public void testContactModification() {

    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next(); //Первый попавшийся из списка
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Test").
            withLastName("Testov").withNickName("Testik").withHomePhone("85555555555").
            withMobPhone("79051111111").withEmail("qwerty3@gmail.com");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size()); //Проверка, что кол-во контактов не изменилось
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
