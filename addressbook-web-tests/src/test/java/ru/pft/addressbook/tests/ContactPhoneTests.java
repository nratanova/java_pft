package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  //Склеивание методом обратных проверок
  private String mergePhones(ContactData contact) {
     return Arrays.asList(contact.getHomePhone(), contact.getMobPhone(), contact.getWorkPhone())
            .stream().filter((s) -> ! s.equals("")) //Отфильтровываем пустые строки
             .map(ContactPhoneTests::cleaned) //Ко всем тел. применяем функцию очистки
             .collect(Collectors.joining("\n")); //склеиваем полученные тел. в строчку и сравниваем с тем,
    // что на главной странице
  }

  //Удаление лишних символов
  public static String cleaned (String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
