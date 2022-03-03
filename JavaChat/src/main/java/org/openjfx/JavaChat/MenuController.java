package org.openjfx.JavaChat;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.*;

public class MenuController {

    public static User currentUser;
    public final static String connectionString = "jdbc:sqlite:src/Database.db";

    @FXML
    private TextField cUsername = new TextField(), LUsername = new TextField();
    @FXML
    private PasswordField cPassword = new PasswordField(), cPassword2 = new PasswordField(),
            Password = new PasswordField();
    @FXML
    private Text accountconfirm = new Text(), accountconfirm1 = new Text();

    @FXML
    private void login() throws SQLException, Exception {
        if (verifyLogin(LUsername.getText(), User.hashPassword(Password.getText())) == true) {
            currentUser = new User(LUsername.getText(), Password.getText());
            App.setRoot("Kontakter");
        } else {
            accountconfirm1.setText("Incorrect username or password");
        }
    }

    @FXML
    private void createAccount() throws SQLException, Exception {
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        String query = null;
        if (cPassword.getText().equals(cPassword2.getText()) && cUsername.getText().length() > 0) {
            try {

                conn = DriverManager.getConnection(connectionString);
                query = "INSERT INTO Users(Username, Password) VALUES('" + cUsername.getText() + "','"
                        + User.hashPassword(cPassword.getText()) + "');";

                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.executeUpdate();
                    System.out.println("User added to database");
                    accountconfirm.setText("New account created");
                } catch (SQLException e) {
                    accountconfirm.setText("Username already used");
                    System.out.println("Username already used");
                }
            } catch (Exception e) {
                accountconfirm.setText("Database connection error");
                System.out.println("Something gone bad");

            } finally {
                conn.close();

            }
        } else {
            accountconfirm.setText("Passwords don't match, or no username specified");
        }
    }

    public boolean verifyLogin(String name, String password) throws SQLException, Exception {
        Connection conn = null;
        String query = null;

        try {

            conn = DriverManager.getConnection(connectionString);
            query = "SELECT* FROM Users WHERE Username ='" + name + "' AND Password ='" + password + "'";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                ResultSet rs = pstmt.executeQuery();
                if (name.length() > 0) {
                    if (rs.getString("Username").equals(name) && rs.getString("Password").equals(password)) {
                        System.out.println("Found user in databse");
                        return true;
                    }
                    System.out.println("Username or password incorrect");
                    accountconfirm1.setText("Username or password incorrect");
                    return false;
                } else {
                    System.out.println("No username specified");
                    accountconfirm1.setText("No username specified");
                    return false;
                }

            } catch (SQLException e) {
                System.out.println("Username or password incorrect");
                accountconfirm1.setText("Username or password incorrect");
                return false;

            }
        } catch (Exception e) {
            accountconfirm1.setText("Connection failure to database");
            System.out.println("Connection failure to database");

            return false;
        } finally {
            conn.close();

        }

    }

}
