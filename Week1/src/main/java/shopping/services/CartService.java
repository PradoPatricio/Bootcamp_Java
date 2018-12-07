package shopping.services;

import java.util.ArrayList;
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
            Cart cart = this.cartRepo.findById(id).get();
            if(cart!=null){
                return cart;
            }
            throw new IllegalArgumentException();
        }
        public List<Cart> getCarts(){           
           return (List<Cart>)this.cartRepo.findAll();
           
        }
        public Cart addNewCart(Cart cart) {
                if (cart == null) {
                    cart=new Cart();                
                }
                return this.cartRepo.save(cart);	               
            }   
        public Cart deleteCart(long id) {  
            Cart cart =this.cartRepo.findById(id).get();
            if(cart!=null){
                this.cartRepo.delete(cart);
                return cart;
            }
            throw new IllegalArgumentException();
        }
        /*
        public Cart editCart(long id,Cart cart){
            Cart oldCart = this.cartRepo.findById(id);
            if(oldCart!=null){
                this.cartRepo.delete(oldCart);
                return cart;
            }
            throw new IllegalArgumentException();
        }
        */


        public Cart setCartUser(Long id,Long userId){
            if ((this.cartRepo.existsById(id))&&(userRepo.existsById(userId))) {
                Cart myCart= this.cartRepo.findById(id).get();
                myCart.setUser(userRepo.findById(userId).get());
                return this.cartRepo.save(myCart);                
            }
            throw new IllegalArgumentException();
        }
        public User getCartUser(Long id){
            if (this.cartRepo.existsById(id)){
                return this.cartRepo.findById(id).get().getUser();
            }
            throw new IllegalArgumentException();
        }
        //Cart elements CRUD
        public List<CartElement> getCartElements(Long id) {		           
            if (this.cartRepo.existsById(id)){
                return this.cartRepo.findById(id).get().getShopList();
            }
            throw new IllegalArgumentException();
        }
           
        public Cart addElementToCart(Long cartId,Long productId, int quantity){           
            if ((this.cartRepo.existsById(cartId))&&(this.productRepo.existsById(productId))){   
                Product product=this.productRepo.findById(productId).get();          
                CartElement newCartElement=new CartElement(product,quantity);
                newCartElement=this.elementRepo.save(newCartElement);
                Cart myCart= this.cartRepo.findById(cartId).get();
                myCart.addToCart(newCartElement);
                return this.cartRepo.save(myCart);          
            }
            else{
                throw new IllegalArgumentException();
            }            
        }
            
        
        public CartElement removeElementFromCart(long cartId,long elementId){
            if ((this.cartRepo.existsById(cartId))&&( this.elementRepo.existsById(elementId))){   
            CartElement deleted=this.elementRepo.findById(cartId).get();
            Cart cart=this.cartRepo.findById(cartId).get();
            cart.deleteElement(deleted);
            this.elementRepo.delete(deleted);
            this.cartRepo.save(cart);
            return deleted;
          }
          else{
              throw new IllegalArgumentException();
         }

       
        }
}