package shopping.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long id;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
    @OneToMany
    private List<Item> shopList;
   
    public Cart(){
        
    }
    public Cart(Long id,User user){
        this.id=id;
        this.user=user;
        this.shopList=new ArrayList<>();
        
    }

    public void setId(Long id) {
        this.id=id;
    }
    public Long getId(){
        return id;
    }
    public void setUser(User user){
        this.user=user;
    }
    public User getUser(){
        return user;
    }
    public void setShopList(List<Item> shopList){
        this.shopList=shopList;
    }
    public List<Item> getShopList(){
        return shopList;
    }
   

	
    public Item addToCart(Item item) {
		if (shopList == null) {
			shopList = new ArrayList<Item>();
		}
        shopList.add(item);
        return item;
    }
    public Item deleteItem(long id){
        for (Item x : shopList) {
            if (x.getId()==id) {
                Item deleted =x;
                shopList.remove(x);   
                return deleted;             
            }
       }
        return null;
        
    }

    public Item deleteItem(Item item){
        this.shopList.remove(item);
        return item;
    }


   
}