/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sevices.impSevices;

import ViewModels.QLNhanVien;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface INhanVienService {

    ArrayList<QLNhanVien> fillListNhanViens();

    String themNhanVien(QLNhanVien qlnv);

    String suaNhanVien(String maNV, QLNhanVien qlnv);

    String xoaNhanVien(String maNV);
}
