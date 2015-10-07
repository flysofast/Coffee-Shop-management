/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Account;
import entities.Diary;
import entities.Functions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ashley
 */
public class DiaryEntityManager extends AbstractEntityManager<Diary> {
    
    public DiaryEntityManager() {
        super(Diary.class);
    }
    
    public static boolean createDiary( String description) {
        try {
            if (AccountEntityManager.currentAccount == null) {
                return false;
            }
            
            DiaryEntityManager dm = new DiaryEntityManager();
            Diary d = new Diary();
            d.setAccount(AccountEntityManager.currentAccount);
            d.setIsActive(true);
            d.setDescription(description);
            d.setTime(new Date());
            if (dm.addNew(d)) {
                return true;
            }
        } catch (Exception ex) {
            System.err.println("Failed attemping to add diary: " + ex.getMessage());
        }
        return false;
    }

    /**
     * List cac doi tuong dang con duoc su dung (isActive=true)
     *
     * @return list doi tuong
     */
    @Override
    public List<Diary> getAll() {
        List<Diary> list = super.getAll();
        
        List<Diary> res = new ArrayList<>();
        
        for (Diary ins : list) {
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
    public Diary find(int id) {
        for (Diary ins : getAll()) {
            if (ins.getId() == id) {
                return ins;
            }
        }
        System.out.println("Diary \"" + id + "\" chua ton tai");
        return null;
    }

    /**
     * Them doi tuong moi
     *
     * @param instance
     * @return insert thanh cong hay khong
     */
    public boolean addNew(Diary instance) {
        
        super.insert(instance);
        return true;
        
    }

    /**
     * Update doi tuong moi
     *
     * @param instance ID cua instance phai ton tai trong CSDL, neu ko se tao
     * mot ban ghi moi
     * @param name
     */
    public boolean edit(Diary instance) {
        Diary search = find(instance.getId());
        
        if (search == null || Objects.equals(search.getId(), instance.getId())) {
            super.update(instance);
            return true;
        }
        
        return false;
        
    }

    /**
     * Xoa mot doi tuong (isActive=false)
     *
     * @param instance
     */
    @Override
    public void delete(Diary instance) {
        try {
            instance.setIsActive(false);
            super.update(instance);
            
        } catch (Exception ex) {
            System.out.println("Xoa diary khong thanh cong: " + ex.getMessage());
        }
    }
}
