package server.models;

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
