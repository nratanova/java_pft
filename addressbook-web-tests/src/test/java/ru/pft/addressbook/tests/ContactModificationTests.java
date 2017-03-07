package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

import java.util.Set;

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

    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next(); //Первый попавшийся из списка
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Test").
            withLastName("Testov").withNickName("Testik").withHomePhone("85555555555").
            withMobPhone("79051111111").withEmail("qwerty3@gmail.com");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size()); //Проверка, что кол-во контактов не изменилось

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}
