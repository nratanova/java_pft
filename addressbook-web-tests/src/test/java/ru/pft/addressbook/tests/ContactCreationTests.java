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
    //Способ сравнения через цикл
    int max = 0;
    for (ContactData c:after) {
      if (c.getId()>max) {
        max=c.getId();
      }
    }
  //Способ сравнения через вспомогательный объект (сравниватель)
    Comparator<? super ContactData> byId = new Comparator<ContactData>() {
      @Override
      public int compare(ContactData o1, ContactData o2) {
        return Integer.compare(o1.getId(),o2.getId());
      }
    };
    int max2 = after.stream().max(byId).get().getId();
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));//Сравнение множеств контактов
  }
}
