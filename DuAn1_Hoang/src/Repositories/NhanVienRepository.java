/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.NhanVien;
import Repositories.impRepo.INhanVienRepository;
import Utilities.DBConnectionGiang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class NhanVienRepository implements INhanVienRepository {

    private ArrayList<NhanVien> lstNhanViens;
    private DBConnectionGiang dbConnection;

    public NhanVienRepository() {
        lstNhanViens = new ArrayList<>();
        dbConnection = new DBConnectionGiang();
    }

    @Override
    public ArrayList<NhanVien> getAllNhanViens() {
        ArrayList<NhanVien> lst = new ArrayList<>();
        try {
            String sql = "  SELECT MaNV,TenNv,GioiTinh,NgaySinh,DiaChi,Sdt,CMND,Email,GhiChu,Anh From NhanVien";
            ResultSet rs = dbConnection.executeQuery(sql);
            while (rs.next()) {
                NhanVien nv = mappingNhanVien(rs);
                if (nv != null) {
                    lst.add(nv);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    @Override
    public NhanVien mappingNhanVien(ResultSet rs) {
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

                return new NhanVien(ma, ten, gioiTinh, ngaySinh, diaChi, sdt,cmnd,Email,GhiChu,Anh);
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienRepository.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return null;
    }

}
