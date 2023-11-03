package com.jsp_jdbc.dao;

import com.jsp_jdbc.db.DBConnection;
import com.jsp_jdbc.model.ToDo;
import com.jsp_jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static Connection connection;

    private static String LOGIN_QUERY="SELECT id, username, password FROM auth WHERE username=? and password=?;";
    private static String ADD_USER="INSERT INTO auth(userName,password) VALUES(?,?)";
    public UserDao(){
        connection= DBConnection.getConnection();
    }

    public static User loginUser(String userName, String password){
        User user=null;
        try{
            PreparedStatement statement=connection.prepareStatement(LOGIN_QUERY);
            statement.setString(1,userName);
            statement.setString(2,password);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user=new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void addUser(String name, String password) throws SQLException {
        PreparedStatement statement=connection.prepareStatement(ADD_USER);
        statement.setString(1,name);
        statement.setString(2,password);
        statement.executeUpdate();
        }

    }
