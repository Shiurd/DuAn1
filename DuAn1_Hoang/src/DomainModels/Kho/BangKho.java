/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels.Kho;

import ViewModels.Kho.QLBangKho;

/**
 *
 * @author Hacke
 */
public class BangKho {
    private String MaKhoString, DiaChiString,TenKhoString;

    public BangKho() {
    }

    public BangKho(String MaKhoString, String DiaChiString, String TenKhoString) {
        this.MaKhoString = MaKhoString;
        this.DiaChiString = DiaChiString;
        this.TenKhoString = TenKhoString;
    }

    public String getMaKhoString() {
        return MaKhoString;
    }

    public void setMaKhoString(String MaKhoString) {
        this.MaKhoString = MaKhoString;
    }

    public String getDiaChiString() {
        return DiaChiString;
    }

    public void setDiaChiString(String DiaChiString) {
        this.DiaChiString = DiaChiString;
    }

    public String getTenKhoString() {
        return TenKhoString;
    }

    public void setTenKhoString(String TenKhoString) {
        this.TenKhoString = TenKhoString;
    }
    
    
}
