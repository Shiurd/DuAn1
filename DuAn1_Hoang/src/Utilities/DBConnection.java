/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

/**
 *
 * @author Hacke
 */

import java.sql.*;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static String hostName = "SHIURD_PC";
    private static String acc = "shiurd";
    private static String pass = "123456";
    private static String dbName = "DuAn1";
    private static String connectionSql
            = "jdbc:sqlserver://" + hostName + ":1433;databaseName=" + dbName +";encrypt=true;trustServerCertificate=true;";
    private static String  driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection conn;
    
    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println("Lỗi Driver");
        }
    }
    
    //1.Mở kết nối
    public static Connection openDbConnection() {
        try {
            return DriverManager.getConnection(connectionSql, acc, pass);
        } catch (Exception e) {
            return null;
        }
    }
    
    //2.Thực thi truy vấn thêm, sửa, xóa
    public static int Excute(String sql, Object...args) {
        PreparedStatement pstm = getStmt(sql, args);
        try {
            try {
                return pstm.executeUpdate();
            } finally {
                pstm.close();
            }
        } catch (Exception e) {
            System.out.println("Lỗi tại Excute");
            return 0;
        }
    }
    
    //3.Trả lại 1 tập đối tượng
    public static ResultSet getDataFromQuery(String sql, Object...args) throws SQLException {
        PreparedStatement pstm = getStmt(sql, args);
         return pstm.executeQuery();
    }
    
    //4.Chuẩn bị câu truy vấn trước khi thực thi - Các varargs sử dụng dấu ba chấm sau kiểu dữ liệu
    public static PreparedStatement getStmt(String sql, Object...args) {
        try {
            conn = openDbConnection();
            PreparedStatement ps;
            //ps = conn.prepareCall(sql) Gọi stored procedure
            ps = conn.prepareStatement(sql);//Dùng để triển khai các câu lệnh truy vấn thường
            for (int i = 0;i < args.length; i++){
                ps.setObject(i + 1, args[i]);//Cộng các value sau câu truy vấn
            }
            return ps;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
