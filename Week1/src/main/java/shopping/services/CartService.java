package shopping.services;

    import java.util.ArrayList;
    import java.util.Map;
    import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.models.Product;
import shopping.models.User;
import shopping.repositories.CartRepo;
import shopping.repositories.ProductRepo;
import shopping.repositories.UserRepo;
import shopping.repositories.ElementRepo;
import shopping.models.CartElement;
    import shopping.models.Cart;    
    import static java.lang.Math.toIntExact;

@Service
public class CartService {
        
        
        private CartRepo cartRepo;
        private ProductRepo productRepo;
        private ElementRepo elementRepo;
        
        //cart CRUD
        public Cart getCartById(long id) {
            int intId = toIntExact(id);
            Cart cart = findCart(intId);
            if(cart!=null){
                return cart;
            }
            throw new IllegalArgumentException();
        }
        public List<Cart> getCarts(){
            return cartRepo.findAll();
        }
        public Cart addNewCart(Cart cart) {
            if (cart == null) {
                cart=new Cart();                
            }
            cartRepo.save(cart);	
            return cart;	
        }
        public Cart deleteCart(long id) {  
            int intId = toIntExact(id);
            Cart cart = findCart(intId);
            if(cart!=null){
                cartRepo.delete(cart);
                return cart;
            }
            throw new IllegalArgumentException();
        }
        public Cart editCart(long id,Cart cart){
            int intId = toIntExact(id);
            Cart oldCart = findCart(intId);
            if(oldCart!=null){
                cartRepo.delete(oldCart);
                return cart;
            }
            throw new IllegalArgumentException();
        }
        
        public User getCartUser(long id){
            int intId = toIntExact(id);
            if (cartRepo.existsById(intId)){
                return findCart(intId).getUser();
            }
            throw new IllegalArgumentException();
        }
        //Cart elements CRUD
        public List<CartElement> getCartElements(long id) {		
            int intId = toIntExact(id);
            if (cartRepo.existsById(intId)){
                return findCart(intId).getShopList();
            }
            throw new IllegalArgumentException();
        }
           
        public CartElement addElementToCart(long cartId,Product product, int quantity){
            CartElement newElement= new CartElement(product,quantity);
            
        }
        public CartElement removeElementFromCart(long cartId,long elementId){
           CartElement deleted= allCarts.get(cartId).deleteElement(elementId);
            return deleted;
        }

        private Cart findCart(int id){
            return cartRepo.findById(id).get();
        }
    }