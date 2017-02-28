package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

/**
 * Created by Наташа on 03.02.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Nat", "Rat", null,
              null, "888888888", "qwert3@gmail.com", "Test"), true);
    }
    int before = app.getContactHelper().getContactCount(); //Получение кол-ва контактов до операции
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Test", "Testov", "Testik",
            "85555555555", "79051111111", "qwerty3@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnHomePage();
    int after = app.getContactHelper().getContactCount(); //Получение кол-ва контактов после операции
    Assert.assertEquals(after, before); //Проверка, что кол-во контактов не изменилось
  }
}
