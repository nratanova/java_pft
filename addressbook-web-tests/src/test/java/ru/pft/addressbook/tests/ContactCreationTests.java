package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.gotoContactNewPage();
    app.fillContactForm(new ContactData("Natalia", "Ratanova", "hairpin90", "81231232323", "79055097630", "rnatash3@gmail.com"));
    app.submitContactCreation();
    app.returnHomePage();
  }
}
