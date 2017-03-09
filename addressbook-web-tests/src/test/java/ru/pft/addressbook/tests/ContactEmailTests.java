package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Наташа on 10.03.2017.
 */
public class ContactEmailTests extends TestBase {

  @Test
  public void testContactEmails() {
    app.goTo().gotoHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  //Склеивание методом обратных проверок
  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals("")) //Отфильтровываем пустые строки
            .map(ContactEmailTests::cleaned) //Ко всем тел. применяем функцию очистки
            .collect(Collectors.joining("\n")); //склеиваем полученные email в строчку и сравниваем с тем,
    // что на главной странице
  }

  //Удаление лишних символов
  public static String cleaned (String email) {
    return email.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
