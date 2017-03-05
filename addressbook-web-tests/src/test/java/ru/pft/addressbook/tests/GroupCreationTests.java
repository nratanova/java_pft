package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("Test2", null, null);
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1); //Проверка, что после создания группы кол-во групп увелисилось на 1

//Вычисление максимальногоо ID (ID новой группы)

    /*Превращение списка в поток stream(), по потоку пробегается лямбда функция - сравниватель,
    котрая находит максимальный элемент сравнивая объекты типы GroupData по идентификатору
    на выходе будет группа с максимальным идентификатором, достаем это id - get().getId()*/

    group.setId(after.stream().max((Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));//Сравнение множеств групп
  }

}
