package com.jsp_jdbc.controller;

import com.jsp_jdbc.dao.UserDao;
import com.jsp_jdbc.db.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterController extends HttpServlet {
    public RegisterController() {
        DBConnection.getConnection();
        UserDao userDao=new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        String confirmPassword=req.getParameter("confirmPassword");

        if(confirmPassword.equals(password)){
            try {
                UserDao.addUser(name,password);
                RequestDispatcher dispatcher=req.getRequestDispatcher("login");
                dispatcher.forward(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            req.setAttribute("error", true);
            RequestDispatcher dispatcher=req.getRequestDispatcher("register.jsp");
            dispatcher.forward(req,resp);
        }
    }
}
