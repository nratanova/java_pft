package ru.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Наташа on 15.03.2017.
 */
public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCSV(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXML(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJSON(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format" + format);
    }
  }

  private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();//Пропуск
    //всех полей, которые не поомечены анотацией @Expose (для gson)
    String json = gson.toJson(contacts);
    try(Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try(Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  //Запись в файл
  private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getMobPhone(),
                contact.getGroup(), contact.getEmail()));
      }
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData()
              .withFirstName(String.format("Nat %s", i))
              .withLastName(String.format("Rat %s", i))
              .withGroup(String.format("Test", i))
              .withEmail(String.format("Nat%s@mail.ru", i))
              .withMobPhone(String.format("8905909909%s", i)));
    }
    return contacts;
  }
}
