package ru.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsXML() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map((g)-> new Object[] {g} ).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType()); //То же самое что и List<GroupData>.class
      return groups.stream().map((g)-> new Object[] {g} ).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validGroupsJson")
  public void testGroupCreation(GroupData group) {
      app.goTo().groupPage();
      Groups before = app.db().groups();
      app.group().create(group);
      assertThat(app.group().count(), equalTo(before.size() + 1)); //Хэширование, предварительная проверка,
      // при помощи более быстрого способа
      Groups after = app.db().groups();
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
