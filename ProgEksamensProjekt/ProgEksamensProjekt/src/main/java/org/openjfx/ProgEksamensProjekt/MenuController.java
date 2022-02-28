package org.openjfx.ProgEksamensProjekt;

import javafx.fxml.FXML;
import java.sql.*;

public class MenuController {

    public User currentUser;
    public final static String connectionString = "jdbc:sqlite:src/Database.db";

    @FXML
    private void switchToKontakter() throws SQLException, Exception {
        currentUser = new User("Navn7", "Kode", "[1,2]");

        saveUser(currentUser);

        System.out.println(currentUser.getID());

        App.setRoot("Kontakter");

    }

    private void saveUser(User u) throws SQLException, Exception {
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        String query = null;

        try {

            conn = DriverManager.getConnection(connectionString);
            query = "INSERT INTO Users(Username, Password, Friends) VALUES('" + u.getUsername() + "','" + u.getPassword() + "','" + u.getFriendsString() +"')";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.executeUpdate();
            } catch (SQLException e) {
                // TODO: handle exception
                System.out.println("Username already used");
            }
        } catch (Exception e) {
            System.out.println("Something gone bad");

        } finally {
            conn.close();

        }

    }
}
