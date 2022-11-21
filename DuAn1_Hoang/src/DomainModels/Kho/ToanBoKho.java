/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels.Kho;

import java.io.Serializable;

/**
 *
 * @author Hacke
 */
public class ToanBoKho implements Serializable{
    private String MaKho;
    private String TenKho;
    private String MaBia;
    private String TenBia;
    private String DiaChi;
    private int SoLuong;

    public ToanBoKho() {
    }

    public ToanBoKho(String MaKho, String TenKho, String MaBia, String TenBia, String DiaChi, int SoLuong) {
        this.MaKho = MaKho;
        this.TenKho = TenKho;
        this.MaBia = MaBia;
        this.TenBia = TenBia;
        this.DiaChi = DiaChi;
        this.SoLuong = SoLuong;
    }

    public String getMaKho() {
        return MaKho;
    }

    public void setMaKho(String MaKho) {
        this.MaKho = MaKho;
    }

    public String getMaBia() {
        return MaBia;
    }

    public void setMaBia(String MaBia) {
        this.MaBia = MaBia;
    }

    

    public String getTenKho() {
        return TenKho;
    }

    public void setTenKho(String TenKho) {
        this.TenKho = TenKho;
    }

    public String getTenBia() {
        return TenBia;
    }

    public void setTenBia(String TenBia) {
        this.TenBia = TenBia;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }
    
    
}
