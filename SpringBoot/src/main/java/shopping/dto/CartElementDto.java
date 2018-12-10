package shopping.dto;




public class CartElementDto {
   
    private Long id;
    private ProductDto product;
    CartDto cart;    
    private int quantity;
 

    public CartElementDto(){

    }
    public CartElementDto(ProductDto product, int quantity,Long id,CartDto cart) {
        this.id=id;
        this.product = product;
        this.quantity = quantity;
        this.cart=cart;
    }

    public void setId(Long id) {
        this.id=id;
    }
    public Long getId(){
        return id;
    }
    
     
    public void setProduct(ProductDto product){
        this.product=product;
    }

    public ProductDto getProduct() {
        return product;
    }  
	
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setCart(CartDto cart){
        this.cart=cart;
    }
    public CartDto getCart(){
        return cart;
    }
}