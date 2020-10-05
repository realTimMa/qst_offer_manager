<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.qst.dao.UserDao" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="user" class="com.qst.bean.User"></jsp:useBean>
<jsp:setProperty property="*" name="user"/>
<%
    UserDao userDao = new UserDao();
    userDao.update(user);
    response.sendRedirect("userList.jsp");
%>