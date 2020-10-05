package com.qst.util;

import java.sql.*;
import com.qst.util.Config;
/**
 * add by Tim Ma 2020-07-03: pls use mysql-connector-java 6+
 */

public class DBUtil {
    final static String driver = Config.getValue("driver");
    final static String url = Config.getValue("url");
    final static String user = Config.getValue("username");
    final static String password = Config.getValue("password");

    //加载数据库驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException  e) {
            e.printStackTrace();
        }


        return conn;
    }



    public static void closeJDBC(ResultSet rs, Statement stmt,Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
