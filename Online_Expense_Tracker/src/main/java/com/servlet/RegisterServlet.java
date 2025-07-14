package com.servlet;

import java.io.IOException;

import com.dao.UserDao;
import com.db.HibernateUtil;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userRegister")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  String fullName= req.getParameter("fullName");
	       String email=req.getParameter("email");
	       String password=req.getParameter("password");
	       String about=req.getParameter("about");
	       
	       User u=new User(fullName,email,password,about);
	      // System.out.println(u);
          UserDao dao=new UserDao(HibernateUtil.getSessionFactory());
          boolean f=dao.saveuser(u);
          HttpSession session=req.getSession();
          
          if(f) {
        	  session.setAttribute("msg", "Register successfully");
        	  resp.sendRedirect("register.jsp");
        	  //System.out.println("Register successfully");
          }else {
        	  session.setAttribute("msg", "Something wrong on server");
        	  //System.out.println("something wrong on server");
              resp.sendRedirect("register.jsp");
          }
	}

}
