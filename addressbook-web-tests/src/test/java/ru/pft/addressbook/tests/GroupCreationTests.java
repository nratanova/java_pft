package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount(); //Получение кол-ва групп до операции
    app.getGroupHelper().createGroup(new GroupData("Test1", null, null));
    int after = app.getGroupHelper().getGroupCount(); //Получение кол-ва групп после операции
    Assert.assertEquals(after, before + 1); //Проверка, что после создания группы кол-во групп увелисилось на 1
  }

}
