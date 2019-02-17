package com.hiyaojie.oracle;

import com.hiyaojie.utils.JDBCUtils;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class TestProcedure {
    @Test
    public void TestProcedure() {
        String sql = "{call querystubyid(?,?,?)}";

        Connection conn = null;
        CallableStatement call = null;

        try {
            conn = JDBCUtils.getConnection();
            call = conn.prepareCall(sql);

            call.setInt(1, 2);
            call.registerOutParameter(2, OracleTypes.VARCHAR);
            call.registerOutParameter(3, OracleTypes.CHAR);

            call.execute();
            System.out.println("sname:" + call.getString(2));
            System.out.println("ssex:" + call.getString(3));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, call, null);
        }
    }

    /**
     * 调用包中的存储过程，可以返回一个自定义的游标；
     * 通过游标取出结果集；
     */
    @Test
    public void TestPackage() {
        String sql = "{call querystu.querystubysex(?,?)}";

        Connection conn = null;
        CallableStatement call = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            call = conn.prepareCall(sql);
            call.setString(1, "M");
            call.registerOutParameter(2, OracleTypes.CURSOR);
            call.execute();
            rs = ((OracleCallableStatement) call).getCursor(2);

            while (rs.next()) {
                int sid = rs.getInt("id");
                String sname = rs.getString("name");
                System.out.println("id= " + sid + "\tsname = " + sname);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, call, rs);
        }
    }
}
