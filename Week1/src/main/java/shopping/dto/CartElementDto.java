package shopping.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;



public class CartElementDto {
   
    private Long id;
    private ProductDto product;
    CartDto cart;    
    private int quantity;
 

    public CartElementDto(){

    }
    public CartElementDto(ProductDto product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
   
    public int getQuantity() {
        return quantity;
    }

    public ProductDto getProduct() {
        return product;
    }

    public Long getId(){
        return id;
    }
    public void setProduct(ProductDto product){
        this.product=product;
    }

	public void setId(Long id) {
        this.id=id;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
}