/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DomainModels.Kho.ToanBoKho;
import DomainModels.Kho.BangKho;
import DomainModels.Kho.TongSanPhamKho;
import Sevices.impSevices.IKhoService;
import Sevices.impSevices.INhanVienService;
import Sevices.KhoService;
import Sevices.NhanVienService;
import ViewModels.Kho.DSBia;
import ViewModels.Kho.QLBangKho;
import ViewModels.Kho.QLToanBoKho;
import ViewModels.QLNhanVien;
import ViewModels.Kho.QLTongSPKho;
import java.awt.Color;
import java.awt.Panel;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
    DefaultTableModel model;
    
    
    private int _currentPage;
    private int _totalPages;
    private final int _pageSize;
    public Display() {
        initComponents();
        
        _iKhoService = new KhoService();
        nhanVienService = new NhanVienService();
        
        _currentPage = 1;
        _pageSize = 10;
        
        model = (DefaultTableModel) tblNhanVienGiang.getModel();        
        loadDataToTableKho();
        loadTblNhanVien();
        loadDataToCBXMaKho();
        loadDataToCBXMaBia();
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
    
    private void loadDataToTableKho(){
//        List<ToanBoKho> ds = _iKhoService.getAllKho();
//        List<TongSanPhamKho> tongKho = _iKhoService.getTotalBeer();
//        List<TongKho> khoTong = _iKhoService.getTongKho();
    
        List<QLToanBoKho> ds = _iKhoService.getAllKho();
        List<QLTongSPKho> tongKho = _iKhoService.getTongSPKho();
        List<QLBangKho> bangKho = _iKhoService.getBangKho();
        DefaultTableModel dtm = (DefaultTableModel) this.tblKhoBia.getModel();
        DefaultTableModel dtm2 = (DefaultTableModel) this.tblKho_TongBia.getModel();
        DefaultTableModel dtm3 = (DefaultTableModel) this.tblTongKho.getModel();
        dtm.setRowCount(0);
        dtm2.setRowCount(0);
        dtm3.setRowCount(0);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        jTable1 = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();
        doanhThuPanel = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        nhanVienPanel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblNhanVienGiang = new javax.swing.JTable();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtMaNVGiang = new javax.swing.JTextField();
        txtTenNVGiang = new javax.swing.JTextField();
        txtNgaySinhGiang = new javax.swing.JTextField();
        txtDiaChiGiang = new javax.swing.JTextField();
        txtSDTGiang = new javax.swing.JTextField();
        rdoNamGiang = new javax.swing.JRadioButton();
        rdoNuGiang = new javax.swing.JRadioButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtGhiChuGiang = new javax.swing.JTextArea();
        txtCMNDGiang = new javax.swing.JTextField();
        txtEmailGiang = new javax.swing.JTextField();
        btnThemNhanVienGiang = new javax.swing.JButton();
        btnSuaNhanVienGiang = new javax.swing.JButton();
        btnXoaNhanVienGiang = new javax.swing.JButton();
        btnTaoMoiNhanVienGiang = new javax.swing.JButton();
        lblAnhNhanVienGiang = new javax.swing.JLabel();
        khuyenMaiPanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
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
        jLabel16.setText("   Khuyen Mai");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnKhuyenMaiLayout = new javax.swing.GroupLayout(btnKhuyenMai);
        btnKhuyenMai.setLayout(btnKhuyenMaiLayout);
        btnKhuyenMaiLayout.setHorizontalGroup(
            btnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnKhuyenMaiLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnKhuyenMaiLayout.setVerticalGroup(
            btnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnKhuyenMaiLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
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
                .addContainerGap(3620, Short.MAX_VALUE))
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
                .addContainerGap(3686, Short.MAX_VALUE))
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(350, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tổng sản phẩm từng kho", jPanel7);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(693, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
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
                .addContainerGap(2217, Short.MAX_VALUE))
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
                .addContainerGap(3596, Short.MAX_VALUE))
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
        jLabel19.setText("Quản Lý Nhân Viên");

        jPanel4.setBackground(new java.awt.Color(255, 255, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblNhanVienGiang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaNV", "TenNV", "Giới Tính", "Ngày sinh", "Địa Chỉ ", "Số điện thoại", "CMND", "Email", "GhiChu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblNhanVienGiang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNhanVienGiangMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(tblNhanVienGiang);

        jLabel42.setText("Mã Nhân Viên:");

        jLabel43.setText("Họ Tên:");

        jLabel44.setText("Giới Tính:");

        jLabel45.setText("Ngày sinh:");

        jLabel46.setText("Địa Chỉ:");

        jLabel47.setText("Số điện thoại");

        jLabel48.setText("CMND:");

        jLabel49.setText("Email:");

        jLabel50.setText("Ghi Chú:");

        buttonGroup1.add(rdoNamGiang);
        rdoNamGiang.setSelected(true);
        rdoNamGiang.setText("Nam");

        buttonGroup1.add(rdoNuGiang);
        rdoNuGiang.setText("Nữ");

        txtGhiChuGiang.setColumns(20);
        txtGhiChuGiang.setRows(5);
        jScrollPane7.setViewportView(txtGhiChuGiang);

        btnThemNhanVienGiang.setText("Thêm");

        btnSuaNhanVienGiang.setText("Sửa");

        btnXoaNhanVienGiang.setText("Xóa");

        btnTaoMoiNhanVienGiang.setText("Mới");
        btnTaoMoiNhanVienGiang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiNhanVienGiangActionPerformed(evt);
            }
        });

        lblAnhNhanVienGiang.setText("Ảnh");
        lblAnhNhanVienGiang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDiaChiGiang, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgaySinhGiang, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNVGiang, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenNVGiang, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(rdoNamGiang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addComponent(rdoNuGiang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSDTGiang))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(btnThemNhanVienGiang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSuaNhanVienGiang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoaNhanVienGiang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTaoMoiNhanVienGiang)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtEmailGiang))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCMNDGiang, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane7)
                                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                                .addComponent(lblAnhNhanVienGiang, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(238, 238, 238)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(jLabel48)
                            .addComponent(txtMaNVGiang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCMNDGiang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(jLabel49)
                            .addComponent(txtTenNVGiang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailGiang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(jLabel50)
                            .addComponent(rdoNamGiang)
                            .addComponent(rdoNuGiang))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel45)
                                    .addComponent(txtNgaySinhGiang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel46)
                                    .addComponent(txtDiaChiGiang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAnhNhanVienGiang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(txtSDTGiang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanVienGiang)
                    .addComponent(btnSuaNhanVienGiang)
                    .addComponent(btnXoaNhanVienGiang)
                    .addComponent(btnTaoMoiNhanVienGiang))
                .addGap(60, 60, 60)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout nhanVienPanelLayout = new javax.swing.GroupLayout(nhanVienPanel);
        nhanVienPanel.setLayout(nhanVienPanelLayout);
        nhanVienPanelLayout.setHorizontalGroup(
            nhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanVienPanelLayout.createSequentialGroup()
                .addGroup(nhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nhanVienPanelLayout.createSequentialGroup()
                        .addGap(608, 608, 608)
                        .addComponent(jLabel19))
                    .addGroup(nhanVienPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(2993, Short.MAX_VALUE))
        );
        nhanVienPanelLayout.setVerticalGroup(
            nhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanVienPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel19)
                .addGap(32, 32, 32)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        body.addTab("tab6", nhanVienPanel);

        khuyenMaiPanel.setBackground(new java.awt.Color(255, 255, 153));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 102, 0));
        jLabel20.setText("Quản Lý Khuyến Mãi");

        javax.swing.GroupLayout khuyenMaiPanelLayout = new javax.swing.GroupLayout(khuyenMaiPanel);
        khuyenMaiPanel.setLayout(khuyenMaiPanelLayout);
        khuyenMaiPanelLayout.setHorizontalGroup(
            khuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khuyenMaiPanelLayout.createSequentialGroup()
                .addGap(534, 534, 534)
                .addComponent(jLabel20)
                .addContainerGap(3585, Short.MAX_VALUE))
        );
        khuyenMaiPanelLayout.setVerticalGroup(
            khuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khuyenMaiPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel20)
                .addContainerGap(600, Short.MAX_VALUE))
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

    private void tblNhanVienGiangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienGiangMousePressed
        // TODO add your handling code here:
        int selectedRow = tblNhanVienGiang.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Lỗi", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        txtMaNVGiang.setText(tblNhanVienGiang.getValueAt(selectedRow, 0).toString());
        txtTenNVGiang.setText(tblNhanVienGiang.getValueAt(selectedRow, 1).toString());
        if (tblNhanVienGiang.getValueAt(selectedRow, 2).toString().equalsIgnoreCase("Nam")) {
            rdoNamGiang.setSelected(true);
        } else {
            rdoNuGiang.setSelected(true);
        }
        txtNgaySinhGiang.setText(tblNhanVienGiang.getValueAt(selectedRow, 3).toString());
        txtDiaChiGiang.setText(tblNhanVienGiang.getValueAt(selectedRow, 4).toString());
        txtSDTGiang.setText(tblNhanVienGiang.getValueAt(selectedRow, 5).toString());
        txtCMNDGiang.setText(tblNhanVienGiang.getValueAt(selectedRow, 6).toString());
        txtEmailGiang.setText(tblNhanVienGiang.getValueAt(selectedRow, 7).toString());
        txtGhiChuGiang.setText(tblNhanVienGiang.getValueAt(selectedRow, 8).toString());
    }//GEN-LAST:event_tblNhanVienGiangMousePressed

    private void btnTaoMoiNhanVienGiangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiNhanVienGiangActionPerformed
        // TODO add your handling code here:
        txtMaNVGiang.setText("");
        txtTenNVGiang.setText("");
        rdoNamGiang.setSelected(true);
        txtNgaySinhGiang.setText("");
        txtDiaChiGiang.setText("");
        txtSDTGiang.setText("");
        txtCMNDGiang.setText("");
        txtEmailGiang.setText("");
        txtGhiChuGiang.setText("");
    }//GEN-LAST:event_btnTaoMoiNhanVienGiangActionPerformed

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

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Display().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel biaPanel;
    private javax.swing.JTabbedPane body;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JPanel btnBia;
    private javax.swing.JPanel btnDoanhThu;
    private javax.swing.JPanel btnHoaDon;
    private javax.swing.JPanel btnKhoBia;
    private javax.swing.JPanel btnKhuyenMai;
    private javax.swing.JButton btnLoadLaiTrongTongBIa;
    private javax.swing.JPanel btnNhanVien;
    private javax.swing.JButton btnSreachInTongKhoBia;
    private javax.swing.JButton btnSuaNhanVienGiang;
    private javax.swing.JButton btnTaoMoiNhanVienGiang;
    private javax.swing.JButton btnThemNhanVienGiang;
    private javax.swing.JButton btnXoaNhanVienGiang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxMaBia;
    private javax.swing.JComboBox<String> cbxMaKho;
    private javax.swing.JPanel doanhThuPanel;
    private javax.swing.JPanel footerPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel hoaDonPanel;
    private javax.swing.JPanel hoaDonPanel1;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel khoBiaPanel;
    private javax.swing.JPanel khuyenMaiPanel;
    private javax.swing.JLabel labelMoiKho;
    private javax.swing.JLabel labelSuaKho;
    private javax.swing.JLabel labelThemKho;
    private javax.swing.JLabel labelXoaKho;
    private javax.swing.JLabel lblAnhNhanVienGiang;
    private javax.swing.JPanel navbarPanel;
    private javax.swing.JPanel nhanVienPanel;
    private javax.swing.JPanel panelMoiBia;
    private javax.swing.JPanel panelSuaKho;
    private javax.swing.JPanel panelThemKho;
    private javax.swing.JPanel panelXoaKho;
    private javax.swing.JRadioButton rdoNamGiang;
    private javax.swing.JRadioButton rdoNuGiang;
    private javax.swing.JTable tblKhoBia;
    private javax.swing.JTable tblKho_TongBia;
    private javax.swing.JTable tblNhanVienGiang;
    private javax.swing.JTable tblTongKho;
    private javax.swing.JTextField txtCMNDGiang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDiaChiGiang;
    private javax.swing.JTextArea txtDiaChi_Kho;
    private javax.swing.JTextField txtEmailGiang;
    private javax.swing.JTextArea txtGhiChuGiang;
    private javax.swing.JTextField txtKho;
    private javax.swing.JTextField txtMaBia_Kho;
    private javax.swing.JTextField txtMaKho_Kho;
    private javax.swing.JTextField txtMaNVGiang;
    private javax.swing.JTextField txtNgaySinhGiang;
    private javax.swing.JTextField txtSDTGiang;
    private javax.swing.JTextField txtSoLuong_Kho;
    private javax.swing.JTextField txtTenBia_QuanLyKho;
    private javax.swing.JTextField txtTenKho;
    private javax.swing.JTextField txtTenKho_QuanLyBia;
    private javax.swing.JTextField txtTenNVGiang;
    // End of variables declaration//GEN-END:variables
}
