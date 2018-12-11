package shopping.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.model.Product;
import shopping.model.User;
import shopping.repository.CartRepo;
import shopping.repository.ElementRepo;
import shopping.repository.ProductRepo;
import shopping.repository.UserRepo;
import shopping.model.CartElement;
import shopping.model.Cart;    

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
            if(this.cartRepo.existsById(id)){
                return this.cartRepo.findById(id).get();
            }
            throw new RuntimeException("Invalid cart Id");
        }

        public List<Cart> getCarts(){           
           return (List<Cart>)this.cartRepo.findAll();           
        }

        public Cart addNewCart() {               
            Cart cart=new Cart();                
            return this.cartRepo.save(cart);	               
            }   

        public Cart deleteCart(long id) {
            if(this.cartRepo.existsById(id)){
                Cart cart =this.cartRepo.findById(id).get();
                this.cartRepo.delete(cart);
                return cart;
            }
            throw new RuntimeException("Invalid cart Id");
        }


        public Cart setCartUser(Long id,Long userId){
            if (this.cartRepo.existsById(id)) {
                if (userRepo.existsById(userId)) {
                    Cart myCart= this.cartRepo.findById(id).get();
                    myCart.setUser(userRepo.findById(userId).get());
                    return this.cartRepo.save(myCart);       
                }   
                throw new RuntimeException("Invalid user Id");                     
            }
            throw new RuntimeException("Invalid cart Id");
        }

        public User getCartUser(Long id){
            if (this.cartRepo.existsById(id)){
                return this.cartRepo.findById(id).get().getUser();
            }
            throw new RuntimeException("Invalid cart Id");
        }


        //Cart elements CRUD
        public List<CartElement> getCartElements(Long id) {		           
            if (this.cartRepo.existsById(id)){
                return this.cartRepo.findById(id).get().getShopList();
            }
            throw new RuntimeException("Invalid cart Id");
        }
           
        public CartElement addElementToCart(Long cartId,Long productId, int quantity){    

            if(this.cartRepo.existsById(cartId)){   
                if(this.productRepo.existsById(productId)){
                    Product product=this.productRepo.findById(productId).get();          
                    CartElement newCartElement=new CartElement(product,quantity);
                    Cart myCart= this.cartRepo.findById(cartId).get();
                    newCartElement.setCart(myCart);
                    newCartElement=this.elementRepo.save(newCartElement);
                    myCart.addToCart(newCartElement);
                    this.cartRepo.save(myCart);       
                    return newCartElement;
                }   
                throw new RuntimeException("Invalid product Id");
            }
            throw new RuntimeException("Invalid cart Id");
               
                     
        }
            
        
        public CartElement removeElementFromCart(long cartId,long elementId){
            if(this.cartRepo.existsById(cartId)){   
                if(this.productRepo.existsById(elementId)){
                    CartElement deleted=this.elementRepo.findById(cartId).get();
                    Cart cart=this.cartRepo.findById(cartId).get();
                    cart.deleteElement(deleted);
                    this.elementRepo.delete(deleted);
                    this.cartRepo.save(cart);
                    return deleted;
                }     
                throw new RuntimeException("Invalid element Id");     
            }  
            throw new RuntimeException("Invalid cart Id");     
        }
}