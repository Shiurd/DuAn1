/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels.Kho;

/**
 *
 * @author Hacke
 */
public class QLKhoChiTiet {
    private String MaSP,MaKhoString;
    private int SoLuong;

    public QLKhoChiTiet() {
    }

    public QLKhoChiTiet(String MaSP, String MaKhoString, int SoLuong) {
        this.MaSP = MaSP;
        this.MaKhoString = MaKhoString;
        this.SoLuong = SoLuong;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getMaKhoString() {
        return MaKhoString;
    }

    public void setMaKhoString(String MaKhoString) {
        this.MaKhoString = MaKhoString;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }
}
