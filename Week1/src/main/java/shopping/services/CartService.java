package shopping.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.models.Product;
import shopping.models.User;
import shopping.repositories.CartRepo;
import shopping.repositories.UserRepo;
import shopping.models.CartElement;
import shopping.models.Cart;    

@Service
public class CartService {
        
        @Autowired
        private CartRepo cartRepo;
        @Autowired
        UserRepo userRepo;
      
        
        //cart CRUD
        public Cart getCartById(Long id) {
            Cart cart = cartRepo.findCartById(id);
            if(cart!=null){
                return cart;
            }
            throw new IllegalArgumentException();
        }
        public List<Cart> getCarts(){
            return cartRepo.findAll();
        }
        public Cart addNewCart(long userId,Cart cart) {

            if(userRepo.existsById((int)userId)){
                if (cart == null) {
                    cart=new Cart();                
                }
                cartRepo.save(cart);	
                return cart;
            }           
            else throw new IllegalArgumentException();        
        }
        public Cart deleteCart(long id) {  
            Cart cart = cartRepo.findCartById(id);
            if(cart!=null){
                cartRepo.delete(cart);
                return cart;
            }
            throw new IllegalArgumentException();
        }
        public Cart editCart(long id,Cart cart){
            int intId = (int)(id);
            Cart oldCart = findCart(intId);
            if(oldCart!=null){
                cartRepo.delete(oldCart);
                return cart;
            }
            throw new IllegalArgumentException();
        }
        
        public User getCartUser(long id){
            int intId = (int)(id);
            if (cartRepo.existsById(intId)){
                return findCart(intId).getUser();
            }
            throw new IllegalArgumentException();
        }
        //Cart elements CRUD
        public List<CartElement> getCartElements(long id) {		
            int intId = (int)(id);
            if (cartRepo.existsById(intId)){
                return findCart(intId).getShopList();
            }
            throw new IllegalArgumentException();
        }
           
        public CartElement addElementToCart(long cartId,Product product, int quantity){
            int intId = (int)(cartId);
            if (cartRepo.existsById(intId)){
            CartElement newCartElement=new CartElement(product,quantity);
            findCart(intId).addToCart(newCartElement);
            return newCartElement;
            }
            else{
                throw new IllegalArgumentException();
            }            
        }
            
        
        public CartElement removeElementFromCart(long cartId,long elementId){
            int intId = (int)(cartId);
            CartElement deleted= findCart(intId).deleteElement(elementId);
            return deleted;
        }

        private Cart findCart(int id){
            return cartRepo.findById(id).get();
        }
    }