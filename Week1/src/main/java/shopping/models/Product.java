package shopping.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private  long id;
    @Column(name = "product_name")
    private  String name;
    @Column(name = "product_price")
    private  double price;

    public Product(){
        
    }
    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getname() {
        return name;
    }
    public long getId(){
        return id;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public void setId(long id){
        this.id=id;
    }

}