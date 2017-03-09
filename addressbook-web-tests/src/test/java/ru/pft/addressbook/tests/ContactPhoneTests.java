package ru.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Наташа on 08.03.2017.
 */
public class ContactPhoneTests extends TestBase {

  @Test
  public void testContactPhones() {
    app.goTo().gotoHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    assertThat(contact.getMobPhone(), equalTo(cleaned(contactInfoFromEditForm.getMobPhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
  }
  //Удаление лишних символов
  public String cleaned (String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
