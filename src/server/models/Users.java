package server.models;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Users {
    private int userId;
    private String username;
    private String hashPassword;
    private String sessionToken;

    public Users(int userId, String username, String hashPassword, String sessionToken) {
        this.userId = userId;
        this.username = username;
        this.hashPassword = hashPassword;
        this.sessionToken = sessionToken;
    }

    public static int nextId() {
        int id = 0;
        for (Users u: users) {
            if (u.getUserId() > id) {
                id = u.getUserId();
            }
        }
        return id + 1;
    }

    public static String passwordHash(String password){
        try {
            MessageDigest hasher = MessageDigest.getInstance("MD5");
            hasher.update(password.getBytes());
            return DatatypeConverter.printHexBinary(hasher.digest()).toUpperCase();
        } catch (NoSuchAlgorithmException nsae) {
            return nsae.getMessage();
        }
    }

    public static ArrayList<Users> users = new ArrayList<>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
