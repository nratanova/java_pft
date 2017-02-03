package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.appmager.ApplicationManager;

/**
 * Created by Наташа on 03.02.2017.
 */
public class ContactDelTests extends TestBase {

  @Test
  public void testContactDel() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selecContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().switchYes();
    app.getNavigationHelper().gotoHomePage();
  }
}
