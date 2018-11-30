package shopping.services;

    import java.util.ArrayList;
    import java.util.Map;
    import java.util.HashMap;
    import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import shopping.models.Product;
    import shopping.models.CartElement;
    import shopping.models.Cart;    

@Service
public class CartService {
        private AtomicLong CartCount = new AtomicLong();
        private AtomicLong ElementCount =new AtomicLong();
        private Map<Long,Cart> allCarts = new HashMap<>(); //map allow easy access to CartElements 
        
        
        //cart CRUD
        public Cart getCartById(long id) {
                return allCarts.get(id);		
        }
        public ArrayList<Cart> getCarts(){
            return new ArrayList<>(allCarts.values());
        }
        public Cart addNewCart(Cart cart) {
            if (cart != null) {
                cart.setId(CartCount.incrementAndGet());
                allCarts.put(cart.getId(), cart);	
            }
            return cart;	
        }
        public Cart deleteCart(long id) {        
            Cart deleted=allCarts.get(id);
            allCarts.remove(id);
            return deleted;
        }
        public Cart editCart(long id,Cart cart){
            if(allCarts.containsKey(id)){
                allCarts.replace(id, cart);
            return cart;
            }
            else{
                return null;
            }
            
        }   
        //Cart elements CRUD
        public ArrayList<CartElement> getCartElements(long id) {		
            return new ArrayList<>(allCarts.get(id).getShopList());		
        }
        public CartElement addElementToCart(long cartId,Product product, int quantity){
            CartElement newElement= new CartElement(ElementCount.incrementAndGet(),product,quantity);
            allCarts.get(cartId).addToCart(newElement);
            return newElement;
        }
        public CartElement removeElementFromCart(long cartId,long elementId){
           CartElement deleted= allCarts.get(cartId).deleteElement(elementId);
            return deleted;
        }
    }