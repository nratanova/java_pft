package ru.pft.addressbook.appmager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;

import java.security.acl.Group;
import java.util.List;

/**
 * Created by Наташа on 02.02.2017.
 */
public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  //Метод выбора группы по идентификатору (в качестве параметра передается идентификатор)
  private void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCach = null; //Очистить кэш
    returnToGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCach = null; //Очистить кэш
    returnToGroupPage();
  }

  public void delete(GroupData deleteGroup) {
    selectGroupById(deleteGroup.getId());
    deleteSelectedGroups();
    groupCach = null; //Очистить кэш
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Groups groupCach = null;

  //Вспомогательный метод, который сразу возвращает множество, а не список
  public Groups all() {

    if (groupCach != null) {
      return new Groups(groupCach);//вернуть копию кэша
    }

    Groups groupCach = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText(); //Вытащить наименование группы
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")); //вытащить id чекбокса
      groupCach.add(new GroupData().withId(id).withName(name)); //Добавить найденную группу в список групп
    }
    return new Groups(groupCach);//Возвращаем копию кэша
  }

}