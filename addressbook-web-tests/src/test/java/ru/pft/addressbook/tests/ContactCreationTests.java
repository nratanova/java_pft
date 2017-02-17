package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoNewContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Natalia", "Ratanova", "hairpin90",
            "81231232323", "79055097630", "rnatash3@gmail.com", "Test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnHomePage();
  }
}
