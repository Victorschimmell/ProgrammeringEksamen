package org.openjfx.JavaChat;

import java.security.*;
import java.sql.*;

public class User {

    public int id;
    private String username;
    private String password;
    public int[] friends;

    public User(String username, String password) {

        this.username = username;
        this.password = password;
        //HEJSA

    }

    public int getID() {

        Connection conn = null;
        String query = null;

        try {

            conn = DriverManager.getConnection(MenuController.connectionString);
            query = "SELECT ID FROM Users WHERE Username = '" + getUsername() + "'";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                ResultSet rs = pstmt.executeQuery();

                id = rs.getInt(1);

            } catch (SQLException e) {
                System.out.println("FUCK2" + e);
            }

        } catch (Exception e) {
            System.out.println("FUCK");
        }

        return id;
    }

    public String getPassword() {
       return hashPassword(password);   
    }

    public String getUsername() {
        return username;
    }
    public static String hashPassword(String password_){

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update((password_).getBytes());

            byte[] byteList = md.digest();

            StringBuffer hashedValueBuffer = new StringBuffer();

            for (byte b : byteList)
                hashedValueBuffer.append(Integer.toHexString(b));

            password_ = hashedValueBuffer.toString();

        } catch (Exception e) {

            System.out.println("Exception with passwordhash: " + e.getMessage());
        }

        return password_;
    }

}
