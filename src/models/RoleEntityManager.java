/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Functions;
import entities.Role;
import entities.RoleFunction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author lehai
 */
public class RoleEntityManager extends AbstractEntityManager<Role> {

    public RoleEntityManager() {
        super(Role.class);
    }

    /**
     * List cac doi tuong dang con duoc su dung (isActive=true)
     *
     * @return list doi tuong
     */
    @Override
    public List<Role> getAll() {
        List<Role> list = super.getAll();

        List<Role> res = new ArrayList<>();

        for (Role ins : list) {
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
    public Role find(String name) {
        for (Role ins : getAll()) {
            if (ins.getName().equalsIgnoreCase(name)) {
                return ins;
            }
        }
        return null;
    }

    /**
     * Them doi tuong moi, co kiem tra tinh hop le
     *
     * @param instance
     * @param funcList list function
     * @return insert thanh cong hay khong
     */
    public boolean addNew(Role instance, Set<Functions> funcList) {
        if (instance.getName().isEmpty()) {
            return false;
        }

        if (find(instance.getName()) != null) {
            System.err.println("Role da ton tai!");
            return false;
        }

        try {
            instance.setIsActive(true);
            super.insert(instance);

            DiaryEntityManager.createDiary( "Created role \""+instance.getName()+"\"");
            
            RoleFunctionEntityManager rfModel = new RoleFunctionEntityManager();

            for (Functions func : funcList) {
                RoleFunction rf = new RoleFunction();
                rf.setRole(instance);
                rf.setFunctions(func);
                rfModel.insert(rf);
                DiaryEntityManager.createDiary( "Grant \""+func.getName()+"\" permission to role \""+instance.getName()+"\"");
            }
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

    /**
     * Update doi tuong moi, co kiem tra tinh hop le
     *
     * @param instance ID cua instance phai ton tai trong CSDL, neu ko se tao
     * mot ban ghi moi
     * @param funcList list function
     * @return
     */
    public boolean edit(Role instance, Set<Functions> funcList) {
        if (instance.getName().isEmpty()) {
            return false;
        }

        Role search = find(instance.getName());

        try {

            //Neu doi ten thi phai bao dam la ten nay chua ton tai
            if (search == null || Objects.equals(search.getId(), instance.getId())) {
                instance.setIsActive(true);
                super.update(instance);

                RoleFunctionEntityManager rfModel = new RoleFunctionEntityManager();

                //Xoa danh sach function cu
                for (RoleFunction rf : rfModel.getAll()) {
                    if (rf.getRole().getId() == instance.getId()) {
                        rfModel.delete(rf);
                    }
                }
                //Add lai danh sach moi
                for (Functions func : funcList) {
                    RoleFunction rf = new RoleFunction();
                    rf.setRole(instance);
                    rf.setFunctions(func);
                    rfModel.insert(rf);
                }
                DiaryEntityManager.createDiary( "Edited role \""+instance.getName()+"\"");
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.err.println("Update role khong thanh cong: " + ex.getMessage());
            return false;
        }

    }

    /**
     * Xoa mot doi tuong (isActive=false)
     *
     * @param instance
     */
    @Override
    public void delete(Role instance) {
        if (instance == null) {
            return;
        }

        instance.setIsActive(false);
        try {
            super.update(instance);
            DiaryEntityManager.createDiary( "Deleted role \""+instance.getName()+"\"");
        } catch (Exception ex) {
            System.out.println("Xoa role khong thanh cong: " + ex.getMessage());

        }

    }

    /**
     * Lay danh sach cac function cua role
     *
     * @param instance
     * @return list function
     */
    public Set<Functions> getFunctionList(Role instance) {
        if (instance == null) {
            return null;
        }

        Set<Functions> res = new HashSet<>();
        RoleFunctionEntityManager rfModel = new RoleFunctionEntityManager();

        for (RoleFunction rf : rfModel.getAll()) {
            if (Objects.equals(rf.getRole().getId(), instance.getId())) {
                res.add(rf.getFunctions());
            }
        }

        return res;

    }
}
