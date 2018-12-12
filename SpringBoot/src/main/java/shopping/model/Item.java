package shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="element_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne    
    @JoinColumn(name = "cart_id")
    Cart cart;
    @Column(name = "product_quantity")
    private int quantity;
 

    public Item(){

    }
    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public double getTotalAmount() {
        return this.product.getPrice()*quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Long getId(){
        return id;
    }
    public void setProduct(Product product){
        this.product=product;
    }

	public void setId(Long id) {
        this.id=id;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }


    public void setCart(Cart cart){
        this.cart=cart;
    }
}


