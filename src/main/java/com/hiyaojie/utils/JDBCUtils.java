package com.hiyaojie.utils;

import java.sql.*;

/**
 * OracleJDBC的工具类
 */
public class JDBCUtils {

    static {
        try {
            String driver = "oracle.jdbc.OracleDriver";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private JDBCUtils(){
        throw new AssertionError();
    }

    // 获取数据库的连接
    public static Connection getConnection() {
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            String user = "scott";
            String password = "root";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // 释放连接
    public static void release(Connection conn, Statement stat, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                rs = null;
            }
        }

        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                stat = null;
            }
        }

        if (conn !=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conn = null;
            }
        }
    }
}
