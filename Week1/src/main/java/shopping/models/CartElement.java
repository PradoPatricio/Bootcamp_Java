package shopping.models;

public class CartElement {
    private long id;
    private Product product;
    private int quantity;

    public CartElement(){

    }
    public CartElement(long id,Product product, int quantity) {
        this.id = id;
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