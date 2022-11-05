package service;

import exceptions.EmailNotUniqueException;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

public class MysqlService {
    private Connection connection;

    public MysqlService() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connectToServer(){
        try {
            // jdbc:mysql://sql11.freemysqlhosting.net:3306/?user=sql11527682
            // jdbc:mysql://localhost:3306/rezsikalkulator?serverTimezone=UTC
            connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7544427", "sql7544427","gINvQ4mWJZ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void registration(String username, String password, String email) throws EmailNotUniqueException {
        String encodedPassword = DigestUtils.sha1Hex(password);
        String query = "INSERT INTO `users` (`username`, `email`, `passwords`) VALUES ('" + username + "', '" + email + "', '" + encodedPassword + "');";
        Statement statement = createStatement();

        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new EmailNotUniqueException();
            }
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
        }
    }

    public boolean login(String username, String password) {
        String encodedPassword = DigestUtils.sha1Hex(password);
        String sql = "SELECT * FROM users WHERE username LIKE '"+ username +"' AND passwords LIKE '" + encodedPassword + "';";
        Statement statement = createStatement();
        ResultSet resultSet;

        try {
            resultSet = statement.executeQuery(sql);
            resultSet.next();

            AuthenticationService authenticationService = AuthenticationService.getInstance();
            authenticationService.setUserId(resultSet.getInt("id"));
            authenticationService.setLoggedIn(true);
            authenticationService.setUsername(username);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
            // throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
        }
    }

    public Statement createStatement() {
        try {
            return connection.createStatement();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}