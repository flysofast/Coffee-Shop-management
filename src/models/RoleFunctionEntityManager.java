/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.RoleFunction;

/**
 *
 * @author lehai
 */
public class RoleFunctionEntityManager extends AbstractEntityManager<RoleFunction>{

    public RoleFunctionEntityManager() {
        super(RoleFunction.class);
    }
    
    /**
     * Tim moi quan he role - function
     * @param roleID
     * @param functionID
     * @return ket qua. Neu khong co tra ve null.
     */
    public RoleFunction find(int roleID,int functionID){
        for(RoleFunction rf:super.getAll()){
            if(rf.getFunctions().getId()==functionID&&rf.getRole().getId()==roleID){
                return rf;
            }
        }
        return null;
    }
    
}
