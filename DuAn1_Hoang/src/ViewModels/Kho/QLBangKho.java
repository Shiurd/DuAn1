/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels.Kho;

/**
 *
 * @author Hacke
 */
public class QLBangKho {
      private String MaKhoString, DiaChiString,TenKhoString;

    public QLBangKho() {
    }

    public QLBangKho(String MaKhoString, String DiaChiString, String TenKhoString) {
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
