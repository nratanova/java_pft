package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupDelTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("Test1"));
        }
    }

    @Test
    public void testGroupDel() {
        Set<GroupData> before = app.group().all();
        //iterator - позволяет последов-но перебирать элементы
        GroupData deleteGroup = before.iterator().next(); //Вернется первый попавшийся элемент множества
        app.group().delete(deleteGroup);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1); //Проверка, что после удаления группы кол-во групп уменьшилось на 1

        before.remove(deleteGroup);
        Assert.assertEquals(after,before);
    }


}
