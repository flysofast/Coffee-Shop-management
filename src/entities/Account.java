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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Account generated by hbm2java
 */
@Entity
@Table(name="Account"
    ,schema="dbo"
    ,catalog="CoffeeShopAptech"
)
public class Account  implements java.io.Serializable {


     private Integer id;
     private Role role;
     private String username;
     private String password;
     private boolean isActive;
     private Set<Diary> diaries = new HashSet<Diary>(0);
     private Set<Bill> bills = new HashSet<Bill>(0);

    public Account() {
    }

	
    public Account(Role role, String username, String password, boolean isActive) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
    }
    public Account(Role role, String username, String password, boolean isActive, Set<Diary> diaries, Set<Bill> bills) {
       this.role = role;
       this.username = username;
       this.password = password;
       this.isActive = isActive;
       this.diaries = diaries;
       this.bills = bills;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RoleID", nullable=false)
    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }

    
    @Column(name="Username", nullable=false, length=30)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    @Column(name="Password", nullable=false, length=50)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="IsActive", nullable=false)
    public boolean isIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="account")
    public Set<Diary> getDiaries() {
        return this.diaries;
    }
    
    public void setDiaries(Set<Diary> diaries) {
        this.diaries = diaries;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="account")
    public Set<Bill> getBills() {
        return this.bills;
    }
    
    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }




}


