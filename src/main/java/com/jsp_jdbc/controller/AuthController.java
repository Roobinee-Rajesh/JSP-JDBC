package com.jsp_jdbc.controller;

import com.jsp_jdbc.dao.UserDao;
import com.jsp_jdbc.db.DBConnection;
import com.jsp_jdbc.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthController extends HttpServlet {
    public AuthController(){
        DBConnection.getConnection();
        UserDao userDao=new UserDao();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("post");


        String userName=req.getParameter("userName");
        String password=req.getParameter("password");
        User loggedInUser = UserDao.loginUser(userName,password);

        if(loggedInUser!=null){
            HttpSession session=req.getSession();
            session.setAttribute("id",loggedInUser.getId());
            RequestDispatcher dispatcher=req.getRequestDispatcher("home");
            dispatcher.forward(req,resp);
        }
        else {
            req.setAttribute("error", true);
            RequestDispatcher dispatcher=req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req,resp);
        }

    }
}
