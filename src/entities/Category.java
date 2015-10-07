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
 * Category generated by hbm2java
 */
@Entity
@Table(name="Category"
    ,schema="dbo"
    ,catalog="CoffeeShopAptech"
)
public class Category  implements java.io.Serializable {


     private Integer id;
     private String name;
     private boolean isActive;
     private Set<Product> products = new HashSet<Product>(0);

    public Category() {
    }

	
    public Category(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }
    public Category(String name, boolean isActive, Set<Product> products) {
       this.name = name;
       this.isActive = isActive;
       this.products = products;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="category")
    public Set<Product> getProducts() {
        return this.products;
    }
    
    public void setProducts(Set<Product> products) {
        this.products = products;
    }




}

