package database;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends AbstractDAO implements GenericDAO {
    public UserDAO(DBAccess dbAccess) {
        super(dbAccess);
    }

    @Override
    public void storeOne(Object userType) {
        if (userType instanceof User) {
            User user = (User) userType;
            String sql = "INSERT INTO User(userName, password, role, firstName, prefix, lastName) Values(?,?,?,?,?,?);";
            try {
                setupPreparedStatementWithKey(sql);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getRole());
                preparedStatement.setString(4, user.getFirstName());
                preparedStatement.setString(5, user.getPrefix());
                preparedStatement.setString(6, user.getLastName());
                int key = executeInsertStatementWithKey();
                user.setUserID(key);
            } catch (SQLException error) {
                System.out.println("Deze gebruiker is niet opgeslagen");
            }
        }
    }

    @Override
    public User getOneById(int userId) {
        String sql = "SELECT * FROM User Where userID = ?";
        User user = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String firstName = resultSet.getString("firstName");
                String prefix = resultSet.getString("prefix");
                String lastName = resultSet.getString("lastName");
                user = new User(userName, password, role, firstName, prefix, lastName);
                user.setUserID(userId);
            } else {
                System.out.println("Gebruiker met dit gebruikersnummer bestaat niet");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public User getOneByUsername(String mpUsername) {
        String sql = "SELECT * FROM User WHERE userName = ?";
        User user = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, mpUsername);
            ResultSet resultSet = executeSelectStatement();
            System.out.println(resultSet);
            while (resultSet.next()) {
                int userId = resultSet.getInt("userID");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String firstName = resultSet.getString("initials");
                String prefix = resultSet.getString("prefix");
                String lastName = resultSet.getString("lastName");
                user = new User(userId, mpUsername, password, role, firstName, prefix, lastName);

            }
        } catch (SQLException error) {
            System.out.println("Deze gebruikersnaam is niet bekend");
        }
        return user;
    }


    @Override
    public ArrayList getAll() {
        String sql = "SELECT * FROM User";
        ArrayList<User> usersList = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            User user;
            while (resultSet.next()) {
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String firstName = resultSet.getString("firstName");
                String prefix = resultSet.getString("prefix");
                String lastName = resultSet.getString("lastName");
                user = new User(userName, password, role, firstName, prefix, lastName);
                user.setUserID(resultSet.getInt("userID"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return usersList;
    }

    @Override
    public void updateOne(Object mpUser) {
        if (mpUser instanceof User) {
            User user = (User) mpUser;
            String sql = "UPDATE Klant SET userName = ?,  password = ? role = ?, initials = ?, prefix = ?, achternaam = ?, telefoon = ? where klantnr = ?;";
            try {
                setupPreparedStatement(sql);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getRole());
                preparedStatement.setString(4, user.getFirstName());
                preparedStatement.setString(5, user.getPrefix());
                preparedStatement.setString(6, user.getLastName());
                executeManipulateStatement();
            } catch (SQLException e) {
                System.out.println("SQL error " + e.getMessage());
            }
        }
    }

    @Override
    public void deleteOne(Object mpUser) {
        if(mpUser instanceof User) {
            User user = (User) mpUser;
            String sql = "DELETE FROM User WHERE userID = ?";
            try {
                setupPreparedStatement(sql);
                preparedStatement.setInt(1, user.getUserID());
                executeManipulateStatement();
            } catch (SQLException error) {
                System.out.println("SQL error: " + error.getMessage());
            }
        }
    }
}
