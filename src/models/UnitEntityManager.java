/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Unit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Administrator
 */
public class UnitEntityManager extends AbstractEntityManager<Unit> {

    public UnitEntityManager() {
        super(Unit.class);
    }

    /**
     * List cac doi tuong dang con duoc su dung (isActive=true)
     *
     * @return list doi tuong
     */
    @Override
    public List<Unit> getAll() {
        List<Unit> list = super.getAll();

        List<Unit> res = new ArrayList<>();

        for (Unit ins : list) {
            if (ins.isIsActive()) {
                res.add(ins);
            }
        }
        return res;
    }

    /**
     * Tim kiem doi tuong theo ten
     *
     * @param name
     * @return doi tuong tim thay
     */
    public Unit find(String name) {
        for (Unit ins : getAll()) {
            if (ins.getName().equalsIgnoreCase(name)) {
                return ins;
            }
        }

        System.err.println("Unit \"" + name + "\" chua ton tai");
        return null;
    }

    /**
     * Them doi tuong moi, co kiem tra tinh hop le
     *
     * @param instance
     * @return
     */
    public boolean addNew(Unit instance) {
        if (instance.getName().isEmpty()) {
            return false;
        }
        try {
            if (find(instance.getName()) != null) {
                System.err.println("Unit da ton tai!");
                return false;
            }

            super.insert(instance);
            DiaryEntityManager.createDiary( "Created unit \"" + instance.getName() + "\"");
            return true;
        } catch (Exception ex) {
            System.out.println("Them Unit moi khong thanh cong: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Update doi tuong moi, co kiem tra tinh hop le
     *
     * @param instance ID cua instance phai ton tai trong CSDL, neu ko se tao
     * mot ban ghi moi
     * @return
     */
    public boolean edit(Unit instance) {
        if (instance.getName().isEmpty()) {
            return false;
        }

        try {
            Unit search = find(instance.getName());
            //Neu doi ten thi phai bao dam la ten nay chua ton tai
            if (search == null || Objects.equals(search.getId(), instance.getId())) {
                super.update(instance);
                DiaryEntityManager.createDiary( "Edited unit \"" + instance.getName() + "\"");
                return true;
            }

            return false;

        } catch (Exception ex) {
            System.out.println("Update Unit khong thanh cong: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Xoa mot doi tuong (isActive=false)
     *
     * @param instance
     */
    @Override
    public void delete(Unit instance) {
        try {
            instance.setIsActive(false);
            super.update(instance);
            DiaryEntityManager.createDiary( "Deleted unit \"" + instance.getName() + "\"");
        } catch (Exception ex) {
            System.out.println("Xoa Unit khong thanh cong: " + ex.getMessage());
        }
    }
}
