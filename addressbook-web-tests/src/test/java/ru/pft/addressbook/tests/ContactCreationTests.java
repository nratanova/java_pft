package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.goTo().gotoHomePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("Nat").withLastName("Rat").
            withMobPhone("11111111111").withEmail("qwert3@gmail.com").withGroup("Test");
    app.contact().create((contact), true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1); //Проверка, что кол-во контактов увеличилось на 1

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);//Сравнение множеств контактов
  }
}
