package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.appmager.ApplicationManager;
import ru.pft.addressbook.model.ContactData;

/**
 * Created by Наташа on 03.02.2017.
 */
public class ContactDelTests extends TestBase {

  @Test
  public void testContactDel() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Nat", "Rat", null,
              null, "888888888", "qwert3@gmail.com", "Test"), true);
    }
    app.getContactHelper().selecContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().switchYes();
    app.getNavigationHelper().gotoHomePage();
  }
}
