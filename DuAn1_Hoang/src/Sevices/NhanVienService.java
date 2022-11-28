/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sevices;

import DomainModels.NhanVien1;
import Repositories.impRepo.INhanVienRepository;
import Repositories.NhanVienRepository;
import Sevices.impSevices.INhanVienService;
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
        ArrayList<NhanVien1> lstNhanViens = nhanVienRepository.getAllNhanViens();
        for (NhanVien1 nv : lstNhanViens) {
            QLNhanVien qlnv = new QLNhanVien();
            qlnv.setMaQLNV(nv.getMa());
            qlnv.setTenQLNV(nv.getTen());
            qlnv.setgTinhQLNV(nv.getGioiTinh());
            qlnv.setNgaySinhQLNV(nv.getNgaySinh());
            qlnv.setDiaChiQLNV(nv.getDiaChi());
            qlnv.setSdtQLNV(nv.getSdt());
            qlnv.setEmailQLNV(nv.getEmail());
            qlnv.setCmndQLNV(nv.getCmnd());
            qlnv.setAnhQLNV(nv.getAnh());
            qlnv.setGhiChuQLNV(nv.getGhiChu());

            lst.add(qlnv);
        }
        return lst;
    }

    @Override
    public String themNhanVien(QLNhanVien qlnv) {
        int affectedRows = nhanVienRepository.themNhanVien(qlnv);
        if (affectedRows > 0) {
            return "Thêm Thành Công";
        }
        return "Thêm Thất Bại";
    }

    @Override
    public String suaNhanVien(String maNV, QLNhanVien qlnv) {
        int affectedRows = nhanVienRepository.suaNhanVien(maNV, qlnv);
        if (affectedRows > 0) {
            return "Sửa Thành Công";
        }
        return "Sửa Thất Bại";
    }

    @Override
    public String xoaNhanVien(String maNV) {
        int affectedRows = nhanVienRepository.xoaNhanVien(maNV);
        if (affectedRows > 0) {
            return "Xóa Thành Công";
        }
        return "Xóa Thất Bại";
    }

}
