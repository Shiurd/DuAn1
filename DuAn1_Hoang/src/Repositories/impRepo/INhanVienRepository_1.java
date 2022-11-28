/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impRepo;

import DomainModels.NhanVien1;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public interface INhanVienRepository_1 {

    ArrayList<NhanVien1> getAllNhanViens();

    NhanVien1 mappingNhanVien(ResultSet rs);
}
