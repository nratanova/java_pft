package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.goTo().gotoHomePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstName("Nat").withLastName("Rat").
            withMobPhone("11111111111").withEmail("qwert3@gmail.com").withGroup("Test");
    app.contact().create((contact), true);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1); //Проверка, что кол-во контактов увеличилось на 1

    //Вычисление максимального идентификатора контакта
    //Способ сравнения через лямда функцию
    //contact.setId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId()); //функция сравнения объектов по Id
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);//Сравнение списков контактов
  }
}
