/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sevices;

import DomainModels.NhanVien;
import Repositories.impRepo.IKhoRepository;
import Sevices.impSevices.INSXService;
import DomainModels.NSX;
import Repositories.NSXReponsitory;
import Repositories.impRepo.INSXReponsitory;
import ViewModels.Kho.QLNSX;
import ViewModels.QLNhanVien;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class NSXService implements INSXService{
   private ArrayList<NSX> lstQLNSX;
    private INSXReponsitory iNSXReponsitory;  
    private List<QLNSX> _lstQlnsxs;

    public NSXService() {
         lstQLNSX = new ArrayList<>();
        iNSXReponsitory = new NSXReponsitory();
        _lstQlnsxs = new ArrayList<>();
    }
    

    @Override
    public ArrayList<NSX> fillListNSX() {
       ArrayList<NSX> lst = new ArrayList<>();
        ArrayList<NSX> lstNSX = iNSXReponsitory.getAllNsx();
        for (NSX nv : lstNSX) {
            NSX qlnv = new NSX();
            qlnv.setMaNSX(nv.getMaNSX());
            qlnv.setTenNSX(nv.getTenNSX());
            

            lst.add(qlnv);
        }
        return lst; 
}
    @Override
    public QLNSX createNSX(QLNSX qlNSX){
        var x = iNSXReponsitory.createNSX(new NSX(qlNSX.getMaNSX(),qlNSX.getTenNSX()));
        return new QLNSX(x.getMaNSX(),x.getTenNSX());
    }
}
