/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Bill;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ashley
 */
public class BillEntityManager extends AbstractEntityManager<Bill> {

    public BillEntityManager() {
        super(Bill.class);
    }

    /**
     * List cac doi tuong dang con duoc su dung (isActive=true)
     *
     * @return list doi tuong
     */
    @Override
    public List<Bill> getAll() {
        List<Bill> list = super.getAll();

        List<Bill> res = new ArrayList<>();

        for (Bill ins : list) {
            if (ins.isIsActive()) {
                res.add(ins);
            }
        }
        return res;
    }

    /**
     * Tim kiem doi tuong theo id
     *
     * @param name
     * @return doi tuong tim thay
     */
    public Bill find(int id) {
        return super.find(id);
    }

    /**
     * Them doi tuong moi
     *
     * @param instance
     * @return
     */
    public boolean addNew(Bill instance) {

        super.insert(instance);
        DiaryEntityManager.createDiary( "Created bill #"+instance.getId());
        return true;

    }

    /**
     * Update doi tuong moi
     *
     * @param instance ID cua instance phai ton tai trong CSDL, neu ko se tao
     * mot ban ghi moi
     * @return
     */
    public boolean edit(Bill instance) {

        Bill search = find(instance.getId());

        if (search == null || Objects.equals(search.getId(), instance.getId())) {
            super.update(instance);
            DiaryEntityManager.createDiary( "Edited bill #"+instance.getId());
            return true;
        }

        return false;

    }

    /**
     * Xoa mot doi tuong (isActive=false)
     *
     * @param instance
     */
    public boolean disable(Bill instance) {
        try {
            instance.setIsActive(false);
            super.update(instance);
            DiaryEntityManager.createDiary( "Deleted bill #"+instance.getId());
            return true;

        } catch (Exception ex) {
            System.out.println("Xoa Category khong thanh cong: " + ex.getMessage());
            return false;
        }
    }
}
