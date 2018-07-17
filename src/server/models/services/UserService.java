package server.models.services;

import server.Console;
import server.DatabaseConnection;
import server.models.Users;

import javax.ws.rs.core.Cookie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public static String validateSessionCookie(Cookie sessionCookie) {
        if (sessionCookie != null) {
            String token = sessionCookie.getValue();
            String result = UserService.selectAllInto(Users.users);
            if (result.equals("OK")) {
                for (Users u : Users.users) {
                    if (u.getSessionToken().equals(token)) {
                        Console.log("Valid session token received.");
                        return u.getUsername();
                    }
                }
            }
        }
        Console.log("Error: Invalid user session token");
        return null;
    }

    public static String selectAllInto(List<Users> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT Id, Username, PasswordHash, SessionToken FROM Users"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Users(results.getInt("Id"), results.getString("Username"), results.getString("PasswordHash"), results.getString("SessionToken")));


                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'Users' table: " + resultsException.getMessage();

            Console.log(error);
            return error;
        }
        return "OK";
    }

    public static Users selectById(int id) {
        Users result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT Id, Username, PasswordHash, SessionToken FROM Users WHERE Id = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new Users(results.getInt("Id"), results.getString("Username"), results.getString("PasswordHash"), results.getString("SessionToken"));


                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'Users' table: " + resultsException.getMessage();

            Console.log(error);
        }
        return result;
    }

    public static String insert(Users itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO Users (Id, Username, PasswordHash, SessionToken) VALUES (?, ?, ?, ?)"
            );
            statement.setInt(1, itemToSave.getUserId());
            statement.setString(2, itemToSave.getUsername());
            statement.setString(3, itemToSave.getHashPassword());
            statement.setString(4, itemToSave.getSessionToken());
            Console.log(itemToSave.getHashPassword());





            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'Users' table: " + resultsException.getMessage();

            Console.log(error);
            return error;
        }
    }

    public static String update(Users itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE Users SET Username = ?, Password = ?, SessionToken = ? WHERE Id = ?"
            );
            statement.setString(1, itemToSave.getUsername());
            statement.setString(2, itemToSave.getHashPassword());
            statement.setString(3, itemToSave.getSessionToken());






            statement.setInt(4, itemToSave.getUserId());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'Users' table: " + resultsException.getMessage();

            Console.log(error);
            return error;
        }
    }

    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM Users WHERE Id = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'Users' table: " + resultsException.getMessage();

            Console.log(error);
            return error;
        }
    }



}
