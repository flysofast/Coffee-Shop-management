/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Administrator
 */
public class CategoryEntityManager extends AbstractEntityManager<Category> {

    public CategoryEntityManager() {
        super(Category.class);
    }

    /**
     * List cac doi tuong dang con duoc su dung (isActive=true)
     * @return list doi tuong
     */
    @Override
    public List<Category> getAll() {
        List<Category> list = super.getAll();

        List<Category> res = new ArrayList<>();

        for (Category ins : list) {
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
    public Category find(String name) {
        for (Category ins : getAll()) {
            if (ins.getName().equalsIgnoreCase(name)) {
                return ins;
            }
        }

        System.err.println("Category \"" + name + "\" chua ton tai");
        return null;
    }

    /**
     * Them doi tuong moi, co kiem tra tinh hop le
     * @param instance 
     * @return  
     */
    public boolean addNew(Category instance) {
        if (instance.getName().isEmpty()) {
            return false;
        }
        try {
            if (find(instance.getName()) != null) {
                System.err.println("Category da ton tai!");
                return false;
            }

            super.insert(instance);
            DiaryEntityManager.createDiary( "Created category \""+instance.getName()+"\"");
            return true;
        } catch (Exception ex) {
            System.out.println("Them Category moi khong thanh cong: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Update doi tuong moi, co kiem tra tinh hop le
     * @param instance ID cua instance phai ton tai trong CSDL, neu ko se tao mot ban ghi moi 
     * @return  
     */
    public boolean edit(Category instance) {
        if (instance.getName().isEmpty()) {
            return false;
        }

        try {
            Category search = find(instance.getName());
            //Neu doi ten thi phai bao dam la ten nay chua ton tai
            if (search == null || Objects.equals(search.getId(), instance.getId())) {
                super.update(instance);
                DiaryEntityManager.createDiary( "Edited category \""+instance.getName()+"\"");
                return true;
            }

            return false;

        } catch (Exception ex) {
            System.out.println("Update Category khong thanh cong: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Xoa mot doi tuong (isActive=false)
     * @param instance 
     */
    @Override
    public void delete(Category instance) {
        try {
            instance.setIsActive(false);
            super.update(instance);
            DiaryEntityManager.createDiary( "Deleted category \""+instance.getName()+"\"");

        } catch (Exception ex) {
            System.out.println("Xoa Category khong thanh cong: " + ex.getMessage());
        }
    }

    
}
