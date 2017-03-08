package ru.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next(); //Вернется первый попавшийся элемент множества
    //При модификации указываем новые имя, header, footer + старый ID (перед модификацией)
    GroupData group = new GroupData().withId(modifiedGroup.getId()).
            withName("Test").withHeader("Test2").withFooter("Test3");
    app.group().modify(group);
    Groups after = app.group().all();
    assertEquals(after.size(), before.size()); //Проверка, что после модификации группы кол-во групп не изменилось

    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));


  }


}
