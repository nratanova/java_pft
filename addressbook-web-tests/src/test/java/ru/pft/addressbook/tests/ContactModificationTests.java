package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Наташа on 03.02.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Nat").withLastName("Rat").
                      withMobPhone("888888888").withEmail("qwert3@gmail.com").withGroup("Test"), true);
    }
  }

  @Test
  public void testContactModification() {

    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withId(before.get(before.size() - 1).getId()).withFirstName("Test").
            withLastName("Testov").withNickName("Testik").withHomePhone("85555555555").
            withMobPhone("79051111111").withEmail("qwerty3@gmail.com");
    int index = before.size() - 1;
    app.contact().modify(contact, index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size()); //Проверка, что кол-во контактов не изменилось

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId()); //функция сравнения объектов по Id
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
