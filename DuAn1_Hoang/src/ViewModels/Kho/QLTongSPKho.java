/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels.Kho;

/**
 *
 * @author Hacke
 */
public class QLTongSPKho {
    private String MaKhoString;
    private int SoLuong;

    public QLTongSPKho() {
    }

    public QLTongSPKho(String MaKhoString, int SoLuong) {
        this.MaKhoString = MaKhoString;
        this.SoLuong = SoLuong;
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
