package com.qst.dao;

import com.qst.bean.User;
import com.qst.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    /**
     * add by tim 20200704:Add
     *
     * @param user
     */
    public static void save(User user) {
        //获得用户列表主键的最大值
        int maxId = selectMaxUserId();
        maxId++;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO TB_USERS(USER_ID,USER_LOGNAME,USER_PWD,USER_REALNAME,USER_EMAIL,USER_ROLE,USER_STATE) " +
                "VALUES('" + maxId + "',?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getuserLogName());
            pstmt.setString(2, user.getUserPwd());
            pstmt.setString(3, user.getUserRealname());
            pstmt.setString(4, user.getUserEmail());
            pstmt.setInt(5, user.getUserRole());
            pstmt.setInt(6, user.getUserState());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
    }

    /**
     * add by tim 20200704:select by usrid
     *
     * @param uid
     * @return
     */
    public User selectById(int uid) {
        User u = new User();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT USER_ID,USER_LOGNAME,USER_REALNAME,USER_EMAIL,USER_ROLE,USER_STATE "
                + "FROM TB_USERS WHERE USER_ID = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, uid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                u.setUserId(rs.getInt(1));
                u.setuserLogName(rs.getString(2));
                u.setUserRealname(rs.getString(3));
                u.setUserEmail(rs.getString(4));
                u.setUserRole(rs.getInt(5));
                u.setUserState(rs.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(rs, pstmt, conn);
        }
        return u;
    }

    /**
     * add by tim 20200704:select all usrs
     *
     * @return
     */
    public List<User> list() {
        List<User> list = new ArrayList<User>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT USER_ID,USER_LOGNAME,USER_REALNAME,USER_EMAIL,USER_ROLE,USER_STATE "
                + "FROM TB_USERS";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt(1));
                u.setuserLogName(rs.getString(2));
                u.setUserRealname(rs.getString(3));
                u.setUserEmail(rs.getString(4));
                u.setUserRole(rs.getInt(5));
                u.setUserState(rs.getInt(6));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * add by tim 20200704:update usr
     *
     * @param user
     */
    public void update(User user) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String sql = "UPDATE TB_USERS SET USER_REALNAME = ?,USER_EMAIL = ?,USER_ROLE = ?,USER_STATE = ? "
                + "WHERE USER_ID = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserRealname());
            pstmt.setString(2, user.getUserEmail());
            pstmt.setInt(3, user.getUserRole());
            pstmt.setInt(4, user.getUserState());
            pstmt.setInt(5, user.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
    }

    /**
     * add by tim 20200704:login
     *
     * @param userLogName
     * @param userPwd
     * @return
     */
    public User login(String userLogName, String userPwd) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        User u = null;
        String sql = "SELECT USER_ID,USER_REALNAME,USER_EMAIL,USER_ROLE,USER_STATE FROM TB_USERS WHERE USER_LOGNAME=? AND USER_PWD=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userLogName);
            pstmt.setString(2, userPwd);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                u = new User();
                u.setUserId(rs.getInt(1));
                u.setUserRealname(rs.getString(2));
                u.setUserEmail(rs.getString(3));
                u.setUserRole(rs.getInt(4));
                u.setUserState(rs.getInt(5));
                u.setuserLogName(userLogName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
        return u;
    }

    /**
     * add by tim 20200704:delete user
     *
     * @param uid
     * @return
     */
    public static void deleteUsr(int uid) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            String sql = "DELETE FROM tb_users WHERE USER_ID=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, uid);
            pstmt.executeUpdate();
            DBUtil.closeJDBC(null, pstmt, conn);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }


    /**
     * 查询所需分页的总记录数
     * add by Miao 20200708
     *
     * @return
     */
    public int getRecordCount() {
        int recordCount = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) FROM tb_users";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next())
                recordCount = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(rs, pstmt, conn);
        }
        return recordCount;
    }

    /**
     * 查询某一页的全部记录
     * add by Miao 20200708
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<User> getUserPageList(int pageNo, int pageSize) {
        List<User> result = new ArrayList<User>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ("
                + "SELECT A.*,A.USER_ID ID FROM("
                + "SELECT USER_ID,USER_LOGNAME,"
                + "USER_REALNAME,USER_EMAIL,"
                + "USER_ROLE,USER_STATE FROM TB_USERS)A "
                + "WHERE A.USER_ID<=?)B " +
                "WHERE B.ID >?";
        ;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pageNo * pageSize);
            pstmt.setInt(2, (pageNo - 1) * pageSize);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setuserLogName(rs.getString(2));
                user.setUserRealname(rs.getString(3));
                user.setUserEmail(rs.getString(4));
                user.setUserRole(rs.getInt(5));
                user.setUserState(rs.getInt(6));
                result.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(rs, pstmt, conn);
        }
        return result;
    }

    /**
     * 获得用户列表主键的最大值
     *
     * @return
     */
    public static int selectMaxUserId() {
        int maxUserId = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(USER_ID) FROM TB_USERS";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                maxUserId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maxUserId;
    }

//    public static void main(String[] args) {
//        UserDao dao=new UserDao();
//        int a=dao.deleteUsr(10);
//        if(a!=0) {
//            System.out.println("删除成功！");
//        }else{
//            System.out.println("删除失败");
//        }
//    }

}
