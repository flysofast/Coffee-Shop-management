/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Functions;
import entities.Role;
import java.util.HashSet;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.FunctionEntityManager;
import models.RoleEntityManager;
import utils.CustomCheckBoxList;

/**
 *
 * @author ADMIN
 */
public final class RoleFormDB extends javax.swing.JInternalFrame {

    /**
     * Creates new form RoleFormDB
     */
    static RoleFormDB instance = null;
    FunctionEntityManager funcModel = new FunctionEntityManager();
    RoleEntityManager roleModel = new RoleEntityManager();

    public RoleFormDB() {
        initComponents();
        setIcon();
        loadFunctionListToTable();
        loadRoleListToTable();

        tblFunction.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = tblFunction.getSelectedRow();
                if (selectedIndex != -1) {

                    txtIdFun.setText(tblFunction.getValueAt(selectedIndex, 0).toString());
                    txtNameFun.setText(tblFunction.getValueAt(selectedIndex, 1).toString());

                }
            }
        });

        tblRole.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = tblRole.getSelectedRow();
                if (selectedIndex != -1) {

                    txtRoleId.setText(tblRole.getValueAt(selectedIndex, 0).toString());
                    txtRoleName.setText(tblRole.getValueAt(selectedIndex, 1).toString());

                    loadEnabledFunction();

                }
            }
        });
        centerTextCell(tblFunction);
        centerTextCell(tblRole);
    }

    public static RoleFormDB getIns() {
        if (instance == null) {
            instance = new RoleFormDB();
        }
        return instance;

    }

    public void setIcon() {
        this.setFrameIcon(new ImageIcon(getClass().getResource("/img/Coffee-break-icon.png")));
    }

    void centerTextCell(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    /**
     * Do du lieu vao table
     */
    void loadFunctionListToTable() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };

        dtm.addColumn("ID");
        dtm.addColumn("Name");

        DefaultListModel listModel = new DefaultListModel<JCheckBox>();

        for (Functions func : funcModel.getAll()) {
            dtm.addRow(new Object[]{func.getId(), func.getName()});
            listModel.addElement(new JCheckBox(func.getName()));
        }

        tblFunction.setModel(dtm);
        tblFunction.getColumn("ID").setMaxWidth(36);

        lstFunction = new CustomCheckBoxList(listModel);
        scrFunctionList.setViewportView(lstFunction);
        loadEnabledFunction();
    }

    void loadEnabledFunction() {
        lstFunction.clearSelection();
        Set<Functions> funcList = roleModel.getFunctionList(roleModel.find(txtRoleName.getText()));
        for (Functions func : funcModel.getAll()) {
            if (funcList != null && funcList.contains(func)) {
                ((CustomCheckBoxList) lstFunction).setSelectedItem(func.getName(), true);
            }
        }

    }

    /**
     * Do du lieu vao table
     */
    void loadRoleListToTable() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };

        dtm.addColumn("ID");
        dtm.addColumn("Name");

        for (Role role : roleModel.getAll()) {
            dtm.addRow(new Object[]{role.getId(), role.getName()});
        }

        tblRole.setModel(dtm);
        tblRole.getColumn("ID").setMaxWidth(36);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlInput = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        txtRoleName = new javax.swing.JTextField();
        btncreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtRoleId = new javax.swing.JTextField();
        pnlOutput = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRole = new javax.swing.JTable();
        scrFunctionList = new javax.swing.JScrollPane();
        lstFunction = new javax.swing.JList();
        pnlInputFun = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIdFun = new javax.swing.JTextField();
        txtNameFun = new javax.swing.JTextField();
        btnCreateFun = new javax.swing.JButton();
        btnUpdateFun = new javax.swing.JButton();
        btnDeleteFun = new javax.swing.JButton();
        pnlOutputFun = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFunction = new javax.swing.JTable();

        setTitle("Role Manager");
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlInput.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Role Control"));
        pnlInput.setToolTipText("");
        pnlInput.setMinimumSize(new java.awt.Dimension(600, 230));
        pnlInput.setPreferredSize(new java.awt.Dimension(600, 230));
        pnlInput.setLayout(new java.awt.GridBagLayout());

        lblName.setText("Name: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlInput.add(lblName, gridBagConstraints);

        txtRoleName.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        pnlInput.add(txtRoleName, gridBagConstraints);

        btncreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/them.png"))); // NOI18N
        btncreate.setText("Create");
        btncreate.setMaximumSize(new java.awt.Dimension(87, 25));
        btncreate.setMinimumSize(new java.awt.Dimension(87, 25));
        btncreate.setPreferredSize(new java.awt.Dimension(87, 25));
        btncreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncreateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        pnlInput.add(btncreate, gridBagConstraints);

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sua.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setMaximumSize(new java.awt.Dimension(89, 25));
        btnUpdate.setMinimumSize(new java.awt.Dimension(89, 25));
        btnUpdate.setPreferredSize(new java.awt.Dimension(89, 25));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        pnlInput.add(btnUpdate, gridBagConstraints);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/xoa.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setMaximumSize(new java.awt.Dimension(85, 25));
        btnDelete.setMinimumSize(new java.awt.Dimension(85, 25));
        btnDelete.setPreferredSize(new java.awt.Dimension(85, 25));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        pnlInput.add(btnDelete, gridBagConstraints);

        jLabel1.setText("Id: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlInput.add(jLabel1, gridBagConstraints);

        txtRoleId.setEditable(false);
        txtRoleId.setEnabled(false);
        txtRoleId.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlInput.add(txtRoleId, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        getContentPane().add(pnlInput, gridBagConstraints);

        pnlOutput.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Role Data"));
        pnlOutput.setMinimumSize(new java.awt.Dimension(600, 230));
        pnlOutput.setPreferredSize(new java.awt.Dimension(600, 230));
        pnlOutput.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(400, 180));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 180));

        tblRole.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblRole.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblRole.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblRole);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlOutput.add(jScrollPane1, gridBagConstraints);

        scrFunctionList.setPreferredSize(new java.awt.Dimension(150, 180));
        scrFunctionList.setViewportView(lstFunction);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlOutput.add(scrFunctionList, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        getContentPane().add(pnlOutput, gridBagConstraints);

        pnlInputFun.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Function Control"));
        pnlInputFun.setMinimumSize(new java.awt.Dimension(600, 230));
        pnlInputFun.setPreferredSize(new java.awt.Dimension(600, 230));
        pnlInputFun.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Id:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlInputFun.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlInputFun.add(jLabel3, gridBagConstraints);

        txtIdFun.setEditable(false);
        txtIdFun.setEnabled(false);
        txtIdFun.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlInputFun.add(txtIdFun, gridBagConstraints);

        txtNameFun.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlInputFun.add(txtNameFun, gridBagConstraints);

        btnCreateFun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/them.png"))); // NOI18N
        btnCreateFun.setText("Create");
        btnCreateFun.setToolTipText("Liên hệ nhà phát triển để thực hiện chức năng này");
        btnCreateFun.setEnabled(false);
        btnCreateFun.setMaximumSize(new java.awt.Dimension(87, 25));
        btnCreateFun.setMinimumSize(new java.awt.Dimension(87, 25));
        btnCreateFun.setPreferredSize(new java.awt.Dimension(87, 25));
        btnCreateFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateFunActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        pnlInputFun.add(btnCreateFun, gridBagConstraints);

        btnUpdateFun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sua.png"))); // NOI18N
        btnUpdateFun.setText("Update");
        btnUpdateFun.setMaximumSize(new java.awt.Dimension(89, 25));
        btnUpdateFun.setMinimumSize(new java.awt.Dimension(89, 25));
        btnUpdateFun.setPreferredSize(new java.awt.Dimension(89, 25));
        btnUpdateFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateFunActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        pnlInputFun.add(btnUpdateFun, gridBagConstraints);

        btnDeleteFun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/xoa.png"))); // NOI18N
        btnDeleteFun.setText("Delete");
        btnDeleteFun.setToolTipText("Liên hệ nhà phát triển để thực hiện chức năng này");
        btnDeleteFun.setEnabled(false);
        btnDeleteFun.setMaximumSize(new java.awt.Dimension(85, 25));
        btnDeleteFun.setMinimumSize(new java.awt.Dimension(85, 25));
        btnDeleteFun.setPreferredSize(new java.awt.Dimension(85, 25));
        btnDeleteFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteFunActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        pnlInputFun.add(btnDeleteFun, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        getContentPane().add(pnlInputFun, gridBagConstraints);

        pnlOutputFun.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Function Data"));
        pnlOutputFun.setMinimumSize(new java.awt.Dimension(600, 230));
        pnlOutputFun.setPreferredSize(new java.awt.Dimension(600, 230));
        pnlOutputFun.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setPreferredSize(new java.awt.Dimension(550, 180));

        tblFunction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblFunction.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblFunction.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblFunction);

        pnlOutputFun.add(jScrollPane2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        getContentPane().add(pnlOutputFun, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateFunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateFunActionPerformed
        // TODO add your handling code here:

        if (txtNameFun.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Function name cannot be empty!");
            return;
        }

        Functions function = new Functions(txtNameFun.getText(), true);

        if (!funcModel.addNew(function)) {
            JOptionPane.showMessageDialog(null, "Failed to add new function!");
        } else {
            JOptionPane.showMessageDialog(null, "Function added successfully!");
            loadFunctionListToTable();
        }


    }//GEN-LAST:event_btnCreateFunActionPerformed

    private void btnUpdateFunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateFunActionPerformed
        // TODO add your handling code here:
        if (txtNameFun.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Function name cannot be empty!");
            return;
        }

        Functions function = new Functions();
        function.setId(Integer.valueOf(txtIdFun.getText()));
        function.setName(txtNameFun.getText());
        function.setIsActive(true);

        if (!funcModel.edit(function)) {
            JOptionPane.showMessageDialog(null, "Failed to update function!");
        } else {
            JOptionPane.showMessageDialog(null, "Function updated successfully!");
            loadFunctionListToTable();
        }


    }//GEN-LAST:event_btnUpdateFunActionPerformed

    private void btnDeleteFunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteFunActionPerformed
        // TODO add your handling code here:
        Functions function = new Functions();
        function.setId(Integer.valueOf(txtIdFun.getText()));
        function.setName(txtNameFun.getText());

        funcModel.delete(function);
        loadFunctionListToTable();
        JOptionPane.showMessageDialog(null, "Function deleted successfully!");
    }//GEN-LAST:event_btnDeleteFunActionPerformed

    private void btncreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncreateActionPerformed
        // TODO add your handling code here:
        if (txtRoleName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Role name cannot be empty!");
            return;
        }

        Role role = new Role(txtRoleName.getText(), true);
        Set<Functions> funcList = new HashSet<Functions>();

        for (JCheckBox cb : ((CustomCheckBoxList) lstFunction).getCheckedItems()) {
            funcList.add(funcModel.find(cb.getText()));
        }

        if (!roleModel.addNew(role, funcList)) {
            JOptionPane.showMessageDialog(null, "Failed to add new role!");
        } else {
            JOptionPane.showMessageDialog(null, "Role added successfully!");
            loadRoleListToTable();
        }
    }//GEN-LAST:event_btncreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (txtRoleName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Role name cannot be empty!");
            return;
        }

        Role role = new Role();
        role.setId(Integer.valueOf(txtRoleId.getText()));
        role.setName(txtRoleName.getText());
        role.setIsActive(true);

        Set<Functions> funcList = new HashSet<Functions>();

        for (JCheckBox cb : ((CustomCheckBoxList) lstFunction).getCheckedItems()) {
            funcList.add(funcModel.find(cb.getText()));
        }

        role.setRoleFunctions(null);

        if (!roleModel.edit(role, funcList)) {
            JOptionPane.showMessageDialog(null, "Failed to update role!");
        } else {
            JOptionPane.showMessageDialog(null, "Role updated successfully!");
            loadRoleListToTable();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        Role role = new Role();
        role.setId(Integer.valueOf(txtRoleId.getText()));
        role.setName(txtRoleName.getText());

        roleModel.delete(role);
        loadRoleListToTable();
        JOptionPane.showMessageDialog(null, "Function deleted successfully!");
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateFun;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteFun;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateFun;
    private javax.swing.JButton btncreate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblName;
    private javax.swing.JList lstFunction;
    private javax.swing.JPanel pnlInput;
    private javax.swing.JPanel pnlInputFun;
    private javax.swing.JPanel pnlOutput;
    private javax.swing.JPanel pnlOutputFun;
    private javax.swing.JScrollPane scrFunctionList;
    private javax.swing.JTable tblFunction;
    private javax.swing.JTable tblRole;
    private javax.swing.JTextField txtIdFun;
    private javax.swing.JTextField txtNameFun;
    private javax.swing.JTextField txtRoleId;
    private javax.swing.JTextField txtRoleName;
    // End of variables declaration//GEN-END:variables
}
