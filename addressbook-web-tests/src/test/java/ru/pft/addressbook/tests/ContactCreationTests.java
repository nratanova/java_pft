package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
    File photo = new File ("src/test/resources/panda.jpg");
    ContactData contact = new ContactData().withFirstName("Nat").withLastName("Rat").withHomePhone("79078965454").
            withMobPhone("11111111111").withWorkPhone("79064531223").withEmail("qwert3@gmail.com").withGroup("Test").withPhoto(photo);
    app.contact().create((contact), true);
    assertEquals(app.contact().count(), before.size() + 1); //Хэширование
    Contacts after = app.contact().all();
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testCurrentDir() {
    File currentDir = new File (".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File ("src/test/resources/panda.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
