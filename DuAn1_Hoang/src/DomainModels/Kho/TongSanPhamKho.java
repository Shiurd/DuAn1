/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels.Kho;

/**
 *
 * @author Hacke
 */
public class TongSanPhamKho {
    private String MaKhoString;
    private int SoLuong;

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

    public TongSanPhamKho() {
    }

    public TongSanPhamKho(String MaKhoString, int SoLuong) {
        this.MaKhoString = MaKhoString;
        this.SoLuong = SoLuong;
    }
    
    
}
