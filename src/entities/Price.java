package entities;
// Generated Sep 20, 2015 7:16:35 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
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
 * Price generated by hbm2java
 */
@Entity
@Table(name="Price"
    ,schema="dbo"
    ,catalog="CoffeeShopAptech"
)
public class Price  implements java.io.Serializable {


     private Integer id;
     private Product product;
     private BigDecimal value;
     private Date startDate;
     private Date endDate;
     private boolean isActive;

    public Price() {
    }

    public Price(Product product, BigDecimal value, Date startDate, Date endDate, boolean isActive) {
       this.product = product;
       this.value = value;
       this.startDate = startDate;
       this.endDate = endDate;
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
    @JoinColumn(name="ProductID", nullable=false)
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    
    @Column(name="Value", nullable=false, precision=18)
    public BigDecimal getValue() {
        return this.value;
    }
    
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="StartDate", nullable=false, length=23)
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="EndDate", nullable=false, length=23)
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    @Column(name="IsActive", nullable=false)
    public boolean isIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }




}


