package ru.pft.addressbook;

import org.testng.annotations.Test;

public class GroupDelTests extends TestBase {

    @Test
    public void testsGroupDel() {
        gotoGroupPage();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();
    }

}
