package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("Group2");//Создается объект с именем Group2
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1); //Проверка, что после создания группы кол-во групп увелисилось на 1

    //Присвоение группе идентификатора
    //Берем коллекцию объектов, превращаем в поток(stream), преобразуем поток объектов в поток
    // идентификаторов - mapToInt, где на вход подается анонимная функция, которая преобразует объект в число,
    //после чего находится максимальное значение (max()) и результат преобразуется в обычное целое число (getAsInt())
    group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());

    //Модификация множества "до"
    before.add(group);

    //Сравнение множеств
    Assert.assertEquals(before,after);
  }

}
