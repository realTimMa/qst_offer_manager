package com.qst.bean;

public class User {

    private int userId;
    private String userLogName;
    private String userPwd;
    private String userRealname;
    private String userEmail;
    private int userRole;
    private int userState;

    public User() {
    }

    public User(String userLogName, String userPwd, String userRealname, String userEmail, int userRole, int userState) {
        this.userLogName = userLogName;
        this.userPwd = userPwd;
        this.userRealname = userRealname;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.userState = userState;
    }


    public User(int userId, String userLogName, String userPwd, String userRealname, String userEmail, int userRole, int userState) {
        this.userId = userId;
        this.userLogName = userLogName;
        this.userPwd = userPwd;
        this.userRealname = userRealname;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.userState = userState;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getuserLogName() {
        return userLogName;
    }

    public void setuserLogName(String userLogName) {
        this.userLogName = userLogName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

}
