package shopping.controllers;

import java.awt.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.joran.conditional.ElseAction;
import shopping.models.CartElement;
import shopping.models.Product;
import shopping.models.User;
import shopping.services.CartService;
import shopping.services.ProductService;
import shopping.models.Cart;

@RestController
public class CartController {    
    @Autowired
    private CartService cart_s;
    @Autowired
    private ProductService product_s;

    //Cart mapping
    @GetMapping("/cart/{id}")
    public ArrayList<CartElement> getCartElements(@PathVariable("id") long  Id){
        return cart_s.getCartElements(Id); 
    }
    

    @GetMapping("/cart")                                                        
    public ArrayList<Cart> getAllCarts(){
        return cart_s.getCarts();
    }

    @PostMapping("/cart")                                             //new cart
    public Cart newCart(@RequestBody Cart cart){ 
        cart_s.addNewCart(cart);
        return cart;
    }
    @PutMapping("/cart/{id}")                                           //edit cart
    public Cart editCart(@PathVariable("id") long  Id,@RequestBody Cart cart){ 
        cart_s.editCart(Id,cart);
        return cart;
    }

    @DeleteMapping("/cart/{id}")
        public Cart deleteCart(@PathVariable("id") long  Id){
           return cart_s.deleteCart(Id);
        }

    @GetMapping("/cart/{id}/user")
    public User getCartUser(@PathVariable("id") long id){
        return cart_s.getCartUser(id);
    }
    
    //Elements Mapping

    
    @PutMapping("/cart/{c_id}/element/{p_id}/{quantity}")
    public CartElement addElementToCart(@PathVariable("c_id") long  CartId,@PathVariable("p_id")long  ProductId,@PathVariable("quantity") int quantity){
       return cart_s.addElementToCart(CartId, product_s.getProductById(ProductId), quantity);       
    }
    @DeleteMapping("/cart/{c_id}/element{p_id}")
        public CartElement removeElementFromCart(@PathVariable("c_id") long  CartId,@PathVariable("e_id")long ElementId ){
           return cart_s.removeElementFromCart(CartId,ElementId);
        }
    }