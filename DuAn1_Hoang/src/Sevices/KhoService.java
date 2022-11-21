/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sevices;

import Sevices.impSevices.IKhoService;
import DomainModels.Kho.ToanBoKho;
import DomainModels.Kho.BangKho;
import DomainModels.Kho.Bia;
import DomainModels.Kho.TongSanPhamKho;
import Repositories.impRepo.IKhoRepository;
import Repositories.KhoRepository;
import ViewModels.Kho.DSBia;
import ViewModels.Kho.QLBangKho;
import ViewModels.Kho.QLToanBoKho;
import ViewModels.Kho.QLTongSPKho;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Hacke
 */
public class KhoService implements IKhoService{
    private final IKhoRepository _iKhoRepositories;
    private List<QLToanBoKho> _lstQLKho;
    private List<QLBangKho> _lstQLBangKho;
    private List<QLTongSPKho> _lstQLTongSPKho;
    private List<DSBia> _dsBia;
    
    public KhoService(){
        _iKhoRepositories = new KhoRepository();
        _lstQLKho = new ArrayList<>();
        _lstQLBangKho = new ArrayList<>();
        _lstQLBangKho = new ArrayList<>();
        _dsBia = new ArrayList<>();
    }
    
    @Override
    public List<QLToanBoKho> getAllKho(){
        _lstQLKho = new ArrayList<>();
        var product = _iKhoRepositories.findAll();
        for (ToanBoKho toanBoKho : product) {
            _lstQLKho.add(new QLToanBoKho(toanBoKho.getMaKho(),toanBoKho.getTenKho(),toanBoKho.getMaBia(),toanBoKho.getTenBia(),toanBoKho.getDiaChi(),toanBoKho.getSoLuong()));
        }
        return _lstQLKho;
    }
    
    @Override
    public List<QLBangKho> getBangKho(){
        _lstQLBangKho = new ArrayList<>();
        var product = _iKhoRepositories.bangKho();
        for (BangKho x : product) {
            _lstQLBangKho.add(new QLBangKho(x.getMaKhoString(), x.getDiaChiString(), x.getTenKhoString()));
        }
        return  _lstQLBangKho;
    }
    
    @Override
    public List<QLTongSPKho> getTongSPKho(){
        _lstQLTongSPKho = new ArrayList<>();
        var product = _iKhoRepositories.totalBeer();
        for (TongSanPhamKho x : product) {
            _lstQLTongSPKho.add(new QLTongSPKho(x.getMaKhoString(), x.getSoLuong()));
        }
        return _lstQLTongSPKho;
    }
    
    @Override
    public List<DSBia> getBiaKho(){
        _dsBia = new ArrayList<>();
        var ds = _iKhoRepositories.listBiaKho();
        for (Bia d : ds) {
            _dsBia.add(new DSBia(d.getBia()));
        }
        return _dsBia;
    }
    
    @Override
    public QLBangKho createnewKho(QLBangKho qLBangKho){
        var x = _iKhoRepositories.saveBangKho(new BangKho(qLBangKho.getMaKhoString(), qLBangKho.getDiaChiString(), qLBangKho.getTenKhoString()));
        return new QLBangKho(x.getMaKhoString(),x.getDiaChiString(),x.getTenKhoString());
    }
    
//    @Override
//    public List<QLBangKho> updateKho(String id){
//        
//    }
    public void updateKho(QLBangKho ql){
       
    }
    
    @Override
    public String deleteKho(String maKho){
        return _iKhoRepositories.deleteKho(maKho);
    }
    @Override
    public List<QLToanBoKho> getKhoByMaKho(String id){
        _lstQLKho = new ArrayList<>();
        var search = _iKhoRepositories.findByMaKhoInToanBoKho(id);
        for (ToanBoKho toanBoKho : search) {
            _lstQLKho.add(new QLToanBoKho(toanBoKho.getMaKho(),toanBoKho.getTenKho(),toanBoKho.getMaBia(),toanBoKho.getTenBia(),toanBoKho.getDiaChi(),toanBoKho.getSoLuong()));
        }
        return _lstQLKho;
    }
}
