package ru.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDelTests extends TestBase {

    @Test
    public void testsGroupDel() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }

}
