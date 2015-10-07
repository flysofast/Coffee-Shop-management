/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Account;
import entities.Functions;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import models.AccountEntityManager;
import models.FunctionEntityManager;
import models.RoleEntityManager;

/**
 *
 * @author ADMIN
 */
public final class InternalMain extends javax.swing.JFrame {

    /**
     * Creates new form InternalMain
     */
    public InternalMain(LoginForm login, Account a) {
        initComponents();
        setIcon();
        setLocationRelativeTo(null);
        dtpProgram.setPreferredSize(new Dimension(1359, 628));
        lblUserName.setText(a.getUsername());
        lblUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
        HomeFormDB hf = new HomeFormDB();
        this.dtpProgram.add(hf);
        try {
            hf.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(InternalMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        hf.show();

        grantPermission();
    }

    void menuBarAction(JInternalFrame frame) {
        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == frame) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(frame);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(frame);
        frame.show();
    }

    /**
     * Hiển thị menu chức năng ứng với quyền của account
     */
    void grantPermission() {
        RoleEntityManager roleModel = new RoleEntityManager();
        FunctionEntityManager funModel = new FunctionEntityManager();
        Set<Functions> list = roleModel.getFunctionList(AccountEntityManager.currentAccount.getRole());

        //Quản lý tài khoản
        if (list.contains(funModel.find(FunctionEntityManager.ACCOUNT_MANAGEMENT))) {
            mniUserManagement.setVisible(true);
        }
        //Cấp quyền tài khoản
        if (list.contains(funModel.find(FunctionEntityManager.ACCOUNT_PERMISSION))) {
            mniUserPermission.setVisible(true);
        }
        //Quản lý kho hàng
        if (list.contains(funModel.find(FunctionEntityManager.PRODUCT_MANAGEMENT))) {
            mniProductManagement.setVisible(true);
        }
        //Xem hóa đơn
        if (list.contains(funModel.find(FunctionEntityManager.BILL_OVERVIEW))) {
            mniBill.setVisible(true);
        }
        //Quản lý đơn vị
        if (list.contains(funModel.find(FunctionEntityManager.UNIT_MANAGEMENT))) {
            mniUnit.setVisible(true);
        }
        //Quản lý nhà cung cấp
        if (list.contains(funModel.find(FunctionEntityManager.PUBLISHER_MANAGEMENT))) {
            mniPublisher.setVisible(true);
        }
        //Bán hàng
        if (list.contains(funModel.find(FunctionEntityManager.SELL_PRODUCTS))) {
            mniPay.setVisible(true);
        }
        //Quản lý danh mục
        if (list.contains(funModel.find(FunctionEntityManager.CATEGORY_MANAGEMENT))) {
            mniCategory.setVisible(true);
        }
        //Thống kê - Menu làm ngược lại với item
        if (!list.contains(funModel.find(FunctionEntityManager.REPORT))) {
            mnuReport.setVisible(false);
        }
        //Sao lưu dữ liệu
        if (list.contains(funModel.find(FunctionEntityManager.BACKUP))) {
            mniBackup.setVisible(true);
        }
        //Phục hồi dữ liệu
        if (list.contains(funModel.find(FunctionEntityManager.RESTORE))) {
            mniRestore.setVisible(true);
        }
        //Xem nhật ký hệ thống
        if (list.contains(funModel.find(FunctionEntityManager.DIARY))) {
            mniDiary.setVisible(true);
        }

        showGrantedFunctions();
    }

    /**
     * Xử lý ẩn những menu không có chức năng nào khả dụng
     */
    void showGrantedFunctions() {
        for (int j = 0; j < mnuCoffe.getMenuCount(); j++) {
            JMenu menu = mnuCoffe.getMenu(j);

            //Nếu menu có item con thì tiến hành kiểm tra
            if (menu.getItemCount() > 0) {
                int i = 0;
                for (; i < menu.getItemCount(); i++) {
                    JMenuItem item = menu.getItem(i);
                    if (item.isVisible()) {
                        break;
                    }
                }

                //Tất cả item đều bị ẩn thì ẩn luôn menu
                if (i == menu.getItemCount()) {
                    menu.setVisible(false);
                }
            }
        }
    }

    public InternalMain() {
        initComponents();
        setLocationRelativeTo(null);
        dtpProgram.setPreferredSize(new Dimension(1359, 628));
        HomeFormDB hf = new HomeFormDB();
        this.dtpProgram.add(hf);
        try {
            hf.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(InternalMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        hf.show();

    }

    public String curentTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String timeString = dateFormat.format(date);
        return timeString;
    }

    /**
     * .
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlStatus = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dtpProgram = new javax.swing.JDesktopPane();
        mnuCoffe = new javax.swing.JMenuBar();
        mnuHome = new javax.swing.JMenu();
        mnuAccount = new javax.swing.JMenu();
        mniUserManagement = new javax.swing.JMenuItem();
        mniUserPermission = new javax.swing.JMenuItem();
        mnuProduct = new javax.swing.JMenu();
        mniProductManagement = new javax.swing.JMenuItem();
        mnuBill = new javax.swing.JMenu();
        mniBill = new javax.swing.JMenuItem();
        mniUnit = new javax.swing.JMenuItem();
        mniCategory = new javax.swing.JMenuItem();
        mniPublisher = new javax.swing.JMenuItem();
        mniPay = new javax.swing.JMenuItem();
        mnuReport = new javax.swing.JMenu();
        mnuSystem = new javax.swing.JMenu();
        mniBackup = new javax.swing.JMenuItem();
        mniRestore = new javax.swing.JMenuItem();
        mniDiary = new javax.swing.JMenuItem();
        mnuHelp = new javax.swing.JMenu();
        mnuExit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm quản lý Coffee");
        setIconImages(null);
        setMinimumSize(new java.awt.Dimension(1366, 728));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlStatus.setMinimumSize(new java.awt.Dimension(1359, 34));
        pnlStatus.setPreferredSize(new java.awt.Dimension(1359, 34));
        pnlStatus.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(89, 89, 89), new java.awt.Color(89, 89, 89)));
        jPanel1.setMinimumSize(new java.awt.Dimension(359, 34));
        jPanel1.setPreferredSize(new java.awt.Dimension(159, 34));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Trạng thái: ");
        jPanel1.add(jLabel1, new java.awt.GridBagConstraints());

        lblStatus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblStatus.setText("Active");
        jPanel1.add(lblStatus, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlStatus.add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(89, 89, 89), new java.awt.Color(89, 89, 89)));
        jPanel2.setMinimumSize(new java.awt.Dimension(700, 34));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 34));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Xin chào: ");
        jPanel2.add(jLabel2, new java.awt.GridBagConstraints());

        lblUserName.setText("Thái Thành Nam");
        jPanel2.add(lblUserName, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pnlStatus.add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(89, 89, 89), new java.awt.Color(89, 89, 89)));
        jPanel3.setMinimumSize(new java.awt.Dimension(500, 34));
        jPanel3.setPreferredSize(new java.awt.Dimension(800, 34));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Cám ơn các bạn đã sử dụng phần mềm quản lý Coffee phiên bản 1.0.0.bla.bla... | Thông tin tư vấn liên hệ anh Nam mập đẹp trai.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel3.add(jLabel3, gridBagConstraints);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/smile.png"))); // NOI18N
        jLabel4.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel3.add(jLabel4, gridBagConstraints);

        pnlStatus.add(jPanel3, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(pnlStatus, gridBagConstraints);

        dtpProgram.setMinimumSize(new java.awt.Dimension(1359, 628));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(dtpProgram, gridBagConstraints);

        mnuCoffe.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(89, 89, 89), new java.awt.Color(89, 89, 89)));
        mnuCoffe.setForeground(new java.awt.Color(89, 89, 89));

        mnuHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trangchu.png"))); // NOI18N
        mnuHome.setText("Giới thiệu");
        mnuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuHomeMouseClicked(evt);
            }
        });
        mnuCoffe.add(mnuHome);

        mnuAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nguoidung.png"))); // NOI18N
        mnuAccount.setText("Người dùng");

        mniUserManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien.png"))); // NOI18N
        mniUserManagement.setText("Quản lý nhân viên");
        mniUserManagement.setVisible(false);
        mniUserManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUserManagementActionPerformed(evt);
            }
        });
        mnuAccount.add(mniUserManagement);

        mniUserPermission.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/congviec.png"))); // NOI18N
        mniUserPermission.setText("Quản lý Role");
        mniUserPermission.setVisible(false);
        mniUserPermission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUserPermissionActionPerformed(evt);
            }
        });
        mnuAccount.add(mniUserPermission);

        mnuCoffe.add(mnuAccount);

        mnuProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sanpham.png"))); // NOI18N
        mnuProduct.setText("Sản phẩm");

        mniProductManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/thucdon.png"))); // NOI18N
        mniProductManagement.setText("Quản lý sản phẩm");
        mniProductManagement.setVisible(false);
        mniProductManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniProductManagementActionPerformed(evt);
            }
        });
        mnuProduct.add(mniProductManagement);

        mnuCoffe.add(mnuProduct);

        mnuBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/thanhtoan.png"))); // NOI18N
        mnuBill.setText("Thanh toán");

        mniBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hoadon.png"))); // NOI18N
        mniBill.setText("Xem hóa đơn");
        mniBill.setVisible(false);
        mniBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBillActionPerformed(evt);
            }
        });
        mnuBill.add(mniBill);

        mniUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/donvi.png"))); // NOI18N
        mniUnit.setText("Quản lý đơn vị");
        mniUnit.setVisible(false);
        mniUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUnitActionPerformed(evt);
            }
        });
        mnuBill.add(mniUnit);

        mniCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/danhmuc.png"))); // NOI18N
        mniCategory.setText("Quản lý danh mục");
        mniCategory.setVisible(false);
        mniCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCategoryActionPerformed(evt);
            }
        });
        mnuBill.add(mniCategory);

        mniPublisher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhacungcap.png"))); // NOI18N
        mniPublisher.setText("Quản lý nhà cung cấp");
        mniPublisher.setVisible(false);
        mniPublisher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPublisherActionPerformed(evt);
            }
        });
        mnuBill.add(mniPublisher);

        mniPay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/thanhtoan.png"))); // NOI18N
        mniPay.setText("Thanh Toán");
        mniPay.setVisible(false);
        mniPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPayActionPerformed(evt);
            }
        });
        mnuBill.add(mniPay);

        mnuCoffe.add(mnuBill);

        mnuReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/thongke.png"))); // NOI18N
        mnuReport.setText("Thống kê");
        mnuCoffe.add(mnuReport);

        mnuSystem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hethong.png"))); // NOI18N
        mnuSystem.setText("Hệ thống");
        mnuSystem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuSystemMouseClicked(evt);
            }
        });

        mniBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/saoluu.png"))); // NOI18N
        mniBackup.setText("Sao lưu dữ liệu");
        mniBackup.setVisible(false);
        mniBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBackupActionPerformed(evt);
            }
        });
        mnuSystem.add(mniBackup);

        mniRestore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/phuchoi.png"))); // NOI18N
        mniRestore.setText("Phục hồi dữ liệu");
        mniRestore.setVisible(false);
        mniRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRestoreActionPerformed(evt);
            }
        });
        mnuSystem.add(mniRestore);

        mniDiary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhatky.png"))); // NOI18N
        mniDiary.setText("Xem nhật ký hệ thống");
        mniDiary.setVisible(false);
        mniDiary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDiaryActionPerformed(evt);
            }
        });
        mnuSystem.add(mniDiary);

        mnuCoffe.add(mnuSystem);

        mnuHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trogiup.png"))); // NOI18N
        mnuHelp.setText("Trợ trúp");
        mnuHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuHelpMouseClicked(evt);
            }
        });
        mnuCoffe.add(mnuHelp);

        mnuExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/thoat.png"))); // NOI18N
        mnuExit.setText("Thoát");
        mnuExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuExitMouseClicked(evt);
            }
        });
        mnuCoffe.add(mnuExit);

        setJMenuBar(mnuCoffe);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * .
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          

    public void setIcon() {
        this.setIconImage(new ImageIcon(getClass().getResource("/img/Coffee-break-icon.png")).getImage());
    }

    /**
     * .
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          

    private void mnuExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuExitMouseClicked
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thoát chương trình hay không ?", "Thông báo", 2) == 0) {

            for (JInternalFrame fr : this.dtpProgram.getAllFrames()) {
                fr.dispose();
            }

            this.dispose();
            LoginForm lgf = new LoginForm();
            lgf.setVisible(true);
        }
    }//GEN-LAST:event_mnuExitMouseClicked

    private void mnuHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuHelpMouseClicked
        // TODO add your handling code here:
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("hh.exe " + System.getProperty("user.dir") + "\\help.chm");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Cannot load help file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_mnuHelpMouseClicked

    private void mnuSystemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuSystemMouseClicked

    }//GEN-LAST:event_mnuSystemMouseClicked

    private void mniDiaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDiaryActionPerformed

        DiaryFormDB drf = DiaryFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == drf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(drf);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(drf);
        drf.show();
    }//GEN-LAST:event_mniDiaryActionPerformed

    private void mniRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRestoreActionPerformed
        // TODO add your handling code here:
        RecoveryForm bk = RecoveryForm.getIns();
        bk.setVisible(true);
    }//GEN-LAST:event_mniRestoreActionPerformed

    private void mniBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBackupActionPerformed
        // TODO add your handling code here:
        BackupForm bk = BackupForm.getIns();
        bk.setVisible(true);
    }//GEN-LAST:event_mniBackupActionPerformed

    private void mniPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPayActionPerformed
        // TODO add your handling code here:
        CheckOutForm pf = CheckOutForm.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == pf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(pf);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(pf);
        pf.show();
    }//GEN-LAST:event_mniPayActionPerformed

    private void mniPublisherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPublisherActionPerformed
        // TODO add your handling code here:
        PublisherFormDB plf = PublisherFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == plf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(plf);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(plf);
        plf.show();
    }//GEN-LAST:event_mniPublisherActionPerformed

    private void mniCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCategoryActionPerformed
        // TODO add your handling code here:
        CategoryFormDB cf = CategoryFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == cf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(cf);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(cf);
        cf.show();
    }//GEN-LAST:event_mniCategoryActionPerformed

    private void mniUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUnitActionPerformed
        // TODO add your handling code here:
        UnitFormDB uf = UnitFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == uf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(uf);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(uf);
        uf.show();
    }//GEN-LAST:event_mniUnitActionPerformed

    private void mniBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBillActionPerformed
        // TODO add your handling code here:
        BillFormDB bf = BillFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == bf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(bf);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(bf);
        bf.show();
    }//GEN-LAST:event_mniBillActionPerformed

    private void mniProductManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniProductManagementActionPerformed
        // TODO add your handling code here:
        ProductFormDB pd = ProductFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == pd) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(pd);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(pd);
        pd.show();
    }//GEN-LAST:event_mniProductManagementActionPerformed

    private void mniUserPermissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUserPermissionActionPerformed
        // TODO add your handling code here:
        RoleFormDB rf = RoleFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;

        for (JInternalFrame fr : frmList) {
            if (fr == rf) {
                found = true;
                break;
            }
        }
        if (!found) {
            //            System.out.println("adding new ");
            this.dtpProgram.add(rf);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(rf);
        rf.show();
    }//GEN-LAST:event_mniUserPermissionActionPerformed

    private void mniUserManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUserManagementActionPerformed
        // TODO add your handling code here:
        AccountFormDB ac = AccountFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == ac) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(ac);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(ac);
        ac.show();
    }//GEN-LAST:event_mniUserManagementActionPerformed

    private void mnuHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuHomeMouseClicked
        // TODO add your handling code here:
        HomeFormDB hf = HomeFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == hf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(hf);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(hf);
        hf.show();
    }//GEN-LAST:event_mnuHomeMouseClicked

    /**
     * .
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          

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
                if ("window".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InternalMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InternalMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InternalMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InternalMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InternalMain().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dtpProgram;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JMenuItem mniBackup;
    private javax.swing.JMenuItem mniBill;
    private javax.swing.JMenuItem mniCategory;
    private javax.swing.JMenuItem mniDiary;
    private javax.swing.JMenuItem mniPay;
    private javax.swing.JMenuItem mniProductManagement;
    private javax.swing.JMenuItem mniPublisher;
    private javax.swing.JMenuItem mniRestore;
    private javax.swing.JMenuItem mniUnit;
    private javax.swing.JMenuItem mniUserManagement;
    private javax.swing.JMenuItem mniUserPermission;
    private javax.swing.JMenu mnuAccount;
    private javax.swing.JMenu mnuBill;
    private javax.swing.JMenuBar mnuCoffe;
    private javax.swing.JMenu mnuExit;
    private javax.swing.JMenu mnuHelp;
    private javax.swing.JMenu mnuHome;
    private javax.swing.JMenu mnuProduct;
    private javax.swing.JMenu mnuReport;
    private javax.swing.JMenu mnuSystem;
    private javax.swing.JPanel pnlStatus;
    // End of variables declaration//GEN-END:variables
}
