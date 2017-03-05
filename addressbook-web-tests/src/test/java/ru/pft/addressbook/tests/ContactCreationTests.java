package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.appmager.NavigationHelper;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Nat", "Rat", null,
            null, "888888888", "qwert3@gmail.com", "Test");
    app.getContactHelper().createContact((contact), true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1); //Проверка, что кол-во контактов увеличилось на 1

   //Вычисление максимального идентификатора контакта
  //Способ сравнения через лямда функцию
    contact.setId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));//Сравнение множеств контактов
  }
}
