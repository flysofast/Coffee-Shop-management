/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Category;
import entities.Price;
import entities.Product;
import entities.Publisher;
import entities.Unit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.CategoryEntityManager;
import models.PriceEntityManager;
import models.ProductEntityManager;
import models.PublisherEntityManager;
import models.UnitEntityManager;
import utils.Converter;

/**
 *
 * @author ADMIN
 */
public final class ProductFormDB extends javax.swing.JInternalFrame {

    /**
     * Creates new form ProductFormDB
     */
    static ProductFormDB instance = null;
    CategoryEntityManager cateModel = new CategoryEntityManager();
    PublisherEntityManager pubModel = new PublisherEntityManager();
    UnitEntityManager unitModel = new UnitEntityManager();
    ProductEntityManager proModel = new ProductEntityManager();
    PriceEntityManager priceModel = new PriceEntityManager();
    Product selectedProduct = null;
    Price selectedPrice = null;
    final String searchTextPlaceHolder = "Type a product name...";

    public ProductFormDB() {
        initComponents();
        setIcon();
        loadProductListToTable();
        jxdImportDate.setDate(new Date());
        txtSearch.setText(searchTextPlaceHolder);
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                filterProductList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterProductList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterProductList();
            }
            // implement the methods
        });

        fillCbb();

        tblProduct.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = tblProduct.getSelectedRow();
                if (selectedIndex != -1) {
                    selectedProduct = proModel.find((int) tblProduct.getValueAt(selectedIndex, 0));

                    txtId.setText(selectedProduct.getId().toString());
                    txtName.setText(selectedProduct.getName());
//                    txtImportDate.setText(tblProduct.getValueAt(selectedIndex, 1).toString());
                    txtDes.setText(selectedProduct.getDescription());
                    txtQuantity.setText(String.valueOf(selectedProduct.getQuantity()));
                    cbbCategory.setSelectedItem(selectedProduct.getCategory().getName());
                    cbbPublisher.setSelectedItem(selectedProduct.getPublisher().getName());
                    cbbUnit.setSelectedItem(selectedProduct.getUnit().getName());
                    jxdImportDate.setDate(selectedProduct.getImportDate());

                    cbbProduct.setSelectedItem(selectedProduct.getName());
                    selectedPrice = null;
                    setEnabledPriceButton(false);
                    loadPriceListToTable();
                }
            }
        });
        tblPrice.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = tblPrice.getSelectedRow();
                if (selectedIndex != -1) {
                    selectedPrice = priceModel.find((int) tblPrice.getValueAt(selectedIndex, 0));
                    cbbProduct.setSelectedItem(selectedPrice.getProduct().getName());
                    txtPriceID.setText(selectedPrice.getId().toString());
                    txtPriceValue.setText(selectedPrice.getValue().toString());
                    jxdStartDate.setDate(selectedPrice.getStartDate());
                    jxdEndDate.setDate(selectedPrice.getEndDate());
                    setEnabledPriceButton(true);
                }
            }
        });
    }

    public void setIcon() {
        this.setFrameIcon(new ImageIcon(getClass().getResource("/img/Coffee-break-icon.png")));
    }

    public static ProductFormDB getIns() {
        if (instance == null) {
            instance = new ProductFormDB();
        }
        return instance;

    }

    boolean validateProductFields() {
        try {
            txtQuantity.setText(Integer.valueOf(txtQuantity.getText()).toString());
        } catch (Exception ex) {
            txtQuantity.requestFocus();
            txtQuantity.selectAll();
            return false;
        }

        if (checkIfTextFieldNull(txtName)) {
            return false;
        }
        if (checkIfTextFieldNull(txtDes)) {
            return false;
        }
        if (checkIfTextFieldNull(txtName)) {
            return false;
        }
        if (jxdImportDate.getDate() == null) {
            return false;
        }

        return true;
    }

    boolean validatePriceFields() {
        try {
            txtPriceValue.setText(Double.valueOf(txtPriceValue.getText()).toString());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    boolean checkIfTextFieldNull(JTextField t) {
        if (t.getText().isEmpty()) {
            t.requestFocus();
            t.selectAll();
            return true;
        }
        return false;
    }

    void setEnabledPriceButton(boolean enabled) {
        btnDeletePrice.setEnabled(enabled);
        btnUpdatePrice.setEnabled(enabled);
    }

    void fillCbb() {
        for (Category ac : cateModel.getAll()) {
            cbbCategory.addItem(ac.getName());
        }
        for (Publisher pu : pubModel.getAll()) {
            cbbPublisher.addItem(pu.getName());
        }
        for (Unit un : unitModel.getAll()) {
            cbbUnit.addItem(un.getName());
        }
    }

    void filterProductList() {
        String searchText = txtSearch.getText().toLowerCase();
        if (searchText.equalsIgnoreCase(searchTextPlaceHolder)) {
            return;
        }
        List<Product> l = new ArrayList<>();
        for (Product ins : proModel.getAll()) {
            if (ins.getName().toLowerCase().contains(searchText)
                    || ins.getDescription().toLowerCase().contains(searchText)
                    || searchText.contains(ins.getId().toString())) {
                l.add(ins);
            }
        }
        loadProductListToTable(l);
    }

    void loadPriceListToTable() {
        if (selectedProduct == null) {
            return;
        }

        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };

        dtm.addColumn("ID");
        dtm.addColumn("Price");
        dtm.addColumn("Start date");
        dtm.addColumn("End date");

        for (Price price : priceModel.getAllOfProduct(selectedProduct.getId())) {
            dtm.addRow(new Object[]{price.getId(), price.getValue(), Converter.dateToString(price.getStartDate()), Converter.dateToString(price.getEndDate())});
        }
        tblPrice.setModel(dtm);
        tblPrice.getColumn("ID").setMaxWidth(36);
    }

    void loadProductListToTable() {
        loadProductListToTable(proModel.getAll());
    }

    void loadProductListToTable(List<Product> list) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };

        dtm.addColumn("ID");
        dtm.addColumn("Name");
        dtm.addColumn("Description");
        dtm.addColumn("ImportDate");
        dtm.addColumn("Quantity");
        dtm.addColumn("Category");
        dtm.addColumn("Publisher");
        dtm.addColumn("Unit");

        cbbProduct.removeAllItems();
        for (Product pro : list) {
            dtm.addRow(new Object[]{
                pro.getId(),
                pro.getName(),
                pro.getDescription(),
                Converter.dateToString(pro.getImportDate()),
                pro.getQuantity(),
                pro.getCategory().getName(),
                pro.getPublisher().getName(),
                pro.getUnit().getName(),});

            cbbProduct.addItem(pro.getName());

        }

        tblProduct.setModel(dtm);
        tblProduct.getColumn("ID").setMaxWidth(36);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtDes = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        cbbCategory = new javax.swing.JComboBox();
        cbbPublisher = new javax.swing.JComboBox();
        cbbUnit = new javax.swing.JComboBox();
        btCreateProduct = new javax.swing.JButton();
        btUpdateProduct = new javax.swing.JButton();
        btDeleteProduct = new javax.swing.JButton();
        btResetProduct = new javax.swing.JButton();
        jxdImportDate = new org.jdesktop.swingx.JXDatePicker();
        pnlOutput = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtPriceID = new javax.swing.JTextField();
        txtPriceValue = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnCreatePrice = new javax.swing.JButton();
        btnUpdatePrice = new javax.swing.JButton();
        btnDeletePrice = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jxdStartDate = new org.jdesktop.swingx.JXDatePicker();
        jxdEndDate = new org.jdesktop.swingx.JXDatePicker();
        cbbProduct = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPrice = new javax.swing.JTable();

        setTitle("Product Manager");
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlInput.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Produc Control"));
        pnlInput.setMinimumSize(new java.awt.Dimension(765, 270));
        pnlInput.setName(""); // NOI18N
        pnlInput.setPreferredSize(new java.awt.Dimension(765, 270));
        pnlInput.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("ID:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 10);
        pnlInput.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Name: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 10);
        pnlInput.add(jLabel2, gridBagConstraints);

        jLabel3.setText(" Description: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 10);
        pnlInput.add(jLabel3, gridBagConstraints);

        jLabel4.setText("ImportDate: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 10);
        pnlInput.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Quantity: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 20);
        pnlInput.add(jLabel5, gridBagConstraints);

        jLabel6.setText("Category:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 20);
        pnlInput.add(jLabel6, gridBagConstraints);

        jLabel8.setText("Publisher:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 20);
        pnlInput.add(jLabel8, gridBagConstraints);

        jLabel9.setText("Unit:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 20);
        pnlInput.add(jLabel9, gridBagConstraints);

        txtId.setEditable(false);
        txtId.setEnabled(false);
        txtId.setMinimumSize(new java.awt.Dimension(200, 25));
        txtId.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        pnlInput.add(txtId, gridBagConstraints);

        txtName.setMinimumSize(new java.awt.Dimension(200, 25));
        txtName.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        pnlInput.add(txtName, gridBagConstraints);

        txtDes.setMinimumSize(new java.awt.Dimension(200, 25));
        txtDes.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        pnlInput.add(txtDes, gridBagConstraints);

        txtQuantity.setMinimumSize(new java.awt.Dimension(200, 25));
        txtQuantity.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        pnlInput.add(txtQuantity, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        pnlInput.add(cbbCategory, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        pnlInput.add(cbbPublisher, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        pnlInput.add(cbbUnit, gridBagConstraints);

        btCreateProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/them.png"))); // NOI18N
        btCreateProduct.setText("Create");
        btCreateProduct.setAlignmentX(0.5F);
        btCreateProduct.setMaximumSize(new java.awt.Dimension(87, 23));
        btCreateProduct.setMinimumSize(new java.awt.Dimension(87, 23));
        btCreateProduct.setPreferredSize(new java.awt.Dimension(87, 23));
        btCreateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreateProductActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        pnlInput.add(btCreateProduct, gridBagConstraints);

        btUpdateProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sua.png"))); // NOI18N
        btUpdateProduct.setText("Update");
        btUpdateProduct.setAlignmentX(0.5F);
        btUpdateProduct.setMaximumSize(new java.awt.Dimension(87, 23));
        btUpdateProduct.setMinimumSize(new java.awt.Dimension(87, 23));
        btUpdateProduct.setPreferredSize(new java.awt.Dimension(87, 23));
        btUpdateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateProductActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        pnlInput.add(btUpdateProduct, gridBagConstraints);

        btDeleteProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/xoa.png"))); // NOI18N
        btDeleteProduct.setText("Delete");
        btDeleteProduct.setAlignmentX(0.5F);
        btDeleteProduct.setMaximumSize(new java.awt.Dimension(87, 23));
        btDeleteProduct.setMinimumSize(new java.awt.Dimension(87, 23));
        btDeleteProduct.setPreferredSize(new java.awt.Dimension(87, 23));
        btDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteProductActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        pnlInput.add(btDeleteProduct, gridBagConstraints);

        btResetProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lammoi.png"))); // NOI18N
        btResetProduct.setText("Reset");
        btResetProduct.setAlignmentX(0.5F);
        btResetProduct.setMaximumSize(new java.awt.Dimension(87, 23));
        btResetProduct.setMinimumSize(new java.awt.Dimension(87, 23));
        btResetProduct.setPreferredSize(new java.awt.Dimension(87, 23));
        btResetProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetProductActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        pnlInput.add(btResetProduct, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 16, 5, 0);
        pnlInput.add(jxdImportDate, gridBagConstraints);

        getContentPane().add(pnlInput, new java.awt.GridBagConstraints());

        pnlOutput.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Product Data"));
        pnlOutput.setMinimumSize(new java.awt.Dimension(765, 270));
        pnlOutput.setPreferredSize(new java.awt.Dimension(765, 270));
        pnlOutput.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(710, 170));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(710, 170));

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblProduct.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProduct.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblProduct);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlOutput.add(jScrollPane1, gridBagConstraints);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/timkiem.png"))); // NOI18N
        jLabel10.setText("Search:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlOutput.add(jLabel10, gridBagConstraints);

        txtSearch.setMinimumSize(new java.awt.Dimension(200, 25));
        txtSearch.setPreferredSize(new java.awt.Dimension(200, 25));
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 70, 5, 70);
        pnlOutput.add(txtSearch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(pnlOutput, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Price Control"));
        jPanel1.setMinimumSize(new java.awt.Dimension(450, 270));
        jPanel1.setPreferredSize(new java.awt.Dimension(450, 270));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel11.setText("ID: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 20);
        jPanel1.add(jLabel11, gridBagConstraints);

        jLabel13.setText("Start Date: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 20);
        jPanel1.add(jLabel13, gridBagConstraints);

        jLabel14.setText("End Date: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 20);
        jPanel1.add(jLabel14, gridBagConstraints);

        txtPriceID.setEnabled(false);
        txtPriceID.setMinimumSize(new java.awt.Dimension(200, 25));
        txtPriceID.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jPanel1.add(txtPriceID, gridBagConstraints);

        txtPriceValue.setMinimumSize(new java.awt.Dimension(200, 25));
        txtPriceValue.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jPanel1.add(txtPriceValue, gridBagConstraints);

        jLabel12.setText("Value");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 20);
        jPanel1.add(jLabel12, gridBagConstraints);

        btnCreatePrice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/them.png"))); // NOI18N
        btnCreatePrice.setText("Create");
        btnCreatePrice.setMaximumSize(new java.awt.Dimension(87, 25));
        btnCreatePrice.setMinimumSize(new java.awt.Dimension(87, 25));
        btnCreatePrice.setPreferredSize(new java.awt.Dimension(87, 25));
        btnCreatePrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatePriceActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(btnCreatePrice, gridBagConstraints);

        btnUpdatePrice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sua.png"))); // NOI18N
        btnUpdatePrice.setText("Update");
        btnUpdatePrice.setEnabled(false);
        btnUpdatePrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePriceActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(btnUpdatePrice, gridBagConstraints);

        btnDeletePrice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/xoa.png"))); // NOI18N
        btnDeletePrice.setText("Delete");
        btnDeletePrice.setEnabled(false);
        btnDeletePrice.setMaximumSize(new java.awt.Dimension(87, 25));
        btnDeletePrice.setMinimumSize(new java.awt.Dimension(87, 25));
        btnDeletePrice.setPreferredSize(new java.awt.Dimension(87, 25));
        btnDeletePrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePriceActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(btnDeletePrice, gridBagConstraints);

        jLabel15.setText("Product: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 20);
        jPanel1.add(jLabel15, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jxdStartDate, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jxdEndDate, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanel1.add(cbbProduct, gridBagConstraints);

        getContentPane().add(jPanel1, new java.awt.GridBagConstraints());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Price Data"));
        jPanel2.setMinimumSize(new java.awt.Dimension(450, 270));
        jPanel2.setPreferredSize(new java.awt.Dimension(450, 270));

        jScrollPane2.setMinimumSize(new java.awt.Dimension(380, 220));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(380, 220));

        tblPrice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPrice.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPrice.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblPrice);

        jPanel2.add(jScrollPane2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        getContentPane().add(jPanel2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCreateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreateProductActionPerformed
        // TODO add your handling code here:
        if (!validateProductFields()) {
            JOptionPane.showMessageDialog(rootPane, "Please check the product's input data.");
            return;
        }

        Product pro = new Product();
        pro.setName(txtName.getText());
        pro.setImportDate(jxdImportDate.getDate() == null ? new Date() : jxdImportDate.getDate());
        pro.setDescription(txtDes.getText());
        pro.setQuantity(Integer.valueOf(txtQuantity.getText()));

        pro.setCategory(cateModel.find(cbbCategory.getSelectedItem().toString()));
        pro.setUnit(unitModel.find(cbbUnit.getSelectedItem().toString()));
        pro.setPublisher(pubModel.find(cbbPublisher.getSelectedItem().toString()));

        if (proModel.addNew(pro)) {
            JOptionPane.showMessageDialog(null, "Added new product successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add new product!");
        }

        loadProductListToTable();
    }//GEN-LAST:event_btCreateProductActionPerformed

    private void btUpdateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateProductActionPerformed
        // TODO add your handling code here:
        if (!validateProductFields()) {
            JOptionPane.showMessageDialog(rootPane, "Please check the product's input data.");
            return;
        }

        Product pro = new Product();
        pro.setId(selectedProduct.getId());
        pro.setName(txtName.getText());
        pro.setImportDate(jxdImportDate.getDate() == null ? new Date() : jxdImportDate.getDate());
        pro.setDescription(txtDes.getText());
        pro.setQuantity(Integer.valueOf(txtQuantity.getText()));

        pro.setCategory(cateModel.find(cbbCategory.getSelectedItem().toString()));
        pro.setUnit(unitModel.find(cbbUnit.getSelectedItem().toString()));
        pro.setPublisher(pubModel.find(cbbPublisher.getSelectedItem().toString()));

        if (proModel.edit(pro)) {
            JOptionPane.showMessageDialog(null, "Edited new product successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update the product!");
        }

        loadProductListToTable();
    }//GEN-LAST:event_btUpdateProductActionPerformed

    private void btDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteProductActionPerformed
        // TODO add your handling code here:
        proModel.delete(selectedProduct);
        loadProductListToTable();
        JOptionPane.showMessageDialog(null, "Product deleted successfully!");
    }//GEN-LAST:event_btDeleteProductActionPerformed

    private void btResetProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetProductActionPerformed
        // TODO add your handling code here:
        txtDes.setText("");
        txtName.setText("");
        txtQuantity.setText("");
    }//GEN-LAST:event_btResetProductActionPerformed

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
        // TODO add your handling code here:
        if (txtSearch.getText().equalsIgnoreCase(searchTextPlaceHolder)) {
            txtSearch.setText("");
        }
    }//GEN-LAST:event_txtSearchFocusGained

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        // TODO add your handling code here:
        if (txtSearch.getText().isEmpty()) {
            txtSearch.setText(searchTextPlaceHolder);
        }
    }//GEN-LAST:event_txtSearchFocusLost

    private void btnCreatePriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreatePriceActionPerformed
        // TODO add your handling code here:
        if (!validatePriceFields()) {
            JOptionPane.showMessageDialog(rootPane, "Dữ liệu nhập vào chưa đúng chuẩn. Vui lòng kiểm tra lại.");
            return;
        }

        if (jxdStartDate.getDate().after(jxdEndDate.getDate())) {
            JOptionPane.showMessageDialog(rootPane, "Ngày bắt đầu không được lớn hơn ngày kết thúc.");
            return;
        }

        Price price = new Price(proModel.find(cbbProduct.getSelectedItem().toString()),
                BigDecimal.valueOf(Double.valueOf(txtPriceValue.getText())),
                jxdStartDate.getDate(),
                jxdEndDate.getDate(),
                true);

        priceModel.insert(price);
        loadPriceListToTable();
    }//GEN-LAST:event_btnCreatePriceActionPerformed

    private void btnUpdatePriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePriceActionPerformed
        // TODO add your handling code here:
        if (selectedPrice == null) {
            return;
        }

        if (!validatePriceFields()) {
            JOptionPane.showMessageDialog(rootPane, "Please check the price's input data.");
            return;
        }

        if (jxdStartDate.getDate().after(jxdEndDate.getDate())) {
            JOptionPane.showMessageDialog(rootPane, "Ngày bắt đầu không được lớn hơn ngày kết thúc.");
            return;
        }

        Price price = new Price(proModel.find(cbbProduct.getSelectedItem().toString()),
                BigDecimal.valueOf(Double.valueOf(txtPriceValue.getText())),
                jxdStartDate.getDate(),
                jxdEndDate.getDate(),
                true);
        price.setId(Integer.valueOf(txtPriceID.getText()));

        priceModel.update(price);
        loadPriceListToTable();
    }//GEN-LAST:event_btnUpdatePriceActionPerformed

    private void btnDeletePriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePriceActionPerformed
        // TODO add your handling code here:
        if (selectedPrice == null) {
            return;
        }
        priceModel.delete(selectedPrice);
        loadPriceListToTable();
    }//GEN-LAST:event_btnDeletePriceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCreateProduct;
    private javax.swing.JButton btDeleteProduct;
    private javax.swing.JButton btResetProduct;
    private javax.swing.JButton btUpdateProduct;
    private javax.swing.JButton btnCreatePrice;
    private javax.swing.JButton btnDeletePrice;
    private javax.swing.JButton btnUpdatePrice;
    private javax.swing.JComboBox cbbCategory;
    private javax.swing.JComboBox cbbProduct;
    private javax.swing.JComboBox cbbPublisher;
    private javax.swing.JComboBox cbbUnit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXDatePicker jxdEndDate;
    private org.jdesktop.swingx.JXDatePicker jxdImportDate;
    private org.jdesktop.swingx.JXDatePicker jxdStartDate;
    private javax.swing.JPanel pnlInput;
    private javax.swing.JPanel pnlOutput;
    private javax.swing.JTable tblPrice;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtDes;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPriceID;
    private javax.swing.JTextField txtPriceValue;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
