package com.hiyaojie.oracle;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hiyaojie.utils.JDBCUtils;

public class TestSql {

    @Test
    public void testSelect() {
        String sql = "select id,name,sex from student where 1=0";
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, stat, rs);
        }
    }

    @Test
    public void testInsert() {

        String sql = "insert into student values(?,?,?)";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = JDBCUtils.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, 5);
            statement.setString(2, "chen");
            statement.setString(3, "M");
            statement.execute();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, statement, null);
        }

    }

    @Test
    public void testDel() {
        String sql = "delete from student where id > 4";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = JDBCUtils.getConnection();
            statement = conn.prepareStatement(sql);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, statement, null);
        }
    }

    @Test
    public void testUpdate() {
        String sql = "update student set name = 'tian' where id =5 ";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = JDBCUtils.getConnection();
            statement = conn.prepareStatement(sql);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, statement, null);
        }
    }


}
