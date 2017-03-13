package ru.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<Object[]>();//делаем список массивов объектов
    list.add(new Object[] {new GroupData().withName("Test")
            .withHeader("Header1").withFooter("Footer1")});//Заполнение списка тестовыми данными
    list.add(new Object[] {new GroupData().withName("Test2")
            .withHeader("Header2").withFooter("Footer2")});//Заполнение списка тестовыми данными
    list.add(new Object[] {new GroupData().withName("Test3")
            .withHeader("Header3").withFooter("Footer3")});//Заполнение списка тестовыми данными
    return list.iterator();
  }

  @Test (dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
      app.goTo().groupPage();
      Groups before = app.group().all();
      app.group().create(group);
      assertThat(app.group().count(), equalTo(before.size() + 1)); //Хэширование, предварительная проверка,
      // при помощи более быстрого способа
      Groups after = app.group().all();
      assertThat(after, equalTo
              (before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
      //CoreMatchers.equalTo - проверялка, параметр - то, что надо проверить
  }

  @Test (enabled = false)
  //Негативный тест, проверяющий, что группа с таким названием не создается
  public void testBadGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("Group2'");//Создается объект с именем Group2
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size())); //Проверка, что после создания группы кол-во групп увелисилось на 1
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }
}
