package shopping.models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Cart {
    private long id;
    @ManyToOne
    private User user;
    @OneToMany
    private List<CartElement> shopList;
   
    public Cart(){
        this.shopList=new ArrayList<CartElement>();
    }
  
    public void setUser(User user){
        this.user=user;
    }
    public User getUser(){
        return user;
    }
	
    public List<CartElement> getShopList(){
        return shopList;
    }
    public long getId(){
        return id;
    }

	public void setId(long id) {
        this.id=id;
    }
    public CartElement addToCart(CartElement element) {
		if (shopList == null) {
			shopList = new ArrayList<CartElement>();
		}
        shopList.add(element);
        return element;
    }
    public CartElement deleteElement(long id){
        for (CartElement x : shopList) {
            if (x.getId()==id) {
                CartElement deleted =x;
                shopList.remove(x);   
                return x;             
            }
       }
        return null;
        
    }
}