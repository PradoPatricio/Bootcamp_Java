package shopping.models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class Cart {
    private long id;
    @ManyToOne
    private User user;
    @ManyToMany
    private Map<Long,CartElement> shopList;
   
    public Cart(){
        this.shopList=new HashMap<Long,CartElement>();
    }
  
    public void setUser(User user){
        this.user=user;
    }
    public User getUser(){
        return user;
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