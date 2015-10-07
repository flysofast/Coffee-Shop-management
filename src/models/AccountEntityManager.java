/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Account;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author lehai
 */

/**
 * Demo thu lop custom cho Account. Neu khong can thi khoi custom lam gi.
 * @author lehai
 */
public class AccountEntityManager extends AbstractEntityManager<Account>{
    public AccountEntityManager(){
        super(Account.class);
    }
    
    public static Account currentAccount=null;
    /**
     * Lấy danh sách Account có trạng thái isActive = true
     * @return list active acc
     */
    @Override
    public List<Account> getAll() {
        List<Account> list = super.getAll();

        List<Account> res = new ArrayList<>();

        for (Account ins : list) {
            if (ins.isIsActive()) {
                res.add(ins);
            }
        }
        return res;
    }

    /**
     * Find by name
     * @return Account obj
     *
     */
    
    public Account find(String name) {
        for (Account i : getAll()) {
            if (i.getUsername().equalsIgnoreCase(name)) {
                return i;
            }
        }

        System.err.println("Khong tim thay account " + name );
        return null;
    }

   /**
    * Tạo thêm Account, kiểm tra điều kiện tồn tại, chưa thì add thêm
    * 
    */
    public boolean addNew(Account instance) {
        if (instance.getUsername().isEmpty()) {
            return false;
        }
        try {
            if (find(instance.getUsername()) != null) {
                System.err.println("Account da ton tai!");
                return false;
            }

            super.insert(instance);
            return true;
        } catch (Exception ex) {
            System.out.println("Them account moi khong thanh cong: " + ex.getMessage());
            return false;
        }
    }
    
    
    
    /**
     * Cập nhật thông tin Account
     * 
     * 
     */
    public boolean edit(Account instance) {
        if (instance.getUsername().isEmpty()) {
            return false;
        }

        try {
            Account search = find(instance.getUsername());
            
            if (search == null || Objects.equals(search.getId(), instance.getId())) {
                super.update(instance);
                return true;
            }

            return false;

        } catch (Exception ex) {
            System.out.println("Update account khong thanh cong: " + ex.getMessage());
            return false;
        }
    }
    /**
     * set isActive = false 
     * @param instance
     */
    @Override
    public void delete(Account instance) {
        try {
            instance.setIsActive(false);
            super.update(instance);

        } catch (Exception ex) {
            System.out.println("Xoa account khong thanh cong: " + ex.getMessage());
        }
    }
    /**
     * Kiểm tra login, ghi vào session
     * @param username
     * @param password
     * @return 
     */
    public boolean login(String username,String password){
        for (Account a : getAll()) {
            if (username.equals(a.getUsername()) && password.equals(a.getPassword())) {
                currentAccount=a;
                DiaryEntityManager.createDiary( "Logged in to system");
                return true;
            }
        }
        return false;
    }
}
