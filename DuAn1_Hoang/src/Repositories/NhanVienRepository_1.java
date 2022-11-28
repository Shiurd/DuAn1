/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.NhanVien1;
import Utilities.DBConnectionGiang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Repositories.impRepo.INhanVienRepository_1;

/**
 *
 * @author Admin
 */
public class NhanVienRepository_1 implements INhanVienRepository_1 {

    private ArrayList<NhanVien1> lstNhanViens;
    private DBConnectionGiang dbConnection;

    public NhanVienRepository_1() {
        lstNhanViens = new ArrayList<>();
        dbConnection = new DBConnectionGiang();
    }

    @Override
    public ArrayList<NhanVien1> getAllNhanViens() {
        ArrayList<NhanVien1> lst = new ArrayList<>();
        try {
            String sql = "  SELECT MaNV,TenNv,GioiTinh,NgaySinh,DiaChi,Sdt,CMND,Email,GhiChu,Anh From NhanVien";
            ResultSet rs = dbConnection.executeQuery(sql);
            while (rs.next()) {
                NhanVien1 nv = mappingNhanVien(rs);
                if (nv != null) {
                    lst.add(nv);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienRepository_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    @Override
    public NhanVien1 mappingNhanVien(ResultSet rs) {
        if (rs != null) {
            try {
                String ma = rs.getString("MaNV");
                String ten = rs.getString("TenNv");
                String gioiTinh = rs.getString("GioiTinh");
                String ngaySinh = rs.getString("NgaySinh");
                String diaChi = rs.getString("DiaChi");
                String sdt = rs.getString("Sdt");
                String cmnd = rs.getString("CMND");
                String Email = rs.getString("Email");
                String GhiChu = rs.getString("GhiChu");
                String Anh = rs.getString("Anh");

                return new NhanVien1(ma, ten, gioiTinh, ngaySinh, diaChi, sdt,cmnd,Email,GhiChu,Anh);
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienRepository_1.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return null;
    }

}
