package com.jsp_jdbc.dao;

import com.jsp_jdbc.db.DBConnection;
import com.jsp_jdbc.model.ToDo;
import com.jsp_jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToDoDao {
    private static Connection connection;
    String GET_ALL_TODO="SELECT id,userId,item FROM todo WHERE userId=? AND delete_flag=false";
    String SELECT_TODO="SELECT id,userId,item FROM todo WHERE id=? AND delete_flag=false";
    String INSERT_TODO="INSERT INTO todo(userId,item) VALUES (?,?)";
    String DELETE_TODO="UPDATE todo SET delete_flag=true WHERE id=?";
    public ToDoDao() {
        connection= DBConnection.getConnection();
    }

    public List<ToDo> selectToDo(int userId){
        List<ToDo> todos=new ArrayList<ToDo>() ;
        try{
            PreparedStatement statement=connection.prepareStatement(GET_ALL_TODO);
            statement.setInt(1,userId);


            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ToDo toDo=new ToDo();
                toDo.setId(rs.getInt("id"));
                toDo.setUserId(rs.getInt("userId"));
                toDo.setTodo(rs.getString("item"));
                todos.add(toDo);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    public ToDo getTodo(int id) {
        ToDo todo = null;
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_TODO);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                todo = new ToDo();
                todo.setId(Integer.parseInt(rs.getString("id")));
                todo.setTodo(rs.getString("todo"));
                todo.setUserId(rs.getInt("userId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;
    }

    public void addTodo(String todo, int userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODO);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, todo);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTodo(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_TODO);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
