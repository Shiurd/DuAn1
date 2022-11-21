/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Repositories.impRepo.IKhoRepository;
import DomainModels.Kho.ToanBoKho;
import DomainModels.Kho.BangKho;
import DomainModels.Kho.Bia;
import DomainModels.Kho.TongSanPhamKho;
import Utilities.DBConnection;
import Utilities.DBConnectionGiang;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author Hacke
 */
public class KhoRepository implements IKhoRepository {
    private DBConnectionGiang dbConnection;
    
    final String Select_All_Sql = "select A.MaKho, A.TenKho,C.MaBia,C.Ten,A.DiaChi,B.SoLuong from Kho A join KhoChiTiet B on A.MaKho = B.MaKho join Bia C on B.MaBia = C.MaBia order by A.MaKho";
    final String Select_Total_Beer = "SELECT MaKho, SUM(SoLuong) FROM KhoChiTiet  GROUP BY MaKho";
    final String Select_Kho = "SELECT * FROM KHO";
    final String Select_bia = "Select MaBia From Bia";
    final String insert_into_KhoBia = "Insert into Kho (MaKho,DiaChi,TenKho) values(?,?,?)";
    final String update_kho = "update Kho set DiaChi = ? , TenKho = ? where MaKho = ?";
    final String delete_kho = "delete from Kho where Makho = ?";
    final String select_in_ToanBoKho = "select A.MaKho, A.TenKho,C.MaBia,C.Ten,A.DiaChi,B.SoLuong from Kho A join KhoChiTiet B on A.MaKho = B.MaKho join Bia C on B.MaBia = C.MaBia where A.MaKho = ?";
    public KhoRepository(){
        dbConnection = new DBConnectionGiang();
    } 
    @Override
    public List<ToanBoKho> findByMaKhoInToanBoKho(String id){
        return getSelectSql(select_in_ToanBoKho, id);
    }
    
    @Override
    public void updateDataKho(BangKho model){
        dbConnection.executeUpdate(update_kho, model.getDiaChiString(),model.getTenKhoString(),model.getMaKhoString());
    }
    
    @Override
    public String deleteKho(String maKho){
        DBConnection.Excute(delete_kho, maKho);
        return maKho;
    }
    
    @Override
    public BangKho saveBangKho(BangKho bangKho){
        DBConnection.Excute(insert_into_KhoBia, bangKho.getMaKhoString(),bangKho.getDiaChiString(),bangKho.getTenKhoString());
        return bangKho;
    }
    @Override
    public List<ToanBoKho> findAll(){
        return getSelectSql(Select_All_Sql);
    }
    
    @Override
    public List<TongSanPhamKho> totalBeer(){
        return getSelectSqlKho(Select_Total_Beer);
    }
    @Override
    public List<BangKho> bangKho(){
        return getSelectsqlTongKho(Select_Kho);
    }
    @Override
    public List<Bia> listBiaKho() {
        try {
            List<Bia> list = new ArrayList<>();
            ResultSet rs = DBConnection.getDataFromQuery(Select_Kho);
            while (rs.next()) {                
                list.add(new Bia(rs.getString(1)));
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
    
    public List<BangKho> getSelectsqlTongKho(String sql, Object... args){
        try {
            List<BangKho> lstcategories = new ArrayList<>();
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
        lstcategories.add(new BangKho(rs.getString(1),rs.getString(2),rs.getString(3)));
      }
        return lstcategories;
        } catch (Exception e) {
          throw new RuntimeException();
        }
    }
    
    public List<TongSanPhamKho> getSelectSqlKho(String sql, Object... args) {
    try {
      List<TongSanPhamKho> lstcategories = new ArrayList<>();
      ResultSet rs = DBConnection.getDataFromQuery(sql, args);
      while (rs.next()) {
        lstcategories.add(new TongSanPhamKho(rs.getString(1),rs.getInt(2)));
      }
      return lstcategories;
    } catch (SQLException ex) {
      throw new RuntimeException();
    }
  }
    private List<ToanBoKho> getSelectSql(String sql, Object... args) {
    try {
      List<ToanBoKho> lstcategories = new ArrayList<>();
      ResultSet rs = DBConnection.getDataFromQuery(sql, args);
      while (rs.next()) {
        lstcategories.add(new ToanBoKho(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6)));
      }
      return lstcategories;
    } catch (SQLException ex) {
      throw new RuntimeException();
    }
  }
//    private List<Kho> getSelectSql(String sql, Object... args) {
//    try {
//      List<Kho> lstcategories = new ArrayList<>();
//      ResultSet rs = DBConnection.getDataFromQuery(sql, args);
//      while (rs.next()) {
//        lstcategories.add(new Kho(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6)));
//      }
//      return lstcategories;
//    } catch (SQLException ex) {
//      throw new RuntimeException();
//    }
//  }

}
