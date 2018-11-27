package shopping.model;

public class CartElement {
    private long id;
    private Item item;
    private int quantity;

    public CartElement(){

    }
    public CartElement(long id,Item item, int quantity) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }

    public long getId(){
        return id;
    }
    public void setItem(Item item){
        this.item=item;
    }

	public void setId(long id) {
        this.id=id;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
}