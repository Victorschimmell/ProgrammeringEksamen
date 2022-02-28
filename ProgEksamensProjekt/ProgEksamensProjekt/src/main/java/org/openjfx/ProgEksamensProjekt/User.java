package org.openjfx.ProgEksamensProjekt;

import java.security.*;
import java.util.Arrays;
import java.sql.*;

public class User {

    public int id;
    private String username;
    private String password;
    public int[] friends;

    public User(String username, String password, String savedFriends) {

        this.username = username;
        this.password = password;
        //HEJSA

        String[] string = savedFriends.replaceAll("\\[", "")
                .replaceAll("]", "")
                .split(",");

        this.friends = new int[string.length];
        for (int i = 0; i < string.length; i++) {
            friends[i] = Integer.valueOf(string[i]);
        }
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

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(password.getBytes());

            byte[] byteList = md.digest();

            StringBuffer hashedValueBuffer = new StringBuffer();

            for (byte b : byteList)
                hashedValueBuffer.append(Integer.toHexString(b));

            System.out.println("real password: " + password);

            System.out.println("Sha-256: " + hashedValueBuffer.toString());

            return hashedValueBuffer.toString();

        } catch (Exception e) {

            System.out.println("Exception with password: " + e.getMessage());
        }

        return password;
    }

    public String getUsername() {
        return username;
    }

    public int[] getFriends() {

        System.out.println("FriendList:" + Arrays.toString(friends));

        return friends;
    }

    public String getFriendsString() {
        return Arrays.toString(friends);
    }

}
