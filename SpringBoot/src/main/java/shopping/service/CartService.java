package shopping.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.model.Product;
import shopping.model.User;
import shopping.repository.CartRepo;
import shopping.repository.ItemRepo;
import shopping.repository.ProductRepo;
import shopping.repository.UserRepo;
import shopping.model.Item;
import shopping.model.Cart;    

@Service
public class CartService {
        
        @Autowired
        private CartRepo cartRepo;
        @Autowired
        UserRepo userRepo;
        @Autowired
        ItemRepo itemRepo;
        @Autowired
        ProductRepo productRepo;
      
        
        //cart CRUD
        public Cart getCartById(Long id) {
            if(this.cartRepo.existsById(id)){
                return this.cartRepo.findById(id).get();
            }
            throw new IllegalArgumentException("Invalid cart Id");
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
            throw new IllegalArgumentException("Invalid cart Id");
        }


        public Cart setCartUser(Long id,Long userId){
            if (this.cartRepo.existsById(id)) {
                if (userRepo.existsById(userId)) {
                    Cart myCart= this.cartRepo.findById(id).get();
                    myCart.setUser(userRepo.findById(userId).get());
                    return this.cartRepo.save(myCart);       
                }   
                throw new IllegalArgumentException("Invalid user Id");                     
            }
            throw new IllegalArgumentException("Invalid cart Id");
        }

        public User getCartUser(Long id){
            if (this.cartRepo.existsById(id)){
                return this.cartRepo.findById(id).get().getUser();
            }
            throw new IllegalArgumentException("Invalid cart Id");
        }


        //Cart items CRUD
        public List<Item> getItems(Long id) {		           
            if (this.cartRepo.existsById(id)){
                return this.cartRepo.findById(id).get().getShopList();
            }
            throw new IllegalArgumentException("Invalid cart Id");
        }
           
        public Item addItemToCart(Long cartId,Long productId, int quantity){    

            if(this.cartRepo.existsById(cartId)){   
                if(this.productRepo.existsById(productId)){
                    Product product=this.productRepo.findById(productId).get();          
                    Item newItem=new Item(product,quantity);
                    Cart myCart= this.cartRepo.findById(cartId).get();
                    newItem.setCart(myCart);
                    newItem=this.itemRepo.save(newItem);
                    myCart.addToCart(newItem);
                    this.cartRepo.save(myCart);       
                    return newItem;
                }   
                throw new IllegalArgumentException("Invalid product Id");
            }
            throw new IllegalArgumentException("Invalid cart Id");
               
                     
        }
            
        
        public Item removeItemFromCart(long cartId,long itemId){
            if(this.cartRepo.existsById(cartId)){   
                if(this.productRepo.existsById(itemId)){
                    Item deleted=this.itemRepo.findById(cartId).get();
                    Cart cart=this.cartRepo.findById(cartId).get();
                    cart.deleteItem(deleted);
                    this.itemRepo.delete(deleted);
                    this.cartRepo.save(cart);
                    return deleted;
                }     
                throw new IllegalArgumentException("Invalid item Id");     
            }  
            throw new IllegalArgumentException("Invalid cart Id");     
        }
}