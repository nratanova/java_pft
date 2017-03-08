package ru.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

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
        Groups before = app.group().all();
        GroupData deleteGroup = before.iterator().next(); //Вернется первый попавшийся элемент множества
        app.group().delete(deleteGroup);
        Groups after = app.group().all();
        assertEquals(after.size(), before.size() - 1); //Проверка, что после удаления группы кол-во групп уменьшилось на 1
        assertThat(after, equalTo(before.without(deleteGroup)));
    }


}
