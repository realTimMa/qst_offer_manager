package com.qst.servlet;

import com.qst.bean.User;
import com.qst.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/UseAddServlet")
public class UserAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserAddServlet() {
        super();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userLogName = request.getParameter("userLogName");
        String userPwd = request.getParameter("userPwd");
        String userRealname = request.getParameter("userRealname");
        String userEmail = request.getParameter("userEmail");
        int userRole = (request.getParameter("userRole") == null) ? 3 : Integer.parseInt(request.getParameter("userRole"));
        int userState = (request.getParameter("userState") == null) ? 1 : Integer.parseInt(request.getParameter("userState"));
        User user = new User(userLogName, userPwd, userRealname, userEmail, userRole, userState);
        UserDao userDao = new UserDao();
        userDao.save(user);
        //添加成功后重定向到userList界面
        response.sendRedirect("manage/userList.jsp");
    }

}
