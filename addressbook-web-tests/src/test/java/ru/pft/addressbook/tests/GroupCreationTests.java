package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData("Test2", null, null);
    app.group().create(group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1); //Проверка, что после создания группы кол-во групп увелисилось на 1

//Вычисление максимальногоо ID (ID новой группы)

    /*Превращение списка в поток stream(), по потоку пробегается лямбда функция - сравниватель,
    котрая находит максимальный элемент сравнивая объекты типы GroupData по идентификатору
    на выходе будет группа с максимальным идентификатором, достаем это id - get().getId()*/

    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()); //функция сравнения объектов по Id
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);//Сравнение списков групп
  }

}
