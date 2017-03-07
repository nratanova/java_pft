package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

import java.util.Set;

/**
 * Created by Наташа on 03.02.2017.
 */
public class ContactDelTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Nat").withLastName("Rat").
              withMobPhone("888888888").withEmail("qwert3@gmail.com").withGroup("Test"), true);
    }
  }

  @Test
  public void testContactDel() {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next(); //Первый попавшийся из списка
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1); //Проверка, что кол-во контактов уменьшилось на 1

    //Сравнение множеств до удаления и после
    before.remove(deletedContact); //Удаляем перед сравнением тот контакт, который подлежит удалению
    Assert.assertEquals(before, after);
  }
}
