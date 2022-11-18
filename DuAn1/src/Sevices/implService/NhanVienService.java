/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sevices.implService;

import DomainModels.NhanVien;
import Repositories.INhanVienRepository;
import Repositories.implRepo.NhanVienRepository;
import Sevices.INhanVienService;
import ViewModels.QLNhanVien;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NhanVienService implements INhanVienService {

    private ArrayList<QLNhanVien> lstQLNhanViens;
    private INhanVienRepository nhanVienRepository;

    public NhanVienService() {
        lstQLNhanViens = new ArrayList<>();
        nhanVienRepository = new NhanVienRepository();
    }

    @Override
    public ArrayList<QLNhanVien> fillListNhanViens() {
        ArrayList<QLNhanVien> lst = new ArrayList<>();
        ArrayList<NhanVien> lstNhanViens = nhanVienRepository.getAllNhanViens();
        for (NhanVien nv : lstNhanViens) {
            QLNhanVien qlnv = new QLNhanVien();
            qlnv.setMaQLNV(nv.getMa());
            qlnv.setTenQLNV(nv.getTen());
            qlnv.setgTinhQLNV(nv.getGioiTinh());
            qlnv.setNgaySinhQLNV(nv.getNgaySinh());
            qlnv.setDiaChiQLNV(nv.getDiaChi());
            qlnv.setSdtQLNV(nv.getSdt());

            lst.add(qlnv);
        }
        return lst;
    }

}
