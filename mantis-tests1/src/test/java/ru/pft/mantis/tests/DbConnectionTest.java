package ru.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.pft.mantis.model.UserData;
import ru.pft.mantis.model.Users;

import java.sql.*;

/**
 * Created by Natalya on 22.03.2017.
 */
public class DbConnectionTest {

    @Test
    public void testDbConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=&" +
                    "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id, username, email from mantis_user_table");
            Users users = new Users();
            while (rs.next()) {
                users.add(new UserData().withId(rs.getInt("id"))
                        .withUserName(rs.getString("username"))
                        .withEmail(rs.getString("email")));
            }
            rs.close(); //закрыть запрос, больше не надо читать данные
            st.close(); //закрыть, больше не надо выполнять запросы
            conn.close(); //закрыть соединение с бд

            System.out.println(users);

            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
