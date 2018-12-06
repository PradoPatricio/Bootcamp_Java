package shopping.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.models.Product;
import shopping.models.User;
import shopping.repositories.CartRepo;
import shopping.repositories.ElementRepo;
import shopping.repositories.ProductRepo;
import shopping.repositories.UserRepo;
import shopping.models.CartElement;
import shopping.models.Cart;    

@Service
public class CartService {
        
        @Autowired
        private CartRepo cartRepo;
        @Autowired
        UserRepo userRepo;
        @Autowired
        ElementRepo elementRepo;
        @Autowired
        ProductRepo productRepo;
      
        
        //cart CRUD
        public Cart getCartById(Long id) {
            Cart cart = cartRepo.findById(id);
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
                return cartRepo.save(cart);	               
            }   
        public Cart deleteCart(long id) {  
            Cart cart =cartRepo.findById(id);
            if(cart!=null){
                cartRepo.delete(cart);
                return cart;
            }
            throw new IllegalArgumentException();
        }
        /*
        public Cart editCart(long id,Cart cart){
            Cart oldCart = cartRepo.findById(id);
            if(oldCart!=null){
                cartRepo.delete(oldCart);
                return cart;
            }
            throw new IllegalArgumentException();
        }
        */


        public Cart setCartUser(Long id,Long userId){
            if ((cartRepo.existsById(id))&&(userRepo.existsById(userId))) {
                Cart myCart= cartRepo.findById(id);
                myCart.setUser(userRepo.findById(userId));
                return cartRepo.save(myCart);                
            }
            throw new IllegalArgumentException();
        }
        public User getCartUser(Long id){
            if (cartRepo.existsById(id)){
                return cartRepo.findById(id).getUser();
            }
            throw new IllegalArgumentException();
        }
        //Cart elements CRUD
        public List<CartElement> getCartElements(Long id) {		           
            if (cartRepo.existsById(id)){
                return cartRepo.findById(id).getShopList();
            }
            throw new IllegalArgumentException();
        }
           
        public Cart addElementToCart(Long cartId,Long productId, int quantity){           
            if ((cartRepo.existsById(cartId))&&(productRepo.existsById(productId))){                
            CartElement newCartElement=new CartElement(productRepo.findById(productId),quantity);
            newCartElement=elementRepo.save(newCartElement);
            Cart myCart= cartRepo.findById(cartId);
            myCart.addToCart(newCartElement);
            return cartRepo.save(myCart);          
            }
            else{
                throw new IllegalArgumentException();
            }            
        }
            
        
        public CartElement removeElementFromCart(long cartId,long elementId){
           
            CartElement deleted=  cartRepo.findById(cartId).deleteElement(elementId);
            return deleted;
        }

       
    }