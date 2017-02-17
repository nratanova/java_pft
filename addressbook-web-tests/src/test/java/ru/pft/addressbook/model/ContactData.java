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
}
