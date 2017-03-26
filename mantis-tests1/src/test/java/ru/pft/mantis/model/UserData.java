package ru.pft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Natalya on 26.03.2017.
 */

@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @Id
            @Column(name = "id")
    int id;

    @Column(name = "username")
    String user;

    @Column(name = "password")
    String password;

    @Column(name = "email")
    String email;

    public UserData withId(int id) { //сеттер для ID новой группы
        this.id = id;
        return this;
    }

    public UserData withUserName(String user) {
        this.user = user;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }
}
