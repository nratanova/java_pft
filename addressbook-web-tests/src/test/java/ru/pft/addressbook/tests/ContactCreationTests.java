package ru.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("Nat").withLastName("Rat").
            withMobPhone("11111111111").withEmail("qwert3@gmail.com").withGroup("Test");
    app.contact().create((contact), true);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() + 1); //Проверка, что кол-во контактов увеличилось на 1

    ;
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
