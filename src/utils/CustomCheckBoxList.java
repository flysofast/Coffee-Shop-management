/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Component;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import java.util.ArrayList;

/**
 * Checkbox list, co ham lay nhung o checkbox da duoc check. Su dung:
 * DefaultListModel<JCheckBox> model = new DefaultListModel<JCheckBox>();
 * model.addElement(new JCheckBox("checkbox 1")); model.addElement(new
 * JCheckBox("checkbox 2"));
 *
 * lstCheckbox = new CustomCheckBoxList(model);
 * jScrollPane.setViewportView(lstCheckbox);
 *
 * (lstCheckbox, jScrollPane da duoc tao san bang cach keo tha o Design)
 *
 * @author IT
 */
@SuppressWarnings("serial")
public class CustomCheckBoxList extends JList<JCheckBox> {
    
    protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);
    
    public CustomCheckBoxList() {
        setCellRenderer(new CellRenderer());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if (index != -1) {
                    JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
                    checkbox.setSelected(!checkbox.isSelected());
                    repaint();
                }
            }
        });
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public CustomCheckBoxList(ListModel<JCheckBox> model) {
        this();
        setModel(model);
    }

    /**
     * Lay danh sach cac checkbox duoc check Su dung: nho ep kieu truoc khi goi
     * ham
     *
     * (CustomCheckBoxList)lstFuntions).getCheckedItems()
     *
     * @return list ket qua
     */
    public ArrayList<JCheckBox> getCheckedItems() {
        int count = getModel().getSize();
        ArrayList list = new ArrayList();
        for (int i = 0; i < count; i++) {
            JCheckBox item = getModel().getElementAt(i);
            if (item.isSelected()) {
                list.add(item);
            }
        }
        return list;
    }

    /**
     * Set dau tick cho may o checkbox
     *
     * @param name ten cua o checkbox
     * @param selected set tick hay khong tick
     */
    public void setSelectedItem(String name, boolean selected) {
        int count = getModel().getSize();
        for (int i = 0; i < count; i++) {
            JCheckBox item = getModel().getElementAt(i);
            if (item.getText().equalsIgnoreCase(name)) {
                item.setSelected(selected);
                int a = 0;
            }
        }
        repaint();
    }
    /**
     * Bo chon tat ca cac o
     */
    @Override
    public void clearSelection() {
        int count = getModel().getSize();
        for (int i = 0; i < count; i++) {
            JCheckBox item = getModel().getElementAt(i);
            item.setSelected(false);
        }
        repaint();
    }

    protected class CellRenderer implements ListCellRenderer<JCheckBox> {
        
        @Override
        public Component getListCellRendererComponent(
                JList<? extends JCheckBox> list, JCheckBox value, int index,
                boolean isSelected, boolean cellHasFocus) {
            JCheckBox checkbox = value;

            //Drawing checkbox, change the appearance here
            checkbox.setBackground(isSelected ? getSelectionBackground()
                    : getBackground());
            checkbox.setForeground(isSelected ? getSelectionForeground()
                    : getForeground());
            checkbox.setEnabled(isEnabled());
            checkbox.setFont(getFont());
            checkbox.setFocusPainted(false);
            checkbox.setBorderPainted(true);
            checkbox.setBorder(isSelected ? UIManager
                    .getBorder("List.focusCellHighlightBorder") : noFocusBorder);
            return checkbox;
        }
    }
}
