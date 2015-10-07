package entities;
// Generated Sep 20, 2015 7:16:35 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Role generated by hbm2java
 */
@Entity
@Table(name="Role"
    ,schema="dbo"
    ,catalog="CoffeeShopAptech"
)
public class Role  implements java.io.Serializable {


     private Integer id;
     private String name;
     private boolean isActive;
     private Set<Account> accounts = new HashSet<Account>(0);
     private Set<RoleFunction> roleFunctions = new HashSet<RoleFunction>(0);

    public Role() {
    }

	
    public Role(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }
    public Role(String name, boolean isActive, Set<Account> accounts, Set<RoleFunction> roleFunctions) {
       this.name = name;
       this.isActive = isActive;
       this.accounts = accounts;
       this.roleFunctions = roleFunctions;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="Name", nullable=false)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="IsActive", nullable=false)
    public boolean isIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="role")
    public Set<Account> getAccounts() {
        return this.accounts;
    }
    
    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="role")
    public Set<RoleFunction> getRoleFunctions() {
        return this.roleFunctions;
    }
    
    public void setRoleFunctions(Set<RoleFunction> roleFunctions) {
        this.roleFunctions = roleFunctions;
    }




}

