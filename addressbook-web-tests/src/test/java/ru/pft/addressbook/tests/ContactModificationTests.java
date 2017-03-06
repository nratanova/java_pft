package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Наташа on 03.02.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test (enabled = false)
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Nat", "Rat", null,
              null, "888888888", "qwert3@gmail.com", "Test"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selecContact(before.size()-1);
    app.getContactHelper().initContactModification(before.size()-1);
    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Test", "Testov", "Testik",
            "85555555555", "79051111111", "qwerty3@gmail.com", null);
    app.getContactHelper().fillContactForm((contact), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()); //Проверка, что кол-во контактов не изменилось

    before.remove(before.size()-1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId()); //функция сравнения объектов по Id
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
