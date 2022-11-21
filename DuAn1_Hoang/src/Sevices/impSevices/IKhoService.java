/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Sevices.impSevices;

import DomainModels.Kho.ToanBoKho;
import DomainModels.Kho.BangKho;
import DomainModels.Kho.Bia;
import DomainModels.Kho.TongSanPhamKho;
import ViewModels.Kho.DSBia;
import ViewModels.Kho.QLBangKho;
import ViewModels.Kho.QLToanBoKho;
import ViewModels.Kho.QLTongSPKho;
import java.util.List;

/**
 *
 * @author Hacke
 */
 public interface IKhoService {
     List<QLToanBoKho> getAllKho();
     
     List<QLTongSPKho> getTongSPKho();
     
     List<QLBangKho> getBangKho();
     
     List<DSBia> getBiaKho();
     
     QLBangKho createnewKho(QLBangKho qLBangKho);
     
//     QLBangKho updateKho(QLBangKho ql);
     
     String deleteKho(String maKho);
     
     List<QLToanBoKho> getKhoByMaKho(String id);
}
