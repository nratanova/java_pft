package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("Group2");//Создается объект с именем Group2
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1)); //Хэширование, предварительная проверка,
    // при помощи более быстрого способа
    Groups after = app.group().all();
    assertThat(after, equalTo
            (before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    //CoreMatchers.equalTo - проверялка, параметр - то, что надо проверить
  }

  @Test
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
