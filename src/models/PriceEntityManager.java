/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Price;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class PriceEntityManager extends AbstractEntityManager<Price> {
    public PriceEntityManager() {
        super(Price.class);
    }  
    
     /**
     * List cac doi tuong dang con duoc su dung (isActive=true)
     * @return list doi tuong
     */
    @Override
    public List<Price> getAll() {
        List<Price> list = super.getAll();

        List<Price> res = new ArrayList<>();

        for (Price ins : list) {
            if (ins.isIsActive()) {
                res.add(ins);
            }
        }
        return res;
    }
    

    /**
     * Tim kiem doi tuong theo product id
     * @param productID
     *@return danh sách price có thỏa product id
     */
    public List<Price> getAllOfProduct(int productID) {
        List<Price> lPice = new ArrayList<>();
        for (Price ins : getAll()) {
            if (ins.getProduct().getId() == productID) {
                lPice.add(ins);
            }
        }

        //System.err.println("Khong tim thay price ");
        return lPice;
    }

    /**
     * Xoa mot doi tuong (isActive=false)
     * @param instance 
     */
    @Override
    public void delete(Price instance) {
       instance.setIsActive(false);
        super.update(instance);
        DiaryEntityManager.createDiary( "Deleted price #"+instance.getId());
    }

    
}
