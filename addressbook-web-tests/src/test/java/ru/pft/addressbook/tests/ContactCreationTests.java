package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.appmager.NavigationHelper;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount(); //Получение кол-ва контактов до операции
    app.getContactHelper().createContact(new ContactData("Nat", "Rat", null,
            null, "888888888", "qwert3@gmail.com", "Test"), true);
    int after = app.getContactHelper().getContactCount(); //Получение кол-ва контактов после операции
    Assert.assertEquals(after, before+1); //Проверка, что кол-во контактов увеличилось на 1
  }
}
