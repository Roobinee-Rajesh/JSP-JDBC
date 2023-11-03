package com.jsp_jdbc.model;

public class ToDo {
    private int id;
    private int userId;
    private String todo;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTodo() {
        return todo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }
}
