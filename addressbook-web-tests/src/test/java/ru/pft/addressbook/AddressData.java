package ru.pft.addressbook;

public class AddressData {
  private final String firstName;
  private final String lastName;
  private final String nickName;
  private final String homePhone;
  private final String mobPhone;
  private final String email;

  public AddressData(String firstName, String lastName, String nickName, String homePhone, String mobPhone, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickName = nickName;
    this.homePhone = homePhone;
    this.mobPhone = mobPhone;
    this.email = email;
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
}
