package shopping.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CartElement {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column
    private int quantity;

    public CartElement(){

    }
    public CartElement(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public double getTotalAmount() {
        return this.product.getPrice()*quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    public Product getItem() {
        return product;
    }

    public long getId(){
        return id;
    }
    public void setItem(Product product){
        this.product=product;
    }

	public void setId(long id) {
        this.id=id;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
}