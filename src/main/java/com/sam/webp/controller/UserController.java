package com.sam.webp.controller;

import com.sam.webp.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    private String jdbcURL = "jdbc:mysql://localhost:3306/webp?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "BeerLight";

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + " (name, email, country) VALUES " + " (?,?,?) ;";
    private static final String SELECT_USER_BY_ID = "SELECT id, name, email, country FROM users WHERE id =? ;";
    private static final String SELECT_ALL_USERS = "select * from users ;";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id =? ;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";

    public UserController() {
    }

    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            System.out.println("Connection established");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    //Insert user
    public void insertUser(User user) throws SQLException, ClassNotFoundException {

        System.out.println("Running query: " + INSERT_USERS_SQL);

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

            System.out.println("Query successfull");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //select user by id
    public User selectUser(int id) throws ClassNotFoundException {
        User user = null;

        System.out.println("Running query: " + SELECT_USER_BY_ID);

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }

            System.out.println("Query successfull");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    //select all users
    public List<User> selectAllUsers() throws ClassNotFoundException {

        List<User> users = new ArrayList<>();

        System.out.println("Running query: " + SELECT_ALL_USERS);

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }

            System.out.println("Query successfull");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    //delete user
    public boolean deleteUser(int id) throws SQLException, ClassNotFoundException {
        boolean rowDeleted = false;

        System.out.println("Running query: " + DELETE_USER_SQL);

        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);) {
            
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }

        System.out.println("Query successfull");

        return rowDeleted;
    }

    //update user
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated = false;
        
        System.out.println("Running query: " + UPDATE_USERS_SQL);
        
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());
            rowUpdated = statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Query successfull");
        
        return rowUpdated;
    }

}
