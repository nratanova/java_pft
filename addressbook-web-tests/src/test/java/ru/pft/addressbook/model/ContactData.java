package ru.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String nickName;
  private final String homePhone;
  private final String mobPhone;
  private final String email;
  private String group;

  public ContactData(String firstName, String lastName, String nickName, String homePhone, String mobPhone, String email, String group) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickName = nickName;
    this.homePhone = homePhone;
    this.mobPhone = mobPhone;
    this.email = email;
    this.group = group;
  }



  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickName() {
    return nickName;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobPhone() {
    return mobPhone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  @Override
  //Метод преобразования в строку
  public String toString() {
    return "ContactData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  //Метод сравнения объектов типа Contact Data по атрибутам
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }
}
