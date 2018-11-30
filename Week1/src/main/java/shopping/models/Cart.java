package shopping.models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private long id;
    private Map<Long,CartElement> shopList;
    private String name;

    public Cart(){
        this.shopList=new HashMap<Long,CartElement>();
    }
    public String getName(){
        return name;
    }

	public void setName(String name){
        this.name=name;
    }
    public List<CartElement> getShopList(){
        return new ArrayList<>(shopList.values());
    }
    public long getId(){
        return id;
    }

	public void setId(long id) {
        this.id=id;
    }
    public CartElement addToCart(CartElement element) {
		if (shopList == null) {
			shopList = new HashMap<Long,CartElement>();
		}
        shopList.put(element.getId(),element);
        return element;
    }
    public CartElement deleteElement(long id){
       if(shopList.containsKey(id)){
        CartElement deleted =shopList.get(id);
        shopList.remove(id);
        return deleted;
       }
        return null;
        
    }
}