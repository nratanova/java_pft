package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.appmager.ApplicationManager;
import ru.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Наташа on 03.02.2017.
 */
public class ContactDelTests extends TestBase {

  @Test (enabled = false)
  public void testContactDel() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Nat", "Rat", null,
              null, "888888888", "qwert3@gmail.com", "Test"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selecContact(before.size()-1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().switchYes();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()-1); //Проверка, что кол-во контактов уменьшилось на 1

    //Сравнение списков до удаления и после
    before.remove(before.size()-1); //Удаляем перед сравнением тот контакт, который подлежит удалению
    Assert.assertEquals(before, after);
  }
}
