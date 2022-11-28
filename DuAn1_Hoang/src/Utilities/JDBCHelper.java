/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import Repositories.DBContext;
import java.sql.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCHelper {

    public static ResultSet selectTongQuat(String sql, Object... paramas) {
        PreparedStatement pstm = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            try {
                con = DBContext.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(JDBCHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            pstm = con.prepareStatement(sql);
            for (int i = 0; i < paramas.length; i++) {
                pstm.setObject(i + 1, paramas[i]);
            }
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            close(pstm, con, rs);
            return null;
        }
    }

    public static int updateTongQuat(String sql, Object... paramas) {
        PreparedStatement pstm = null;
        Connection con = null;
        try {
            try {
                con = DBContext.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(JDBCHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            pstm = con.prepareStatement(sql);
            for (int i = 0; i < paramas.length; i++) {
                pstm.setObject(i + 1, paramas[i]);
            }
            return pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            close(pstm, con);
            return 0;
        }
    }

    public static void close(PreparedStatement pstm, Connection con) {
        try {
            pstm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement pstm, Connection con, ResultSet rs) {
        try {
            close(pstm, con);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
