package entities;
// Generated Sep 20, 2015 7:16:35 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Diary generated by hbm2java
 */
@Entity
@Table(name="Diary"
    ,schema="dbo"
    ,catalog="CoffeeShopAptech"
)
public class Diary  implements java.io.Serializable {


     private Integer id;
     private Account account;
     private Date time;
     private String description;
     private boolean isActive;

    public Diary() {
    }

	
    public Diary(Account account, Date time, boolean isActive) {
        this.account = account;
        this.time = time;
        this.isActive = isActive;
    }
    public Diary(Account account, Date time, String description, boolean isActive) {
       this.account = account;
       this.time = time;
       this.description = description;
       this.isActive = isActive;
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
    @JoinColumn(name="AccountID", nullable=false)
    public Account getAccount() {
        return this.account;
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Time", nullable=false, length=23)
    public Date getTime() {
        return this.time;
    }
    
    public void setTime(Date time) {
        this.time = time;
    }

    
    @Column(name="Description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="IsActive", nullable=false)
    public boolean isIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }




}


