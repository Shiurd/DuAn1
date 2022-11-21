/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories.impRepo;

import DomainModels.Kho.ToanBoKho;
import DomainModels.Kho.BangKho;
import DomainModels.Kho.Bia;
import DomainModels.Kho.TongSanPhamKho;
import java.util.List;

/**
 *
 * @author Hacke
 */
public interface IKhoRepository {
    List<ToanBoKho> findAll();
    
    List<TongSanPhamKho> totalBeer();
    
    List<BangKho> bangKho();
    
    List<Bia> listBiaKho();
    
    BangKho saveBangKho(BangKho bangKho);
    
    void updateDataKho(BangKho model);
    
    String deleteKho(String maKho);
    
    List<ToanBoKho> findByMaKhoInToanBoKho(String id);
}
