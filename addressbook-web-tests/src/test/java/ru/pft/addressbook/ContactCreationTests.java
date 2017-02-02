package ru.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{
  @Test
  public void testContactCreation() {
    gotoContactNewPage();
    fillContactForm(new ContactData("Natalia", "Ratanova", "hairpin90", "81231232323", "79055097630", "rnatash3@gmail.com"));
    submitContactCreation();
    returnHomePage();
  }
}
