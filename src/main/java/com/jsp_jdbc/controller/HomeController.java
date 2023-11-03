package com.jsp_jdbc.controller;

import com.jsp_jdbc.dao.ToDoDao;
import com.jsp_jdbc.model.ToDo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeController extends HttpServlet {
    private ToDoDao toDoDao;
    public HomeController() {
        toDoDao=new ToDoDao();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        if (id != null) {
            toDoDao.deleteTodo(Integer.parseInt(id));
        }

        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
        String userId = req.getSession().getAttribute("id").toString();
//        System.out.println(userId);

        String item = req.getParameter("todo");
//        System.out.println(item);
        if (item != null && item.trim().length() > 0) {
            toDoDao.addTodo(item, Integer.parseInt(userId));
        }

        List<ToDo> todos = toDoDao.selectToDo(Integer.parseInt(userId));
        req.setAttribute("todos", todos);

        dispatcher.forward(req, resp);

    }
}
