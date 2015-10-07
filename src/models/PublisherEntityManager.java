/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Publisher;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Administrator
 */
public class PublisherEntityManager extends AbstractEntityManager<Publisher> {
    
    public PublisherEntityManager() {
        super(Publisher.class);
    }
    
    /**
     * List cac doi tuong dang con duoc su dung (isActive=true)
     * @return list doi tuong
     */
    @Override
    public List<Publisher> getAll() {
        List<Publisher> list = super.getAll();

        List<Publisher> res = new ArrayList<>();

        for (Publisher ins : list) {
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
    public Publisher find(String name) {
        for (Publisher ins : getAll()) {
            if (ins.getName().equalsIgnoreCase(name)) {
                return ins;
            }
        }

        System.err.println("Publisher \"" + name + "\" chua ton tai");
        return null;
    }

    /**
     * Them doi tuong moi, co kiem tra tinh hop le
     * @param instance 
     * @return  
     */
    public boolean addNew(Publisher instance) {
        if (instance.getCertificate().isEmpty()&& instance.getName().isEmpty()) {
            return false;
        }
        try {
            if (find(instance.getName()) != null) {
                System.err.println("Publisher da ton tai!");
                return false;
            }

            super.insert(instance);
            DiaryEntityManager.createDiary( "Created publisher \""+instance.getName()+"\"");
            return true;
        } catch (Exception ex) {
            System.out.println("Them Publisher moi khong thanh cong: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Update doi tuong moi, co kiem tra tinh hop le
     * @param instance ID cua instance phai ton tai trong CSDL, neu ko se tao mot ban ghi moi
     * @param name 
     * @return  
     */
    public boolean edit(Publisher instance) {
        if (instance.getName().isEmpty()&& instance.getCertificate().isEmpty()) {
            return false;
        }

        try {
            Publisher search = find(instance.getName());
            //Neu doi ten thi phai bao dam la ten nay chua ton tai
            if (search == null || Objects.equals(search.getId(), instance.getId())) {
                super.update(instance);
                DiaryEntityManager.createDiary( "Edited publisher \""+instance.getName()+"\"");
                return true;
            }

            return false;

        } catch (Exception ex) {
            System.out.println("Update Publisher khong thanh cong: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Xoa mot doi tuong (isActive=false)
     * @param instance 
     */
    @Override
   public void delete(Publisher instance) {
        try {
            instance.setIsActive(false);
            super.update(instance);
            DiaryEntityManager.createDiary( "Deleted publisher \""+instance.getName()+"\"");

        } catch (Exception ex) {
            System.out.println("Xoa Publisher khong thanh cong: " + ex.getMessage());
        }
    }
}
