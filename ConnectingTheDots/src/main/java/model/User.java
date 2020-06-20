package model;

import database.DBAccess;
import database.UserDAO;
import main.Main;

import java.util.Arrays;
import java.util.List;

public class User {
    public static DBAccess dbAccess = Main.getDBAccess();
    public static UserDAO userDAO = new UserDAO(dbAccess);

    private int userID;
    private String userName;
    private String password;
    private String role;
    private String firstName;
    private String prefix;
    private String lastName;
    public static List<String> roles = Arrays.asList("storyteller", "reader", "commenter");

    //all-args constructor
    public User(int userID, String userName, String password, String role, String firstName, String prefix, String lastName) {
        super();
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
    }

    //constructor to be used in DAO without ID
    public User(String userName, String password, String role, String firstName, String prefix, String lastName) {
        this(0, userName, password, role, firstName, prefix, lastName);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static List<String> getRoles() {
        return roles;
    }

    public static void setRoles(List<String> roles) {
        User.roles = roles;
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder("");
        resultString.append(userID + " ");
        resultString.append(firstName + " ");
        if (prefix != null) {
            resultString.append(prefix + " ");
        }
        resultString.append(lastName + " ");
        resultString.append("in the role of " + role);
        return resultString.toString();
    }
}
