package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.appmager.NavigationHelper;
import ru.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoNewContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Nat", "Rat", null,
            null, "888888888", "qwert3@gmail.com", "TestMod2"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnHomePage();
  }
}
