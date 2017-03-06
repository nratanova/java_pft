package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Наташа on 03.02.2017.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().list().size() ==0) {
      app.group().create(new GroupData("Test1", null, null));
    }
  }

  @Test
  public void testGroupModification() {
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    //При модификации указываем новые имя, header, footer + старый ID (перед модификацией)
    GroupData group = new GroupData(before.get(index).getId(),"Test", "Test2", "Test3");
    app.group().modify(index, group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size()); //Проверка, что после модификации группы кол-во групп не изменилось

    //Проверка после модификации контакта
    before.remove(index); //Удаление старого объекта из первонач.списка
    before.add(group); //Добавление нового объекта
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()); //функция сравнения объектов по Id
    before.sort(byId); //сортировка по Id
    after.sort(byId); //сортировка по Id
    Assert.assertEquals(before,after); //Сравнение отсортированных списков


  }


}
