package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

/**
 * Created by Наташа on 03.02.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Test", "Testov", "Testik", "85555555555", "79051111111", "qwert3@gmail.com"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnHomePage();
  }
}
