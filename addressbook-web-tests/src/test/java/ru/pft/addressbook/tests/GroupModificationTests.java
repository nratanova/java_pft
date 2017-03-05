package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Наташа on 03.02.2017.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Test1", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    //При модификации указываем новые имя, header, footer + старый ID (перед модификацией)
    GroupData group = new GroupData(before.get(before.size() - 1).getId(),"Test", "Test2", "Test3");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size()); //Проверка, что после модификации группы кол-во групп не изменилось

    //Проверка после модификации контакта
    before.remove(before.size() - 1); //Удаление старого объекта из первонач.списка
    before.add(group); //Добавление нового объекта
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()); //функция сравнения объектов по Id
    before.sort(byId); //сортировка по Id
    after.sort(byId); //сортировка по Id
    Assert.assertEquals(before,after); //Сравнение отсортированных списков


  }
}
