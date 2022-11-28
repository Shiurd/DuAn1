/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impRepo;

import DomainModels.NhanVien1;
import ViewModels.QLNhanVien;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public interface INhanVienRepository {

    ArrayList<NhanVien1> getAllNhanViens();

    NhanVien1 mappingNhanVien(ResultSet rs);

    int themNhanVien(QLNhanVien qlnv);

    int suaNhanVien(String maNV, QLNhanVien qlnv);

    int xoaNhanVien(String maNV);

}
