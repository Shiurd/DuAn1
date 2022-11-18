/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.implRepo;

import DomainModels.NhanVien;
import Repositories.INhanVienRepository;
import Utilities.DBConnection;
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
    private DBConnection dbConnection;

    public NhanVienRepository() {
        lstNhanViens = new ArrayList<>();
        dbConnection = new DBConnection();
    }

    @Override
    public ArrayList<NhanVien> getAllNhanViens() {
        ArrayList<NhanVien> lst = new ArrayList<>();
        try {
            String sql = "Select * from NhanVien";
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

                return new NhanVien(ma, ten, gioiTinh, ngaySinh, diaChi, sdt);
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienRepository.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return null;
    }

}
