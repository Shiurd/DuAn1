/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DomainModels.Kho.ToanBoKho;
import DomainModels.Kho.BangKho;
import DomainModels.Kho.TongSanPhamKho;
import DomainModels.NSX;
import Sevices.impSevices.IKhoService;
import Sevices.impSevices.INhanVienService;
import Sevices.KhoService;
import Sevices.NSXService;
import Sevices.NhanVienService;
import Sevices.impSevices.INSXService;
import ViewModels.Kho.DSBia;
import ViewModels.Kho.QLBangKho;
import ViewModels.Kho.QLKhoChiTiet;
import ViewModels.Kho.QLNSX;
import ViewModels.Kho.QLToanBoKho;
import ViewModels.QLNhanVien;
import ViewModels.Kho.QLTongSPKho;
import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;
import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Admin
 */
public class Display extends javax.swing.JFrame {

    /**
     * Creates new form Display
     */
    
    IKhoService _iKhoService;
    INhanVienService nhanVienService;
    INSXService iNSXService;
    DefaultTableModel model;
    String anhNhanVien = null;
    String timKiem;
    
    
    private int _currentPage;
    private int _totalPages;
    private final int _pageSize;
    public Display() {
        initComponents();
        
        _iKhoService = new KhoService();
        iNSXService = new NSXService();
        nhanVienService = new NhanVienService();
        _currentPage = 1;
        _pageSize = 10;
        model = (DefaultTableModel) tblNhanVien1.getModel();
             
        loadDataToTableKho();
        loadTblNhanVien();
        loadDataToCBXMaKho();
        loadDataToCBXMaBia();
        loadTblNSX();
        UISettings();
        
    }
    

    
    private void UISettings(){
        txtTenKho_QuanLyBia.setEnabled(false);
        txtTenBia_QuanLyKho.setEnabled(false);
        txtMaKho_Kho.setEnabled(false);
        txtMaBia_Kho.setEnabled(false);
        txtSoLuong_Kho.setEnabled(false);
        txtDiaChi_Kho.setEnabled(false);
        
        tblKhoBia.removeColumn(tblKhoBia.getColumnModel().getColumn(3));
        tblKhoBia.removeColumn(tblKhoBia.getColumnModel().getColumn(4));
        tblKhoBia.removeColumn(tblKhoBia.getColumnModel().getColumn(3));
    }
    
    private void loadDataToCBXMaKho(){
        List<QLBangKho> bangKho = _iKhoService.getBangKho();
        for (QLBangKho x : bangKho) {
            cbxMaKho.addItem(x.getMaKhoString());
        }
    }
    private void loadDataToCBXMaBia(){
        List<DSBia> toanBoKho = _iKhoService.getBiaKho();
        for (DSBia ds : toanBoKho) {
            cbxMaBia.addItem(ds.getBia());
        }
    }
       public void loadTblNSX() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblNSX.getModel();
        ArrayList<NSX> lst = iNSXService.fillListNSX();
        dtm.setRowCount(0);
        for (NSX qLNhanVien : lst) {
            Object[] rowData = {
                qLNhanVien.getMaNSX(),
                qLNhanVien.getTenNSX(),
                
            };
            dtm.addRow(rowData);
        }
    }
    private void loadDataToTableKho(){
//        List<ToanBoKho> ds = _iKhoService.getAllKho();
//        List<TongSanPhamKho> tongKho = _iKhoService.getTotalBeer();
//        List<TongKho> khoTong = _iKhoService.getTongKho();
    
        List<QLToanBoKho> ds = _iKhoService.getAllKho();
        List<QLTongSPKho> tongKho = _iKhoService.getTongSPKho();
        List<QLBangKho> bangKho = _iKhoService.getBangKho();
        List<QLKhoChiTiet> khoChiTiet = _iKhoService.getKhoChiTiet();
        DefaultTableModel dtm = (DefaultTableModel) this.tblKhoBia.getModel();
        DefaultTableModel dtm2 = (DefaultTableModel) this.tblKho_TongBia.getModel();
        DefaultTableModel dtm3 = (DefaultTableModel) this.tblTongKho.getModel();
        DefaultTableModel dtm4 = (DefaultTableModel)this.tblKhoChiTiet.getModel();
        dtm.setRowCount(0);
        dtm2.setRowCount(0);
        dtm3.setRowCount(0);
        dtm4.setRowCount(0);
        
        for (QLKhoChiTiet ct : khoChiTiet) {
            Object[] rowData = {
              ct.getMaSP(),
              ct.getMaKhoString(),
              ct.getSoLuong(),
            };
            dtm4.addRow(rowData);
        }
        
        for(QLToanBoKho kho : ds){
            Object[] rowData = {
               
                kho.getTenKho(),
              
                kho.getTenBia(),
              
                kho.getSoLuong(),
                  kho.getMaKho(),
                  kho.getMaBia(),
                  kho.getDiaChi(),
            };
            
            dtm.addRow(rowData);
        }
        for (QLTongSPKho tk : tongKho) {
                Object[] rowData = {
                  tk.getMaKhoString(),
                  tk.getSoLuong(),
                };
                dtm2.addRow(rowData);
            }
        for (QLBangKho tongKho1 : bangKho) {
            Object[] rowData = {
              tongKho1.getMaKhoString(),
                tongKho1.getDiaChiString(),
                tongKho1.getTenKhoString(),
            };
            dtm3.addRow(rowData);
        }
    }
    
    private void loadTblNhanVien() {
        model.setNumRows(0);
        ArrayList<QLNhanVien> lst = nhanVienService.fillListNhanViens();
        for (QLNhanVien qLNhanVien : lst) {
            model.addRow(new Object[]{
                qLNhanVien.getMaQLNV(),
                qLNhanVien.getTenQLNV(),
                qLNhanVien.getgTinhQLNV(),
                qLNhanVien.getNgaySinhQLNV(),
                qLNhanVien.getDiaChiQLNV(),
                qLNhanVien.getSdtQLNV(),
                qLNhanVien.getCmndQLNV(),
                qLNhanVien.getEmailQLNV(),  
                qLNhanVien.getAnhQLNV(),
                qLNhanVien.getGhiChuQLNV(),
            });
        }
    }
    
    private QLBangKho getKhoFromInput(){
        QLBangKho ql = new QLBangKho();
        
        String maKho = txtKho.getText();
        String diaChi = txtDiaChi.getText();
        String tenKho = txtTenKho.getText();
        
        ql.setMaKhoString(maKho);
        ql.setDiaChiString(diaChi);
        ql.setTenKhoString(tenKho);
        return ql;
    }
    
    private QLKhoChiTiet getKhoChiTietFromInput(){
        QLKhoChiTiet ql = new QLKhoChiTiet();
        
        String maSP = txtMabia2.getText();
        String maKho = txtMaKho2.getText();
        Integer soLuong = Integer.parseInt(txtSoLuong2.getText());
        
        ql.setMaSP(maSP);
        ql.setMaKhoString(maKho);
        ql.setSoLuong(soLuong);
        
        return ql;
    }
    
    private QLNSX getNSXFromInput(){
        QLNSX ql = new QLNSX();
        String MaNSX = txtMaNSX_QLNSX.getText();
        String TenNSX = txtTenNSX_QLNSX.getText();
        
        ql.setMaNSX(MaNSX);
        ql.setTenNSX(TenNSX);
        return  ql;
    }
    
    private boolean validateThongTinNhanVien() {
        ArrayList<QLNhanVien> lst = nhanVienService.fillListNhanViens();
        //Mã
        if (txtMaNV1.getText().trim().isEmpty()) {
            txtMaNV1.setText("Không được để trống");
            txtMaNV1.setForeground(Color.red);
            return false;
        }

        if (txtMaNV1.getText().length() > 10) {
            txtMaNV1.setText("Độ dài không hợp lệ(<10)");
            txtMaNV1.setForeground(Color.red);
            return false;
        }

        //Tên
        if (txtTenNV1.getText().trim().isEmpty()) {
            txtTenNV1.setText("Không được để trống");
            txtTenNV1.setForeground(Color.red);
            return false;
        }

        //Ngày Sinh
        Date day = null;
        try {
            SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yyyy");
            day = fm.parse(txtNgaySinh1.getText());
        } catch (Exception e) {
            txtNgaySinh1.setText("Ngày sinh không hợp lệ(MM/dd/yyyy)!");
            txtNgaySinh1.setForeground(Color.red);
            return false;
        }

        //Địa chỉ
        if (txtDiaChi2.getText().trim().isEmpty()) {
            txtDiaChi2.setText("Không được để trống");
            txtDiaChi2.setForeground(Color.red);
            return false;
        }

        //Số điện thoại
        String dinhDangSDT = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        if (!txtSDT1.getText().matches(dinhDangSDT)) {
            txtSDT1.setText("Số điện thoại không hợp lệ!");
            txtSDT1.setForeground(Color.red);
            return false;
        }

        //CMND
        String dinhDangCMND = "\\d{12}";
        if (!txtCMND1.getText().matches(dinhDangCMND)) {
            txtCMND1.setText("Số CMND không hợp lệ!");
            txtCMND1.setForeground(Color.red);
            return false;
        }
        for (QLNhanVien qLNhanVien : lst) {
            if (qLNhanVien.getCmndQLNV().equalsIgnoreCase(txtCMND1.getText())) {
                txtCMND1.setText("Số CMND trùng");
                txtCMND1.setForeground(Color.red);
                return false;
            }
        }

        //Email
        String dinhDangEmail = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        if (!txtEmail1.getText().matches(dinhDangEmail)) {
            txtEmail1.setText("Email không hợp lệ!");
            txtEmail1.setForeground(Color.red);
            return false;
        }

        //Ghi Chú:
        if (txtGhiChu1.getText().trim().isEmpty()) {
            txtGhiChu1.setText("Không được để trống");
            txtGhiChu1.setForeground(Color.red);
            return false;
        }

        //ảnh
        if (anhNhanVien == null) {
            JOptionPane.showMessageDialog(this, "Ảnh để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void themThongTinNhanVien(QLNhanVien qlnv) {
        String ketQua = nhanVienService.themNhanVien(qlnv);
        loadTblNhanVien();
        JOptionPane.showMessageDialog(this, ketQua);
    }

    private void suaThongTinNhanVien(String maNV, QLNhanVien qlnv) {
        String ketQua = nhanVienService.suaNhanVien(maNV, qlnv);
        loadTblNhanVien();
        JOptionPane.showMessageDialog(this, ketQua);
    }

    private void xoaThongTinNhanVien(String maNV) {
        String ketQua = nhanVienService.xoaNhanVien(maNV);
        loadTblNhanVien();
        txtMaNV1.setText("");
        txtTenNV1.setText("");
        rdoNam1.setSelected(true);
        txtNgaySinh1.setText("");
        txtDiaChi2.setText("");
        txtSDT1.setText("");
        txtCMND1.setText("");
        txtEmail1.setText("");
        txtGhiChu1.setText("");
        //lblAnh
        lblAnhNhanVien1.setIcon(null);
        lblAnhNhanVien1.setText("Hình ảnh");
        anhNhanVien = null;
        JOptionPane.showMessageDialog(this, ketQua);
    }
    
    private void loadTblTimKiem(String timKiem) {
        ArrayList<QLNhanVien> lst = nhanVienService.fillListNhanViens();
        if (timKiem == null) {
            return;
        }
        model.setNumRows(0);
        for (QLNhanVien qlnv : lst) {
            if (qlnv.getMaQLNV().toLowerCase().contains(timKiem.toLowerCase()) || qlnv.getTenQLNV().toLowerCase().contains(timKiem.toLowerCase())
                    || qlnv.getgTinhQLNV().toLowerCase().contains(timKiem.toLowerCase()) || qlnv.getNgaySinhQLNV().toLowerCase().contains(timKiem.toLowerCase())
                    || qlnv.getDiaChiQLNV().toLowerCase().contains(timKiem.toLowerCase()) || qlnv.getSdtQLNV().toLowerCase().contains(timKiem.toLowerCase())
                    || qlnv.getCmndQLNV().toLowerCase().contains(timKiem.toLowerCase()) || qlnv.getEmailQLNV().toLowerCase().contains(timKiem.toLowerCase())
                    || qlnv.getGhiChuQLNV().toLowerCase().contains(timKiem.toLowerCase()) || qlnv.getAnhQLNV().toLowerCase().contains(timKiem.toLowerCase())) {
                model.addRow(new Object[]{
                    qlnv.getMaQLNV(),
                    qlnv.getTenQLNV(),
                    qlnv.getgTinhQLNV(),
                    qlnv.getNgaySinhQLNV(),
                    qlnv.getDiaChiQLNV(),
                    qlnv.getSdtQLNV(),
                    qlnv.getCmndQLNV(),
                    qlnv.getEmailQLNV(),
                    qlnv.getGhiChuQLNV(),
                    qlnv.getAnhQLNV()
                });
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        headerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        navbarPanel = new javax.swing.JPanel();
        btnBia = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnHoaDon = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btnKhoBia = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnDoanhThu = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        btnNhanVien = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        btnKhuyenMai = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        bodyPanel = new javax.swing.JPanel();
        body = new javax.swing.JTabbedPane();
        hoaDonPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        biaPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        khoBiaPanel = new javax.swing.JPanel();
        hoaDonPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhoBia = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        txtTenKho_QuanLyBia = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtSoLuong_Kho = new javax.swing.JTextField();
        txtTenBia_QuanLyKho = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDiaChi_Kho = new javax.swing.JTextArea();
        txtMaKho_Kho = new javax.swing.JTextField();
        txtMaBia_Kho = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        cbxMaBia = new javax.swing.JComboBox<>();
        btnSreachInTongKhoBia = new javax.swing.JButton();
        cbxMaKho = new javax.swing.JComboBox<>();
        jLabel56 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        btnLoadLaiTrongTongBIa = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        panelThemKho = new javax.swing.JPanel();
        labelThemKho = new javax.swing.JLabel();
        panelSuaKho = new javax.swing.JPanel();
        labelSuaKho = new javax.swing.JLabel();
        panelXoaKho = new javax.swing.JPanel();
        labelXoaKho = new javax.swing.JLabel();
        panelMoiBia = new javax.swing.JPanel();
        labelMoiKho = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTongKho = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtKho = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTenKho = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblKho_TongBia = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKhoChiTiet = new javax.swing.JTable();
        txtSoLuong2 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        panelThemKho2 = new javax.swing.JPanel();
        labelThemKho2 = new javax.swing.JLabel();
        panelSuaKho1 = new javax.swing.JPanel();
        labelSuaKho2 = new javax.swing.JLabel();
        panelXoaKho1 = new javax.swing.JPanel();
        labelXoaKho2 = new javax.swing.JLabel();
        panelMoiBia1 = new javax.swing.JPanel();
        labelMoiKho2 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtMabia2 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtMaKho2 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        doanhThuPanel = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        nhanVienPanel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblNhanVien1 = new javax.swing.JTable();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtMaNV1 = new javax.swing.JTextField();
        txtTenNV1 = new javax.swing.JTextField();
        txtNgaySinh1 = new javax.swing.JTextField();
        txtDiaChi2 = new javax.swing.JTextField();
        txtSDT1 = new javax.swing.JTextField();
        rdoNam1 = new javax.swing.JRadioButton();
        rdoNu1 = new javax.swing.JRadioButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtGhiChu1 = new javax.swing.JTextArea();
        txtCMND1 = new javax.swing.JTextField();
        txtEmail1 = new javax.swing.JTextField();
        btnThemNhanVien1 = new javax.swing.JButton();
        btnSuaNhanVien1 = new javax.swing.JButton();
        btnXoaNhanVien1 = new javax.swing.JButton();
        btnTaoMoiNhanVien1 = new javax.swing.JButton();
        lblAnhNhanVien1 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txtTimKiem1 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        khuyenMaiPanel = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        khuyenMaiPanel1 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblNSX = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtTenNSX_QLNSX = new javax.swing.JTextField();
        txtMaNSX_QLNSX = new javax.swing.JTextField();
        btnAddNSX = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        footerPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerPanel.setBackground(new java.awt.Color(255, 153, 51));
        headerPanel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_menu_48px_1.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("QUẢN LÝ CÔNG VIỆC BÁN BIA");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-beer-bottle-64.png"))); // NOI18N
        jLabel3.setText("jLabel3");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/male_user_50px.png"))); // NOI18N
        jLabel10.setText("Hello, Admin");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search_26px.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/group_message_26px.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bell_26px.png"))); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 367, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(161, 161, 161)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(21, 21, 21))
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                    .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10)
                        .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))))
                .addContainerGap())
        );

        getContentPane().add(headerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1620, 90));

        navbarPanel.setBackground(new java.awt.Color(255, 204, 102));

        btnBia.setBackground(new java.awt.Color(255, 153, 51));
        btnBia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        btnBia.setPreferredSize(new java.awt.Dimension(0, 90));
        btnBia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBiaMousePressed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/grid_24px.png"))); // NOI18N
        jLabel12.setText("          Bia");

        javax.swing.GroupLayout btnBiaLayout = new javax.swing.GroupLayout(btnBia);
        btnBia.setLayout(btnBiaLayout);
        btnBiaLayout.setHorizontalGroup(
            btnBiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBiaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnBiaLayout.setVerticalGroup(
            btnBiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBiaLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        btnHoaDon.setBackground(new java.awt.Color(255, 153, 51));
        btnHoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 2));
        btnHoaDon.setPreferredSize(new java.awt.Dimension(0, 90));
        btnHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHoaDonMousePressed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shopping_cart_24px.png"))); // NOI18N
        jLabel11.setText("   Hóa Đon");

        javax.swing.GroupLayout btnHoaDonLayout = new javax.swing.GroupLayout(btnHoaDon);
        btnHoaDon.setLayout(btnHoaDonLayout);
        btnHoaDonLayout.setHorizontalGroup(
            btnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHoaDonLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnHoaDonLayout.setVerticalGroup(
            btnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHoaDonLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        btnKhoBia.setBackground(new java.awt.Color(255, 153, 51));
        btnKhoBia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        btnKhoBia.setPreferredSize(new java.awt.Dimension(0, 90));
        btnKhoBia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnKhoBiaMousePressed(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit_property_24px.png"))); // NOI18N
        jLabel13.setText("       Kho Bia");

        javax.swing.GroupLayout btnKhoBiaLayout = new javax.swing.GroupLayout(btnKhoBia);
        btnKhoBia.setLayout(btnKhoBiaLayout);
        btnKhoBiaLayout.setHorizontalGroup(
            btnKhoBiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnKhoBiaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        btnKhoBiaLayout.setVerticalGroup(
            btnKhoBiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnKhoBiaLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btnDoanhThu.setBackground(new java.awt.Color(255, 153, 51));
        btnDoanhThu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        btnDoanhThu.setDoubleBuffered(false);
        btnDoanhThu.setFocusTraversalPolicyProvider(true);
        btnDoanhThu.setPreferredSize(new java.awt.Dimension(0, 90));
        btnDoanhThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDoanhThuMousePressed(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/account_24px.png"))); // NOI18N
        jLabel14.setText("    Doanh thu");

        javax.swing.GroupLayout btnDoanhThuLayout = new javax.swing.GroupLayout(btnDoanhThu);
        btnDoanhThu.setLayout(btnDoanhThuLayout);
        btnDoanhThuLayout.setHorizontalGroup(
            btnDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDoanhThuLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnDoanhThuLayout.setVerticalGroup(
            btnDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDoanhThuLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        btnNhanVien.setBackground(new java.awt.Color(255, 153, 51));
        btnNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        btnNhanVien.setPreferredSize(new java.awt.Dimension(0, 90));
        btnNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNhanVienMousePressed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add_user_group_woman_man_24px.png"))); // NOI18N
        jLabel15.setText("      Nhân Viên");

        javax.swing.GroupLayout btnNhanVienLayout = new javax.swing.GroupLayout(btnNhanVien);
        btnNhanVien.setLayout(btnNhanVienLayout);
        btnNhanVienLayout.setHorizontalGroup(
            btnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnNhanVienLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnNhanVienLayout.setVerticalGroup(
            btnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnNhanVienLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        btnKhuyenMai.setBackground(new java.awt.Color(255, 153, 51));
        btnKhuyenMai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        btnKhuyenMai.setPreferredSize(new java.awt.Dimension(0, 90));
        btnKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnKhuyenMaiMousePressed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-manufacturing-24.png"))); // NOI18N
        jLabel16.setText("      NSX");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnKhuyenMaiLayout = new javax.swing.GroupLayout(btnKhuyenMai);
        btnKhuyenMai.setLayout(btnKhuyenMaiLayout);
        btnKhuyenMaiLayout.setHorizontalGroup(
            btnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnKhuyenMaiLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnKhuyenMaiLayout.setVerticalGroup(
            btnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout navbarPanelLayout = new javax.swing.GroupLayout(navbarPanel);
        navbarPanel.setLayout(navbarPanelLayout);
        navbarPanelLayout.setHorizontalGroup(
            navbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBia, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addComponent(btnKhoBia, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addComponent(btnDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addComponent(btnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addComponent(btnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addComponent(btnKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        navbarPanelLayout.setVerticalGroup(
            navbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navbarPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnKhoBia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        getContentPane().add(navbarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 170, 670));

        bodyPanel.setBackground(new java.awt.Color(255, 255, 204));
        bodyPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        hoaDonPanel.setBackground(new java.awt.Color(255, 255, 153));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 102, 0));
        jLabel6.setText("Quản Lý Hóa Đơn");

        javax.swing.GroupLayout hoaDonPanelLayout = new javax.swing.GroupLayout(hoaDonPanel);
        hoaDonPanel.setLayout(hoaDonPanelLayout);
        hoaDonPanelLayout.setHorizontalGroup(
            hoaDonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoaDonPanelLayout.createSequentialGroup()
                .addGap(534, 534, 534)
                .addComponent(jLabel6)
                .addContainerGap(4202, Short.MAX_VALUE))
        );
        hoaDonPanelLayout.setVerticalGroup(
            hoaDonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoaDonPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel6)
                .addContainerGap(600, Short.MAX_VALUE))
        );

        body.addTab("tab2", hoaDonPanel);

        biaPanel.setBackground(new java.awt.Color(255, 255, 153));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 102, 0));
        jLabel8.setText("Quản Lý Bia");

        javax.swing.GroupLayout biaPanelLayout = new javax.swing.GroupLayout(biaPanel);
        biaPanel.setLayout(biaPanelLayout);
        biaPanelLayout.setHorizontalGroup(
            biaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(biaPanelLayout.createSequentialGroup()
                .addGap(534, 534, 534)
                .addComponent(jLabel8)
                .addContainerGap(4268, Short.MAX_VALUE))
        );
        biaPanelLayout.setVerticalGroup(
            biaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(biaPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel8)
                .addContainerGap(600, Short.MAX_VALUE))
        );

        body.addTab("tab3", biaPanel);

        khoBiaPanel.setBackground(new java.awt.Color(255, 255, 153));

        hoaDonPanel1.setBackground(new java.awt.Color(255, 255, 255));
        hoaDonPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0), 5));

        tblKhoBia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên kho", "Tên bia", "Số lượng", "Makho", "MaBia", "DiaChi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhoBia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhoBiaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhoBia);
        if (tblKhoBia.getColumnModel().getColumnCount() > 0) {
            tblKhoBia.getColumnModel().getColumn(0).setResizable(false);
            tblKhoBia.getColumnModel().getColumn(1).setResizable(false);
            tblKhoBia.getColumnModel().getColumn(2).setResizable(false);
            tblKhoBia.getColumnModel().getColumn(3).setResizable(false);
            tblKhoBia.getColumnModel().getColumn(4).setResizable(false);
            tblKhoBia.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Tên bia:");

        txtTenKho_QuanLyBia.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTenKho_QuanLyBia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKho_QuanLyBiaActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("Tên kho:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText("Số lượng:");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText("Địa chỉ:");

        txtSoLuong_Kho.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        txtTenBia_QuanLyKho.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTenBia_QuanLyKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenBia_QuanLyKhoActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel51.setText("Mã kho:");

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel52.setText("Mã bia:");

        jLabel55.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        jLabel55.setText("Danh sách bia trong kho");

        txtDiaChi_Kho.setColumns(20);
        txtDiaChi_Kho.setRows(5);
        txtDiaChi_Kho.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(txtDiaChi_Kho);

        txtMaKho_Kho.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        txtMaBia_Kho.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        cbxMaBia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã bia" }));
        cbxMaBia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMaBiaActionPerformed(evt);
            }
        });

        btnSreachInTongKhoBia.setBackground(new java.awt.Color(255, 255, 255));
        btnSreachInTongKhoBia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSreachInTongKhoBia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-24.png"))); // NOI18N
        btnSreachInTongKhoBia.setText("Tìm kiếm");
        btnSreachInTongKhoBia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSreachInTongKhoBiaActionPerformed(evt);
            }
        });

        cbxMaKho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã kho" }));
        cbxMaKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxMaKhoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbxMaKhoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbxMaKhoMouseReleased(evt);
            }
        });
        cbxMaKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMaKhoActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel56.setText("Tìm kiếm:");

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel53.setText("Mã kho:");

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel57.setText("Mã bia:");

        btnLoadLaiTrongTongBIa.setBackground(new java.awt.Color(255, 255, 255));
        btnLoadLaiTrongTongBIa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLoadLaiTrongTongBIa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-available-updates-24.png"))); // NOI18N
        btnLoadLaiTrongTongBIa.setText("Load lại bảng");
        btnLoadLaiTrongTongBIa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadLaiTrongTongBIaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxMaKho, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxMaBia, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSreachInTongKhoBia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLoadLaiTrongTongBIa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(jLabel57))
                        .addGap(3, 3, 3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLoadLaiTrongTongBIa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxMaBia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSreachInTongKhoBia)
                        .addComponent(cbxMaKho, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout hoaDonPanel1Layout = new javax.swing.GroupLayout(hoaDonPanel1);
        hoaDonPanel1.setLayout(hoaDonPanel1Layout);
        hoaDonPanel1Layout.setHorizontalGroup(
            hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoaDonPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hoaDonPanel1Layout.createSequentialGroup()
                        .addGroup(hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(hoaDonPanel1Layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addComponent(txtTenBia_QuanLyKho, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(287, Short.MAX_VALUE))
                    .addGroup(hoaDonPanel1Layout.createSequentialGroup()
                        .addGroup(hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(hoaDonPanel1Layout.createSequentialGroup()
                                .addGroup(hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaKho_Kho)
                                    .addComponent(txtMaBia_Kho, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                                .addGap(20, 20, 20)
                                .addGroup(hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenKho_QuanLyBia, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(hoaDonPanel1Layout.createSequentialGroup()
                                .addGroup(hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoLuong_Kho, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(hoaDonPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addContainerGap())))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hoaDonPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        hoaDonPanel1Layout.setVerticalGroup(
            hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoaDonPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel55)
                .addGap(15, 15, 15)
                .addGroup(hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenKho_QuanLyBia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSoLuong_Kho, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMaKho_Kho))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addGroup(hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel52)
                        .addComponent(jLabel25)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(hoaDonPanel1Layout.createSequentialGroup()
                        .addGroup(hoaDonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenBia_QuanLyKho, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaBia_Kho, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0), 5));

        jPanel6.setBackground(new java.awt.Color(51, 51, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255), 3));

        panelThemKho.setBackground(new java.awt.Color(255, 255, 255));
        panelThemKho.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        labelThemKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-new-24.png"))); // NOI18N
        labelThemKho.setText("Thêm");
        labelThemKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelThemKhoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelThemKhoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelThemKhoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelThemKhoLayout = new javax.swing.GroupLayout(panelThemKho);
        panelThemKho.setLayout(panelThemKhoLayout);
        panelThemKhoLayout.setHorizontalGroup(
            panelThemKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemKhoLayout.createSequentialGroup()
                .addComponent(labelThemKho, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelThemKhoLayout.setVerticalGroup(
            panelThemKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelThemKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelSuaKho.setBackground(new java.awt.Color(255, 255, 255));

        labelSuaKho.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hacke\\OneDrive\\Máy tính\\DuAn1\\src\\icon\\icons8-pencil-30.png")); // NOI18N
        labelSuaKho.setText("Sửa");
        labelSuaKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSuaKhoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelSuaKhoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelSuaKhoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelSuaKhoLayout = new javax.swing.GroupLayout(panelSuaKho);
        panelSuaKho.setLayout(panelSuaKhoLayout);
        panelSuaKhoLayout.setHorizontalGroup(
            panelSuaKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSuaKhoLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(labelSuaKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSuaKhoLayout.setVerticalGroup(
            panelSuaKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelSuaKho, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        panelXoaKho.setBackground(new java.awt.Color(255, 255, 255));
        panelXoaKho.setMaximumSize(new java.awt.Dimension(78, 48));
        panelXoaKho.setMinimumSize(new java.awt.Dimension(78, 48));
        panelXoaKho.setPreferredSize(new java.awt.Dimension(112, 22));

        labelXoaKho.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hacke\\OneDrive\\Máy tính\\DuAn1\\src\\icon\\icons8-trash-24.png")); // NOI18N
        labelXoaKho.setText(" Xóa");
        labelXoaKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelXoaKhoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelXoaKhoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelXoaKhoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelXoaKhoLayout = new javax.swing.GroupLayout(panelXoaKho);
        panelXoaKho.setLayout(panelXoaKhoLayout);
        panelXoaKhoLayout.setHorizontalGroup(
            panelXoaKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelXoaKho, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
        );
        panelXoaKhoLayout.setVerticalGroup(
            panelXoaKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelXoaKho, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        panelMoiBia.setBackground(new java.awt.Color(255, 255, 255));

        labelMoiKho.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hacke\\OneDrive\\Máy tính\\DuAn1\\src\\icon\\icons8-renew-24.png")); // NOI18N
        labelMoiKho.setText("Làm mới");
        labelMoiKho.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        labelMoiKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMoiKhoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelMoiKhoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelMoiKhoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelMoiBiaLayout = new javax.swing.GroupLayout(panelMoiBia);
        panelMoiBia.setLayout(panelMoiBiaLayout);
        panelMoiBiaLayout.setHorizontalGroup(
            panelMoiBiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMoiKho, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
        );
        panelMoiBiaLayout.setVerticalGroup(
            panelMoiBiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMoiKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelThemKho, javax.swing.GroupLayout.PREFERRED_SIZE, 89, Short.MAX_VALUE)
                    .addComponent(panelXoaKho, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSuaKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelMoiBia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelThemKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSuaKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelXoaKho, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(panelMoiBia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        tblTongKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã kho", "Địa chỉ", "Tên kho"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTongKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTongKhoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTongKho);
        if (tblTongKho.getColumnModel().getColumnCount() > 0) {
            tblTongKho.getColumnModel().getColumn(0).setResizable(false);
            tblTongKho.getColumnModel().getColumn(1).setResizable(false);
            tblTongKho.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel5.setText("Mã kho:");

        jLabel17.setText("Tên kho:");

        jLabel21.setText("Địa chỉ:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(txtTenKho)
                            .addComponent(txtKho)
                            .addComponent(jLabel21)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtKho, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenKho, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thông tin kho", jPanel3);

        tblKho_TongBia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kho", "Tổng sản phẩm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tblKho_TongBia);
        if (tblKho_TongBia.getColumnModel().getColumnCount() > 0) {
            tblKho_TongBia.getColumnModel().getColumn(0).setResizable(false);
            tblKho_TongBia.getColumnModel().getColumn(1).setResizable(false);
        }

        tblKhoChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Mã kho", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhoChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhoChiTietMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblKhoChiTiet);
        if (tblKhoChiTiet.getColumnModel().getColumnCount() > 0) {
            tblKhoChiTiet.getColumnModel().getColumn(0).setResizable(false);
            tblKhoChiTiet.getColumnModel().getColumn(1).setResizable(false);
            tblKhoChiTiet.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel11.setBackground(new java.awt.Color(51, 51, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255), 3));

        panelThemKho2.setBackground(new java.awt.Color(255, 255, 255));
        panelThemKho2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        labelThemKho2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-new-24.png"))); // NOI18N
        labelThemKho2.setText("Thêm");
        labelThemKho2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelThemKho2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelThemKho2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelThemKho2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelThemKho2Layout = new javax.swing.GroupLayout(panelThemKho2);
        panelThemKho2.setLayout(panelThemKho2Layout);
        panelThemKho2Layout.setHorizontalGroup(
            panelThemKho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemKho2Layout.createSequentialGroup()
                .addComponent(labelThemKho2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelThemKho2Layout.setVerticalGroup(
            panelThemKho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelThemKho2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelSuaKho1.setBackground(new java.awt.Color(255, 255, 255));

        labelSuaKho2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hacke\\OneDrive\\Máy tính\\DuAn1\\src\\icon\\icons8-pencil-30.png")); // NOI18N
        labelSuaKho2.setText("Sửa");
        labelSuaKho2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSuaKho2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelSuaKho2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelSuaKho2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelSuaKho1Layout = new javax.swing.GroupLayout(panelSuaKho1);
        panelSuaKho1.setLayout(panelSuaKho1Layout);
        panelSuaKho1Layout.setHorizontalGroup(
            panelSuaKho1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSuaKho1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(labelSuaKho2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSuaKho1Layout.setVerticalGroup(
            panelSuaKho1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelSuaKho2, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        panelXoaKho1.setBackground(new java.awt.Color(255, 255, 255));
        panelXoaKho1.setMaximumSize(new java.awt.Dimension(78, 48));
        panelXoaKho1.setMinimumSize(new java.awt.Dimension(78, 48));
        panelXoaKho1.setPreferredSize(new java.awt.Dimension(112, 22));

        labelXoaKho2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hacke\\OneDrive\\Máy tính\\DuAn1\\src\\icon\\icons8-trash-24.png")); // NOI18N
        labelXoaKho2.setText(" Xóa");
        labelXoaKho2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelXoaKho2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelXoaKho2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelXoaKho2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelXoaKho1Layout = new javax.swing.GroupLayout(panelXoaKho1);
        panelXoaKho1.setLayout(panelXoaKho1Layout);
        panelXoaKho1Layout.setHorizontalGroup(
            panelXoaKho1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelXoaKho2, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
        );
        panelXoaKho1Layout.setVerticalGroup(
            panelXoaKho1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelXoaKho2, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        panelMoiBia1.setBackground(new java.awt.Color(255, 255, 255));

        labelMoiKho2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hacke\\OneDrive\\Máy tính\\DuAn1\\src\\icon\\icons8-renew-24.png")); // NOI18N
        labelMoiKho2.setText("Làm mới");
        labelMoiKho2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        labelMoiKho2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMoiKho2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelMoiKho2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelMoiKho2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelMoiBia1Layout = new javax.swing.GroupLayout(panelMoiBia1);
        panelMoiBia1.setLayout(panelMoiBia1Layout);
        panelMoiBia1Layout.setHorizontalGroup(
            panelMoiBia1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMoiKho2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
        );
        panelMoiBia1Layout.setVerticalGroup(
            panelMoiBia1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMoiKho2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelThemKho2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, Short.MAX_VALUE)
                    .addComponent(panelXoaKho1, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSuaKho1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelMoiBia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelThemKho2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSuaKho1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelXoaKho1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(panelMoiBia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel22.setText(" Mã bia:");

        jLabel23.setText("Mã kho:");

        jLabel24.setText("Số lương:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(txtMaKho2)
                            .addComponent(txtMabia2)
                            .addComponent(jLabel24)
                            .addComponent(txtSoLuong2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtMabia2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaKho2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuong2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tổng sản phẩm từng kho", jPanel7);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(204, 102, 0));
        jLabel54.setText("Quản Lý Kho Bia");

        javax.swing.GroupLayout khoBiaPanelLayout = new javax.swing.GroupLayout(khoBiaPanel);
        khoBiaPanel.setLayout(khoBiaPanelLayout);
        khoBiaPanelLayout.setHorizontalGroup(
            khoBiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khoBiaPanelLayout.createSequentialGroup()
                .addGroup(khoBiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(khoBiaPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(hoaDonPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(khoBiaPanelLayout.createSequentialGroup()
                        .addGap(573, 573, 573)
                        .addComponent(jLabel54)))
                .addContainerGap(3492, Short.MAX_VALUE))
        );
        khoBiaPanelLayout.setVerticalGroup(
            khoBiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, khoBiaPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel54)
                .addGap(18, 18, 18)
                .addGroup(khoBiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hoaDonPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56))
        );

        body.addTab("tab4", khoBiaPanel);

        doanhThuPanel.setBackground(new java.awt.Color(255, 255, 153));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 102, 0));
        jLabel18.setText("Quản Lý Doanh Thu");

        javax.swing.GroupLayout doanhThuPanelLayout = new javax.swing.GroupLayout(doanhThuPanel);
        doanhThuPanel.setLayout(doanhThuPanelLayout);
        doanhThuPanelLayout.setHorizontalGroup(
            doanhThuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doanhThuPanelLayout.createSequentialGroup()
                .addGap(534, 534, 534)
                .addComponent(jLabel18)
                .addContainerGap(4178, Short.MAX_VALUE))
        );
        doanhThuPanelLayout.setVerticalGroup(
            doanhThuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doanhThuPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel18)
                .addContainerGap(600, Short.MAX_VALUE))
        );

        body.addTab("tab5", doanhThuPanel);

        nhanVienPanel.setBackground(new java.awt.Color(255, 255, 153));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 102, 0));

        jPanel10.setBackground(new java.awt.Color(255, 255, 153));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblNhanVien1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaNV", "TenNV", "Giới Tính", "Ngày sinh", "Địa Chỉ ", "Số điện thoại", "CMND", "Email", "Ảnh", "GhiChu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNhanVien1MousePressed(evt);
            }
        });
        jScrollPane9.setViewportView(tblNhanVien1);
        if (tblNhanVien1.getColumnModel().getColumnCount() > 0) {
            tblNhanVien1.getColumnModel().getColumn(0).setResizable(false);
            tblNhanVien1.getColumnModel().getColumn(1).setResizable(false);
            tblNhanVien1.getColumnModel().getColumn(2).setResizable(false);
            tblNhanVien1.getColumnModel().getColumn(3).setResizable(false);
            tblNhanVien1.getColumnModel().getColumn(4).setResizable(false);
            tblNhanVien1.getColumnModel().getColumn(5).setResizable(false);
            tblNhanVien1.getColumnModel().getColumn(6).setResizable(false);
            tblNhanVien1.getColumnModel().getColumn(7).setResizable(false);
            tblNhanVien1.getColumnModel().getColumn(8).setResizable(false);
            tblNhanVien1.getColumnModel().getColumn(9).setResizable(false);
        }

        jLabel42.setText("Mã Nhân Viên:");

        jLabel43.setText("Họ Tên:");

        jLabel44.setText("Giới Tính:");

        jLabel45.setText("Ngày sinh:");

        jLabel46.setText("Địa Chỉ:");

        jLabel47.setText("Số điện thoại:");

        jLabel48.setText("CMND:");

        jLabel49.setText("Email:");

        jLabel50.setText("Ghi Chú:");

        buttonGroup2.add(rdoNam1);
        rdoNam1.setSelected(true);
        rdoNam1.setText("Nam");

        buttonGroup2.add(rdoNu1);
        rdoNu1.setText("Nữ");

        txtGhiChu1.setColumns(20);
        txtGhiChu1.setRows(5);
        jScrollPane10.setViewportView(txtGhiChu1);

        btnThemNhanVien1.setText("Thêm");
        btnThemNhanVien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVien1ActionPerformed(evt);
            }
        });

        btnSuaNhanVien1.setText("Sửa");
        btnSuaNhanVien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNhanVien1ActionPerformed(evt);
            }
        });

        btnXoaNhanVien1.setText("Xóa");
        btnXoaNhanVien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhanVien1ActionPerformed(evt);
            }
        });

        btnTaoMoiNhanVien1.setText("Mới");
        btnTaoMoiNhanVien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiNhanVien1ActionPerformed(evt);
            }
        });

        lblAnhNhanVien1.setText("Ảnh");
        lblAnhNhanVien1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblAnhNhanVien1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhNhanVien1MouseClicked(evt);
            }
        });

        jLabel58.setText("Tìm Kiếm:");

        txtTimKiem1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiem1CaretUpdate(evt);
            }
        });
        txtTimKiem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiem1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel44)
                                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(17, 17, 17)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMaNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTenNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(rdoNam1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(65, 65, 65)
                                                .addComponent(rdoNu1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtNgaySinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDiaChi2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel58))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(53, 53, 53)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCMND1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(161, 161, 161)
                                .addComponent(lblAnhNhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(460, 460, 460)
                                .addComponent(btnThemNhanVien1)
                                .addGap(12, 12, 12)
                                .addComponent(btnSuaNhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoaNhanVien1)
                                .addGap(17, 17, 17)
                                .addComponent(btnTaoMoiNhanVien1))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtMaNV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(txtTenNV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoNam1)
                            .addComponent(rdoNu1))
                        .addGap(32, 32, 32)
                        .addComponent(txtNgaySinh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(txtDiaChi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblAnhNhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(jLabel48))
                                .addComponent(txtCMND1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(21, 21, 21)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(jLabel49))
                                .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(38, 38, 38)
                            .addComponent(jLabel50)
                            .addGap(34, 34, 34)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(jLabel42)
                            .addGap(27, 27, 27)
                            .addComponent(jLabel43)
                            .addGap(41, 41, 41)
                            .addComponent(jLabel44)
                            .addGap(37, 37, 37)
                            .addComponent(jLabel45)
                            .addGap(39, 39, 39)
                            .addComponent(jLabel46)
                            .addGap(34, 34, 34)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel47)
                                .addComponent(txtSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(26, 26, 26)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemNhanVien1)
                        .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel58))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSuaNhanVien1)
                        .addComponent(btnXoaNhanVien1)
                        .addComponent(btnTaoMoiNhanVien1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(204, 102, 0));
        jLabel41.setText("Quản Lý Nhân Viên");

        javax.swing.GroupLayout nhanVienPanelLayout = new javax.swing.GroupLayout(nhanVienPanel);
        nhanVienPanel.setLayout(nhanVienPanelLayout);
        nhanVienPanelLayout.setHorizontalGroup(
            nhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanVienPanelLayout.createSequentialGroup()
                .addGroup(nhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nhanVienPanelLayout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(jLabel19))
                    .addGroup(nhanVienPanelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 1346, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(nhanVienPanelLayout.createSequentialGroup()
                        .addGap(648, 648, 648)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(3539, Short.MAX_VALUE))
        );
        nhanVienPanelLayout.setVerticalGroup(
            nhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanVienPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel41)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        body.addTab("tab6", nhanVienPanel);

        khuyenMaiPanel.setBackground(new java.awt.Color(255, 255, 153));

        khuyenMaiPanel1.setBackground(new java.awt.Color(255, 255, 153));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(204, 102, 0));
        jLabel29.setText("Quản Lý Nhà Sản Xuất");

        tblNSX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaNSX", "TenNSX"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNSX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNSXMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblNSX);
        if (tblNSX.getColumnModel().getColumnCount() > 0) {
            tblNSX.getColumnModel().getColumn(0).setResizable(false);
            tblNSX.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel30.setText("Mã NSX");

        jLabel31.setText("Tên NSX");

        btnAddNSX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-new-24.png"))); // NOI18N
        btnAddNSX.setText("Thêm");
        btnAddNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNSXActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-pencil-30.png"))); // NOI18N
        jButton2.setText("Sửa");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-renew-24.png"))); // NOI18N
        jButton3.setText("Làm mới");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-trash-24.png"))); // NOI18N
        jButton4.setText("Xóa");

        javax.swing.GroupLayout khuyenMaiPanel1Layout = new javax.swing.GroupLayout(khuyenMaiPanel1);
        khuyenMaiPanel1.setLayout(khuyenMaiPanel1Layout);
        khuyenMaiPanel1Layout.setHorizontalGroup(
            khuyenMaiPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khuyenMaiPanel1Layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addGroup(khuyenMaiPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(khuyenMaiPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(198, 198, 198)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(khuyenMaiPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(khuyenMaiPanel1Layout.createSequentialGroup()
                            .addComponent(txtMaNSX_QLNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(khuyenMaiPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(khuyenMaiPanel1Layout.createSequentialGroup()
                                    .addGap(84, 84, 84)
                                    .addComponent(jLabel29)
                                    .addGap(106, 106, 106))
                                .addGroup(khuyenMaiPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(txtTenNSX_QLNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(khuyenMaiPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAddNSX, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(khuyenMaiPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(638, Short.MAX_VALUE))
        );
        khuyenMaiPanel1Layout.setVerticalGroup(
            khuyenMaiPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khuyenMaiPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(khuyenMaiPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(khuyenMaiPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNSX_QLNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNSX_QLNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(khuyenMaiPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(khuyenMaiPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(khuyenMaiPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout khuyenMaiPanelLayout = new javax.swing.GroupLayout(khuyenMaiPanel);
        khuyenMaiPanel.setLayout(khuyenMaiPanelLayout);
        khuyenMaiPanelLayout.setHorizontalGroup(
            khuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khuyenMaiPanelLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 1601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3336, Short.MAX_VALUE))
        );
        khuyenMaiPanelLayout.setVerticalGroup(
            khuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khuyenMaiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        body.addTab("tab1", khuyenMaiPanel);

        bodyPanel.add(body, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, -1, 700));

        getContentPane().add(bodyPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 1450, 670));

        footerPanel.setBackground(new java.awt.Color(204, 102, 0));

        javax.swing.GroupLayout footerPanelLayout = new javax.swing.GroupLayout(footerPanel);
        footerPanel.setLayout(footerPanelLayout);
        footerPanelLayout.setHorizontalGroup(
            footerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1690, Short.MAX_VALUE)
        );
        footerPanelLayout.setVerticalGroup(
            footerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(footerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-60, 760, 1690, 50));

        setBounds(0, 0, 1644, 844);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBiaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBiaMousePressed
        // TODO add your handling code here:
        btnHoaDon.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnBia.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        btnKhoBia.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnNhanVien.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnDoanhThu.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnKhuyenMai.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        
        body.setSelectedIndex(1);
    }//GEN-LAST:event_btnBiaMousePressed

    private void btnHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMousePressed
        // TODO add your handling code here:
        btnHoaDon.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        btnBia.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnKhoBia.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnNhanVien.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnDoanhThu.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnKhuyenMai.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        
        body.setSelectedIndex(0);
        
    }//GEN-LAST:event_btnHoaDonMousePressed

    private void btnKhoBiaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhoBiaMousePressed
        // TODO add your handling code here:
        btnHoaDon.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnBia.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnKhoBia.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        btnNhanVien.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnDoanhThu.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnKhuyenMai.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        
        body.setSelectedIndex(2);
    }//GEN-LAST:event_btnKhoBiaMousePressed

    private void btnDoanhThuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoanhThuMousePressed
        // TODO add your handling code here:
        btnHoaDon.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnBia.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnKhoBia.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnNhanVien.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnDoanhThu.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        btnKhuyenMai.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        
        body.setSelectedIndex(3);
    }//GEN-LAST:event_btnDoanhThuMousePressed

    private void btnNhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhanVienMousePressed
        // TODO add your handling code here:
        btnHoaDon.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnBia.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnKhoBia.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnNhanVien.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        btnDoanhThu.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnKhuyenMai.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        
        body.setSelectedIndex(4);
    }//GEN-LAST:event_btnNhanVienMousePressed

    private void btnKhuyenMaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhuyenMaiMousePressed
        // TODO add your handling code here:
        btnHoaDon.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnBia.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnKhoBia.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnNhanVien.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnDoanhThu.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        btnKhuyenMai.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        
        body.setSelectedIndex(5);
    }//GEN-LAST:event_btnKhuyenMaiMousePressed

    private void txtTenBia_QuanLyKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenBia_QuanLyKhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenBia_QuanLyKhoActionPerformed

    private void txtTenKho_QuanLyBiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKho_QuanLyBiaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtTenKho_QuanLyBiaActionPerformed

    private void cbxMaKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMaKhoActionPerformed
        // TODO add your handling code here:
        int index = cbxMaKho.getSelectedIndex();
        if(index >0){
            String getSelected = cbxMaKho.getItemAt(index);
        }
    }//GEN-LAST:event_cbxMaKhoActionPerformed

    private void cbxMaBiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMaBiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxMaBiaActionPerformed

    private void tblKhoBiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhoBiaMouseClicked
        // TODO add your handling code here:     
                int selectedRow = tblKhoBia.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Lỗi", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        txtMaKho_Kho.setText(tblKhoBia.getModel().getValueAt(selectedRow, 3).toString());
        txtTenKho_QuanLyBia.setText(tblKhoBia.getModel().getValueAt(selectedRow, 0).toString());
       
        txtMaBia_Kho.setText(tblKhoBia.getModel().getValueAt(selectedRow, 4).toString());
        txtTenBia_QuanLyKho.setText(tblKhoBia.getModel().getValueAt(selectedRow, 1).toString());
        txtSoLuong_Kho.setText(tblKhoBia.getModel().getValueAt(selectedRow, 2).toString());
        txtDiaChi_Kho.setText(tblKhoBia.getModel().getValueAt(selectedRow, 5).toString());
                                               
    }//GEN-LAST:event_tblKhoBiaMouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16MouseClicked

    private void labelMoiKhoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMoiKhoMouseExited
        // TODO add your handling code here:
        labelMoiKho.setForeground(Color.black);
    }//GEN-LAST:event_labelMoiKhoMouseExited

    private void labelMoiKhoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMoiKhoMouseEntered
        // TODO add your handling code here:
        labelMoiKho.setForeground(Color.red);
    }//GEN-LAST:event_labelMoiKhoMouseEntered

    private void labelXoaKhoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelXoaKhoMouseExited
        // TODO add your handling code here:
        labelXoaKho.setForeground(Color.black);
    }//GEN-LAST:event_labelXoaKhoMouseExited

    private void labelXoaKhoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelXoaKhoMouseEntered
        // TODO add your handling code here:
        labelXoaKho.setForeground(Color.red);
    }//GEN-LAST:event_labelXoaKhoMouseEntered

    private void labelSuaKhoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSuaKhoMouseExited
        // TODO add your handling code here:
        labelSuaKho.setForeground(Color.black);
    }//GEN-LAST:event_labelSuaKhoMouseExited

    private void labelSuaKhoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSuaKhoMouseEntered
        // TODO add your handling code here:
        labelSuaKho.setForeground(Color.red);
    }//GEN-LAST:event_labelSuaKhoMouseEntered

    private void labelThemKhoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelThemKhoMouseExited
        // TODO add your handling code here:
        labelThemKho.setForeground(Color.black);
    }//GEN-LAST:event_labelThemKhoMouseExited

    private void labelThemKhoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelThemKhoMouseEntered
        // TODO add your handling code here:
        labelThemKho.setForeground(Color.red);
    }//GEN-LAST:event_labelThemKhoMouseEntered

    private void tblTongKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTongKhoMouseClicked
        // TODO add your handling code here:
        
        int selectedRow = tblTongKho.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Lỗi", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        txtKho.setText(tblTongKho.getValueAt(selectedRow, 0).toString());
        txtTenKho.setText(tblTongKho.getValueAt(selectedRow, 2).toString());
       
        txtDiaChi.setText(tblTongKho.getValueAt(selectedRow, 1).toString());
        
        
        
    }//GEN-LAST:event_tblTongKhoMouseClicked

    private void labelMoiKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMoiKhoMouseClicked
        // TODO add your handling code here:
        txtKho.setText("");
        txtTenKho.setText("");
       
        txtDiaChi.setText("");
    }//GEN-LAST:event_labelMoiKhoMouseClicked

    private void labelThemKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelThemKhoMouseClicked
        // TODO add your handling code here:
        
        QLBangKho ql = getKhoFromInput();
        if (_iKhoService.createnewKho(ql) != null) {
            JOptionPane.showMessageDialog(this, "Thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất bại");
        }
        loadDataToTableKho();
    }//GEN-LAST:event_labelThemKhoMouseClicked

    private void labelXoaKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelXoaKhoMouseClicked
        // TODO add your handling code here:
        String selectedMa = txtKho.getText();
        _iKhoService.deleteKho(selectedMa);
        loadDataToTableKho();
    }//GEN-LAST:event_labelXoaKhoMouseClicked

    private void btnSreachInTongKhoBiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSreachInTongKhoBiaActionPerformed
        // TODO add your handling code here:
        String selectedKho =(String) cbxMaKho.getSelectedItem();
        List<QLToanBoKho> ds = _iKhoService.getKhoByMaKho(selectedKho);
        DefaultTableModel dtm = (DefaultTableModel) this.tblKhoBia.getModel();
        dtm.setRowCount(0);
        for(QLToanBoKho kho : ds){
            Object[] rowData = {
               
                kho.getTenKho(),
              
                kho.getTenBia(),
              
                kho.getSoLuong(),
                  kho.getMaKho(),
                  kho.getMaBia(),
                  kho.getDiaChi(),
            };
            
            dtm.addRow(rowData);
        }
    }//GEN-LAST:event_btnSreachInTongKhoBiaActionPerformed

    private void btnLoadLaiTrongTongBIaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadLaiTrongTongBIaActionPerformed
        // TODO add your handling code here:
       loadDataToTableKho();
    }//GEN-LAST:event_btnLoadLaiTrongTongBIaActionPerformed

    private void labelSuaKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSuaKhoMouseClicked
        // TODO add your handling code here:
        QLToanBoKho ql = new QLToanBoKho();
    }//GEN-LAST:event_labelSuaKhoMouseClicked

    private void labelThemKho2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelThemKho2MouseClicked
        // TODO add your handling code here:
        QLKhoChiTiet ql = getKhoChiTietFromInput();
        if (_iKhoService.createKhoCT(ql) != null) {
            JOptionPane.showMessageDialog(this, "Thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất bại");
        }
        loadDataToTableKho();
        
    }//GEN-LAST:event_labelThemKho2MouseClicked

    private void labelThemKho2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelThemKho2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_labelThemKho2MouseEntered

    private void labelThemKho2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelThemKho2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_labelThemKho2MouseExited

    private void labelSuaKho2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSuaKho2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_labelSuaKho2MouseClicked

    private void labelSuaKho2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSuaKho2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_labelSuaKho2MouseEntered

    private void labelSuaKho2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSuaKho2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_labelSuaKho2MouseExited

    private void labelXoaKho2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelXoaKho2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_labelXoaKho2MouseClicked

    private void labelXoaKho2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelXoaKho2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_labelXoaKho2MouseEntered

    private void labelXoaKho2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelXoaKho2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_labelXoaKho2MouseExited

    private void labelMoiKho2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMoiKho2MouseClicked
        // TODO add your handling code here:
        txtMabia2.setText("");
        txtMaKho2.setText("");
        txtSoLuong2.setText("");
    }//GEN-LAST:event_labelMoiKho2MouseClicked

    private void labelMoiKho2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMoiKho2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_labelMoiKho2MouseEntered

    private void labelMoiKho2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMoiKho2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_labelMoiKho2MouseExited

    private void tblKhoChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhoChiTietMouseClicked
        // TODO add your handling code here:
        
         int selectedRow = tblKhoChiTiet.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Lỗi", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        txtMabia2.setText(tblKhoChiTiet.getValueAt(selectedRow, 0).toString());
        txtMaKho2.setText(tblKhoChiTiet.getValueAt(selectedRow, 1).toString());
       
        txtSoLuong2.setText(tblKhoChiTiet.getValueAt(selectedRow, 2).toString());
        
    }//GEN-LAST:event_tblKhoChiTietMouseClicked

    private void tblNSXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNSXMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblNSX.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Lỗi", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        txtMaNSX_QLNSX.setText(tblNSX.getValueAt(selectedRow, 0).toString());
        txtTenNSX_QLNSX.setText(tblNSX.getValueAt(selectedRow, 1).toString());
        
        
    }//GEN-LAST:event_tblNSXMouseClicked

    private void btnAddNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNSXActionPerformed
        // TODO add your handling code here:
        QLNSX ql = getNSXFromInput();
        if (iNSXService.createNSX(ql) != null) {
            JOptionPane.showMessageDialog(this, "Thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất bại");
        }
        loadTblNSX();
    }//GEN-LAST:event_btnAddNSXActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        txtMaNSX_QLNSX.setText("");
        txtTenNSX_QLNSX.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblNhanVien1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVien1MousePressed
        // TODO add your handling code here:
        int selectedRow = tblNhanVien1.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Chọn 1 nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        txtMaNV1.setText(tblNhanVien1.getValueAt(selectedRow, 0).toString());
        txtTenNV1.setText(tblNhanVien1.getValueAt(selectedRow, 1).toString());
        if (tblNhanVien1.getValueAt(selectedRow, 2).toString().equalsIgnoreCase("Nam")) {
            rdoNam1.setSelected(true);
        } else {
            rdoNu1.setSelected(true);
        }
        txtNgaySinh1.setText(tblNhanVien1.getValueAt(selectedRow, 3).toString());
        txtDiaChi2.setText(tblNhanVien1.getValueAt(selectedRow, 4).toString());
        txtSDT1.setText(tblNhanVien1.getValueAt(selectedRow, 5).toString());
        txtEmail1.setText(tblNhanVien1.getValueAt(selectedRow, 7).toString());
        txtCMND1.setText(tblNhanVien1.getValueAt(selectedRow, 6).toString());
        txtGhiChu1.setText(tblNhanVien1.getValueAt(selectedRow, 9).toString());

        //Load ảnh
        lblAnhNhanVien1.setText("");
        anhNhanVien = (String) model.getValueAt(selectedRow, 8);
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/icon/" +  anhNhanVien));
        Image img = imgIcon.getImage();
        img.getScaledInstance(lblAnhNhanVien1.getWidth(), lblAnhNhanVien1.getY(), 0);
        lblAnhNhanVien1.setIcon(imgIcon);
    }//GEN-LAST:event_tblNhanVien1MousePressed

    private void btnThemNhanVien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVien1ActionPerformed
        // TODO add your handling code here:
        boolean check = validateThongTinNhanVien();
        if (check == false) {
            return;
        }
        //mã trùng
        boolean checkMaTrung = true;
        ArrayList<QLNhanVien> lst = nhanVienService.fillListNhanViens();
        for (QLNhanVien qLNhanVien : lst) {
            if (qLNhanVien.getMaQLNV().equalsIgnoreCase(txtMaNV1.getText())) {
                txtMaNV1.setText("Mã trùng");
                txtMaNV1.setForeground(Color.red);
                checkMaTrung = false;
            }
        }
        if (checkMaTrung == false) {
            return;
        }
        txtMaNV1.setForeground(Color.black);
        txtTenNV1.setForeground(Color.black);
        txtNgaySinh1.setForeground(Color.black);
        txtDiaChi2.setForeground(Color.black);
        txtSDT1.setForeground(Color.black);
        txtEmail1.setForeground(Color.black);
        txtCMND1.setForeground(Color.black);
        txtGhiChu1.setForeground(Color.black);

        String maNV = txtMaNV1.getText();
        String ten = txtTenNV1.getText();
        String gioiTinh = "Nam";
        if (rdoNu1.isSelected() == true) {
            gioiTinh = "Nữ";
        }
        String ngaySinh = txtNgaySinh1.getText();
        String diaChi = txtDiaChi2.getText();
        String sdt = txtSDT1.getText();
        String email = txtEmail1.getText();
        String cmnd = txtCMND1.getText();
        String ghiChu = txtGhiChu1.getText();

        QLNhanVien qlnv = new QLNhanVien(maNV, ten, gioiTinh, ngaySinh, diaChi, sdt, cmnd, email, anhNhanVien, ghiChu);

        themThongTinNhanVien(qlnv);
    }//GEN-LAST:event_btnThemNhanVien1ActionPerformed

    private void btnSuaNhanVien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNhanVien1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblNhanVien1.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Chọn 1 nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String maCanSua = tblNhanVien1.getValueAt(selectedRow, 0).toString();

        boolean check = validateThongTinNhanVien();
        if (check == false) {
            return;
        }
        //check mã tồn tại
        boolean checkMa = false;
        ArrayList<QLNhanVien> lst = nhanVienService.fillListNhanViens();
        for (QLNhanVien qLNhanVien : lst) {
            if (qLNhanVien.getMaQLNV().equalsIgnoreCase(maCanSua)) {
                checkMa = true;
            }
        }
        if (checkMa == false) {
            JOptionPane.showMessageDialog(this, "Mã không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        txtMaNV1.setForeground(Color.black);
        txtTenNV1.setForeground(Color.black);
        txtNgaySinh1.setForeground(Color.black);
        txtDiaChi2.setForeground(Color.black);
        txtSDT1.setForeground(Color.black);
        txtEmail1.setForeground(Color.black);
        txtCMND1.setForeground(Color.black);
        txtGhiChu1.setForeground(Color.black);

        String maNV = txtMaNV1.getText();
        String ten = txtTenNV1.getText();
        String gioiTinh = "Nam";
        if (rdoNu1.isSelected() == true) {
            gioiTinh = "Nữ";
        }
        String ngaySinh = txtNgaySinh1.getText();
        String diaChi = txtDiaChi2.getText();
        String sdt = txtSDT1.getText();
        String email = txtEmail1.getText();
        String cmnd = txtCMND1.getText();
        String ghiChu = txtGhiChu1.getText();

        QLNhanVien qlnv = new QLNhanVien(maNV, ten, gioiTinh, ngaySinh, diaChi, sdt, cmnd, email, anhNhanVien, ghiChu);

        suaThongTinNhanVien(maCanSua, qlnv);
        selectedRow = -1;
    }//GEN-LAST:event_btnSuaNhanVien1ActionPerformed

    private void btnXoaNhanVien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhanVien1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblNhanVien1.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Chọn 1 nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String maCanXoa = tblNhanVien1.getValueAt(selectedRow, 0).toString();

        xoaThongTinNhanVien(maCanXoa);
        selectedRow = -1;
    }//GEN-LAST:event_btnXoaNhanVien1ActionPerformed

    private void btnTaoMoiNhanVien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiNhanVien1ActionPerformed
        // TODO add your handling code here:
        txtMaNV1.setText("");
        txtTenNV1.setText("");
        rdoNam1.setSelected(true);
        txtNgaySinh1.setText("");
        txtDiaChi2.setText("");
        txtSDT1.setText("");
        txtCMND1.setText("");
        txtEmail1.setText("");
        txtGhiChu1.setText("");
        //lblAnh
        lblAnhNhanVien1.setIcon(null);
        lblAnhNhanVien1.setText("Hình ảnh");
        anhNhanVien = null;

        txtMaNV1.setForeground(Color.black);
        txtTenNV1.setForeground(Color.black);
        txtNgaySinh1.setForeground(Color.black);
        txtDiaChi2.setForeground(Color.black);
        txtSDT1.setForeground(Color.black);
        txtEmail1.setForeground(Color.black);
        txtCMND1.setForeground(Color.black);
        txtGhiChu1.setForeground(Color.black);
    }//GEN-LAST:event_btnTaoMoiNhanVien1ActionPerformed

    private void lblAnhNhanVien1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhNhanVien1MouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            JFileChooser jfc = new JFileChooser("D:\\New folder (8)\\GitTest\\DuAn1_Hoang\\src\\icon");
            jfc.showOpenDialog(null);
            File file = jfc.getSelectedFile();
            Image img = ImageIO.read(file);
            anhNhanVien = file.getName();
            lblAnhNhanVien1.setText("");
            int width = lblAnhNhanVien1.getWidth();
            int height = lblAnhNhanVien1.getHeight();
            lblAnhNhanVien1.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_lblAnhNhanVien1MouseClicked

    private void txtTimKiem1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiem1CaretUpdate
        // TODO add your handling code here:
        timKiem = txtTimKiem1.getText();
        if (timKiem.trim().isEmpty()) {
            model.setNumRows(0);
            return;
        }
        loadTblTimKiem(timKiem);
    }//GEN-LAST:event_txtTimKiem1CaretUpdate

    private void txtTimKiem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiem1MouseClicked
        // TODO add your handling code here:
        txtTimKiem1.setText("");
    }//GEN-LAST:event_txtTimKiem1MouseClicked

    private void cbxMaKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxMaKhoMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbxMaKhoMouseClicked

    private void cbxMaKhoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxMaKhoMouseReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbxMaKhoMouseReleased

    private void cbxMaKhoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxMaKhoMousePressed
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) tblKhoBia.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dtm);
        tblKhoBia.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(cbxMaKho.getSelectedItem().toString()));
        loadDataToTableKho();
    }//GEN-LAST:event_cbxMaKhoMousePressed

    
    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel biaPanel;
    private javax.swing.JTabbedPane body;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton btnAddNSX;
    private javax.swing.JPanel btnBia;
    private javax.swing.JPanel btnDoanhThu;
    private javax.swing.JPanel btnHoaDon;
    private javax.swing.JPanel btnKhoBia;
    private javax.swing.JPanel btnKhuyenMai;
    private javax.swing.JButton btnLoadLaiTrongTongBIa;
    private javax.swing.JPanel btnNhanVien;
    private javax.swing.JButton btnSreachInTongKhoBia;
    private javax.swing.JButton btnSuaNhanVien1;
    private javax.swing.JButton btnTaoMoiNhanVien1;
    private javax.swing.JButton btnThemNhanVien1;
    private javax.swing.JButton btnXoaNhanVien1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbxMaBia;
    private javax.swing.JComboBox<String> cbxMaKho;
    private javax.swing.JPanel doanhThuPanel;
    private javax.swing.JPanel footerPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel hoaDonPanel;
    private javax.swing.JPanel hoaDonPanel1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel khoBiaPanel;
    private javax.swing.JPanel khuyenMaiPanel;
    private javax.swing.JPanel khuyenMaiPanel1;
    private javax.swing.JLabel labelMoiKho;
    private javax.swing.JLabel labelMoiKho2;
    private javax.swing.JLabel labelSuaKho;
    private javax.swing.JLabel labelSuaKho2;
    private javax.swing.JLabel labelThemKho;
    private javax.swing.JLabel labelThemKho2;
    private javax.swing.JLabel labelXoaKho;
    private javax.swing.JLabel labelXoaKho2;
    private javax.swing.JLabel lblAnhNhanVien1;
    private javax.swing.JPanel navbarPanel;
    private javax.swing.JPanel nhanVienPanel;
    private javax.swing.JPanel panelMoiBia;
    private javax.swing.JPanel panelMoiBia1;
    private javax.swing.JPanel panelSuaKho;
    private javax.swing.JPanel panelSuaKho1;
    private javax.swing.JPanel panelThemKho;
    private javax.swing.JPanel panelThemKho2;
    private javax.swing.JPanel panelXoaKho;
    private javax.swing.JPanel panelXoaKho1;
    private javax.swing.JRadioButton rdoNam1;
    private javax.swing.JRadioButton rdoNu1;
    private javax.swing.JTable tblKhoBia;
    private javax.swing.JTable tblKhoChiTiet;
    private javax.swing.JTable tblKho_TongBia;
    private javax.swing.JTable tblNSX;
    private javax.swing.JTable tblNhanVien1;
    private javax.swing.JTable tblTongKho;
    private javax.swing.JTextField txtCMND1;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDiaChi2;
    private javax.swing.JTextArea txtDiaChi_Kho;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextArea txtGhiChu1;
    private javax.swing.JTextField txtKho;
    private javax.swing.JTextField txtMaBia_Kho;
    private javax.swing.JTextField txtMaKho2;
    private javax.swing.JTextField txtMaKho_Kho;
    private javax.swing.JTextField txtMaNSX_QLNSX;
    private javax.swing.JTextField txtMaNV1;
    private javax.swing.JTextField txtMabia2;
    private javax.swing.JTextField txtNgaySinh1;
    private javax.swing.JTextField txtSDT1;
    private javax.swing.JTextField txtSoLuong2;
    private javax.swing.JTextField txtSoLuong_Kho;
    private javax.swing.JTextField txtTenBia_QuanLyKho;
    private javax.swing.JTextField txtTenKho;
    private javax.swing.JTextField txtTenKho_QuanLyBia;
    private javax.swing.JTextField txtTenNSX_QLNSX;
    private javax.swing.JTextField txtTenNV1;
    private javax.swing.JTextField txtTimKiem1;
    // End of variables declaration//GEN-END:variables
}
