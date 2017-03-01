package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.appmager.NavigationHelper;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactData("Nat", "Rat", null,
            null, "888888888", "qwert3@gmail.com", "Test"), true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1); //Проверка, что кол-во контактов увеличилось на 1
  }
}
