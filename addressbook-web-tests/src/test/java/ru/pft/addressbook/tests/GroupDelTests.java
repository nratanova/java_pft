package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

public class GroupDelTests extends TestBase {

    @Test
    public void testGroupDel() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("Test1", null, null));
        }
        int before = app.getGroupHelper().getGroupCount(); //Получение кол-ва групп до операции
        app.getGroupHelper().selectGroup(before-1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount(); //Получение кол-ва групп после операции
        Assert.assertEquals(after, before - 1); //Проверка, что после удаления группы кол-во групп уменьшилось на 1
    }
}
