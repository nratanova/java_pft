package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.Set;

/**
 * Created by Наташа on 03.02.2017.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().all().size() ==0) {
      app.group().create(new GroupData().withName("Test1"));
    }
  }

  @Test
  public void testGroupModification() {
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next(); //Вернется первый попавшийся элемент множества
    //При модификации указываем новые имя, header, footer + старый ID (перед модификацией)
    GroupData group = new GroupData().withId(modifiedGroup.getId()).
            withName("Test").withHeader("Test2").withFooter("Test3");
    app.group().modify(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size()); //Проверка, что после модификации группы кол-во групп не изменилось

    //Проверка после модификации контакта
    before.remove(modifiedGroup); //Удаление старого объекта из первонач.списка
    before.add(group); //Добавление нового объекта
    Assert.assertEquals(before,after); //Сравнение множеств


  }


}
