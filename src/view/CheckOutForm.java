/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Bill;
import entities.Price;
import entities.Product;
import entities.ProductBill;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.AbstractEntityManager;
import models.AccountEntityManager;
import models.BillEntityManager;
import models.ProductEntityManager;
import utils.ButtonColumn;
import utils.Converter;

/**
 *
 * @author lehai
 */
public final class CheckOutForm extends javax.swing.JInternalFrame {
    
    static CheckOutForm instance;
    ProductEntityManager proModel = new ProductEntityManager();
    AbstractEntityManager<ProductBill> pbModel = new AbstractEntityManager<ProductBill>();
    BillEntityManager billModel = new BillEntityManager();
    Product selectedProduct = null;
    final String searchTextPlaceHolder = "Nhập từ khóa...";
    DefaultTableModel cartModel;
    //Tổng cộng hóa đơn
    double total = 0;
    
    public static CheckOutForm getIns() {
        if (instance == null) {
            instance = new CheckOutForm();
        }
        return instance;
        
    }

    /**
     * Kiểm tra và bật các control để add vào giỏ hàng
     *
     */
    void enableButtons() {
        //Nếu chưa được nhập giá thì không bán được
        if (cbbPrice.getItemCount() == 0) {
            cbbPrice.setEnabled(false);
            btnAddToCart.setEnabled(false);
        } else {
            cbbPrice.setEnabled(true);
            btnAddToCart.setEnabled(true);
        }
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
    
    void updateTotalLabel() {
        lblTotal.setText("TOTAL: $" + total);
        
        btnSubmit.setEnabled(tblCart.getRowCount() > 0);
    }

    /**
     * Creates new form CheckOutForm
     */
    public CheckOutForm() {
        initComponents();
        setIcon();
        loadProductListToTable(proModel.getAll());
        cartModel = (DefaultTableModel) tblCart.getModel();
        centerTextCell(tblCart);
        txtSeller.setText(AccountEntityManager.currentAccount.getUsername());
        txtDate.setText(Converter.dateToString(new Date()));
        //Search box
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

        //Nut xoa o cart
        Action addAction = new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                
                int modelRow = Integer.valueOf(e.getActionCommand());
                double itemTotal = Double.valueOf(cartModel.getValueAt(modelRow, 4).toString());
                
                ((DefaultTableModel) table.getModel()).removeRow(modelRow);
                
                total -= itemTotal;
                updateTotalLabel();
            }
        };
        
        new ButtonColumn(tblCart, addAction, 5);
        
        tblProduct.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = tblProduct.getSelectedRow();
                if (selectedIndex != -1) {
                    selectedProduct = proModel.find((int) tblProduct.getValueAt(selectedIndex, 0));
                    spnQuantity.setValue(1);
                    cbbPrice.removeAllItems();
                    for (Price p : selectedProduct.getPrices()) {
                        Date today = new Date();
                        //Giá đang trong hạn áp dụng
                        if (p.getStartDate().compareTo(today) <= 0 && p.getEndDate().compareTo(today) >= 0) {
                            cbbPrice.addItem(p.getValue());
                        }
                    }
                    
                    enableButtons();
//                    loadPriceListToTable();
                }
            }
        });
    }
    
    void filterProductList() {
        String searchText = txtSearch.getText().toLowerCase();
        if (searchText.equalsIgnoreCase(searchTextPlaceHolder)) {
            return;
        }
        List<Product> l = new ArrayList<>();
        for (Product ins : proModel.getAll()) {
            if (ins.getName().toLowerCase().contains(searchText)
                    || ins.getCategory().getName().toLowerCase().contains(searchText)
                    || ins.getDescription().toLowerCase().contains(searchText)
                    || ins.getPublisher().getName().toLowerCase().contains(searchText)
                    || searchText.contains(ins.getId().toString())) {
                l.add(ins);
            }
        }
        loadProductListToTable(l);
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
        dtm.addColumn("In stock");
        dtm.addColumn("Category");
        dtm.addColumn("Publisher");
        dtm.addColumn("Unit");

//        cbbProduct.removeAllItems();
        for (Product pro : list) {
            dtm.addRow(new Object[]{
                pro.getId(),
                pro.getName(),
                pro.getDescription(),
                pro.getQuantity(),
                pro.getCategory().getName(),
                pro.getPublisher().getName(),
                pro.getUnit().getName(),});

//            cbbProduct.addItem(pro.getName());
        }
        
        tblProduct.setModel(dtm);
        tblProduct.getColumn("ID").setMaxWidth(36);
        
        centerTextCell(tblProduct);
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

        pnlBottom = new javax.swing.JPanel();
        btnSubmit = new javax.swing.JButton();
        pnlMain = new javax.swing.JPanel();
        pnlProduct = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        pnlSearch = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        pnlCart = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        pnlButtons = new javax.swing.JPanel();
        btnClearCart = new javax.swing.JButton();
        btnAddToCart = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbbPrice = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        SpinnerModel model =
        new SpinnerNumberModel(1, //initial value
            1, //min
            null, //max
            1);
        spnQuantity = new javax.swing.JSpinner(model);
        pnlTop = new javax.swing.JPanel();
        pnlSeller = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtSeller = new javax.swing.JTextField();
        pnlCustomer = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtCustomer = new javax.swing.JTextField();
        pnlDate = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();

        setTitle("Check out");
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlBottom.setLayout(new java.awt.GridBagLayout());

        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gui (2).png"))); // NOI18N
        btnSubmit.setText("Submit");
        btnSubmit.setEnabled(false);
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        pnlBottom.add(btnSubmit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 15);
        getContentPane().add(pnlBottom, gridBagConstraints);

        pnlMain.setMinimumSize(new java.awt.Dimension(900, 300));
        pnlMain.setLayout(new java.awt.GridBagLayout());

        pnlProduct.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Products"));
        pnlProduct.setMinimumSize(new java.awt.Dimension(350, 250));
        pnlProduct.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(300, 200));

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblProduct.setMinimumSize(new java.awt.Dimension(50, 64));
        tblProduct.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProduct.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblProduct);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        pnlProduct.add(jScrollPane1, gridBagConstraints);

        pnlSearch.setLayout(new javax.swing.BoxLayout(pnlSearch, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/timkiem.png"))); // NOI18N
        jLabel3.setText("Search: ");
        pnlSearch.add(jLabel3);

        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });
        pnlSearch.add(txtSearch);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 0);
        pnlProduct.add(pnlSearch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        pnlMain.add(pnlProduct, gridBagConstraints);

        pnlCart.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cart"));
        pnlCart.setMinimumSize(new java.awt.Dimension(300, 250));
        pnlCart.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setMinimumSize(new java.awt.Dimension(200, 200));

        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Quantity", "Price", "Total", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCart.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCart.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblCart);
        if (tblCart.getColumnModel().getColumnCount() > 0) {
            tblCart.getColumnModel().getColumn(0).setPreferredWidth(36);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        pnlCart.add(jScrollPane2, gridBagConstraints);

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(51, 51, 51));
        lblTotal.setText("Total: $0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlCart.add(lblTotal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        pnlMain.add(pnlCart, gridBagConstraints);

        pnlButtons.setLayout(new java.awt.GridBagLayout());

        btnClearCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/xoagiohang.png"))); // NOI18N
        btnClearCart.setText("Clear cart");
        btnClearCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCartActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        pnlButtons.add(btnClearCart, gridBagConstraints);

        btnAddToCart.setText(">>");
        btnAddToCart.setEnabled(false);
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        pnlButtons.add(btnAddToCart, gridBagConstraints);

        jLabel1.setText("Price:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        pnlButtons.add(jLabel1, gridBagConstraints);

        cbbPrice.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        pnlButtons.add(cbbPrice, gridBagConstraints);

        jLabel2.setText("Quantity:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        pnlButtons.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        pnlButtons.add(spnQuantity, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        pnlMain.add(pnlButtons, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(pnlMain, gridBagConstraints);

        pnlTop.setLayout(new java.awt.GridLayout(1, 3));

        jLabel5.setText("Seller:");
        pnlSeller.add(jLabel5);

        txtSeller.setEnabled(false);
        txtSeller.setMinimumSize(new java.awt.Dimension(60, 20));
        txtSeller.setPreferredSize(new java.awt.Dimension(150, 20));
        pnlSeller.add(txtSeller);

        pnlTop.add(pnlSeller);

        jLabel6.setText("Customer:");
        pnlCustomer.add(jLabel6);

        txtCustomer.setPreferredSize(new java.awt.Dimension(150, 20));
        pnlCustomer.add(txtCustomer);

        pnlTop.add(pnlCustomer);

        jLabel7.setText("Date:");
        pnlDate.add(jLabel7);

        txtDate.setEnabled(false);
        txtDate.setPreferredSize(new java.awt.Dimension(150, 20));
        pnlDate.add(txtDate);

        pnlTop.add(pnlDate);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        getContentPane().add(pnlTop, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        // TODO add your handling code here:
        int quantity;
        double price;
        try {
            quantity = Integer.valueOf(spnQuantity.getValue().toString());
            price = Double.valueOf(cbbPrice.getSelectedItem().toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Dữ liệu nhập vào không phù hợp.");
            return;
        }

        //Item cung ID va cung gia trong gio hang
        int index = -1, addedQuantity = 0;
        try {
            
            total = 0;
            //Lay so luong cua san pham cung ID va cung gia ban neu co trong gio hang
            for (int i = 0; i < tblCart.getRowCount(); i++) {
                int id = Integer.valueOf(cartModel.getValueAt(i, 0).toString());
                total += Double.valueOf(cartModel.getValueAt(i, 4).toString());
                double cartPrice = Double.valueOf(cartModel.getValueAt(i, 3).toString());

                //Nếu ID đã tồn tại (có thể có nhiều row vì được bán với nhiều giá)
                // thì cộng các số lượng đã bán lại
                if (id == selectedProduct.getId()) {
                    //Nếu price cũng bằng luôn thì lưu index đó lại để tăng số lượng
                    // chứ không thêm row mới
                    if (Double.compare(cartPrice, price) == 0) {
                        index = i;
                    }
                    addedQuantity += Integer.valueOf(cartModel.getValueAt(i, 2).toString());
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra: " + ex.getMessage(), "Thao tác thất bại", JOptionPane.ERROR_MESSAGE);
            total = 0;
            return;
        }

        //Neu so luong trong kho con du dap ung cho don hang
        if (quantity + addedQuantity <= selectedProduct.getQuantity()) {
            //Neu da co trong gio hang roi thi chi cong them so luong
            if (index != -1) {
                cartModel.setValueAt(quantity + addedQuantity, index, 2);
                cartModel.setValueAt((quantity + addedQuantity) * price, index, 4);
                
            } else {
                cartModel.addRow(new Object[]{
                    selectedProduct.getId(),
                    selectedProduct.getName(),
                    quantity,
                    price,
                    quantity * price,
                    "Xóa"
                });
            }

            //Cộng thêm mặt hàng vào giá
            total += quantity * price;
            updateTotalLabel();
        } else {
            JOptionPane.showMessageDialog(null, "Không còn đủ hàng cho mặt hàng này.");
        }
        
        tblCart.getColumn("ID").setMaxWidth(36);
        tblCart.getColumn("Quantity").setMaxWidth(72);
        tblCart.getColumn("").setMaxWidth(40);
        

    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void btnClearCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCartActionPerformed
        // TODO add your handling code here:
        while (cartModel.getRowCount() > 0) {
            cartModel.removeRow(0);
        }
        updateTotalLabel();
    }//GEN-LAST:event_btnClearCartActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        Bill bill = new Bill();
        bill.setTotal(BigDecimal.valueOf(total));
        bill.setDate(new Date());
        bill.setCustomerName(txtCustomer.getText());
        bill.setAccount(AccountEntityManager.currentAccount);
        bill.setIsActive(true);
        
        if (billModel.addNew(bill)) {
            try {
                for (int i = 0; i < cartModel.getRowCount(); i++) {
                    ProductBill pb = new ProductBill();
                    Product product = proModel.find(Integer.valueOf(cartModel.getValueAt(i, 0).toString()));
                    int quantity = Integer.valueOf(cartModel.getValueAt(i, 2).toString());
                    product.setQuantity(product.getQuantity() - quantity);
                    
                    if (proModel.edit(product)) {
                        pb.setBill(bill);
                        pb.setProduct(product);
                        pb.setQuantity(quantity);
                        pb.setPrice(BigDecimal.valueOf(Double.valueOf(cartModel.getValueAt(i, 3).toString())));
                        
                        pbModel.insert(pb);
                    } else {
                        throw new Exception();
                    }
                }
                
                JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
                btnClearCartActionPerformed(evt);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Có lỗi xảy ra: " + ex.getMessage(), "Thao tác thất bại", JOptionPane.ERROR_MESSAGE);
                billModel.delete(bill);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Không thể tạo được hóa đơn mới", "Thao tác thất bại", JOptionPane.ERROR_MESSAGE);
        }
        

    }//GEN-LAST:event_btnSubmitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnClearCart;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox cbbPrice;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlCart;
    private javax.swing.JPanel pnlCustomer;
    private javax.swing.JPanel pnlDate;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlProduct;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JPanel pnlSeller;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtCustomer;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSeller;
    // End of variables declaration//GEN-END:variables
}
