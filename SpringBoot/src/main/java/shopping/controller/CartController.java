package shopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shopping.model.Item;
import shopping.service.CartService;
import shopping.dto.CartDto;
import shopping.dto.ItemDto;
import shopping.dto.UserDto;
import shopping.model.Cart;

@RestController
@RequestMapping("/api")
public class CartController {    
    @Autowired
    private CartService cart_s;
    @Autowired
    private ModelMapper modelMapper;

    //Cart mapping
    @GetMapping("/carts/{id}")
    public List<ItemDto> getItems(@PathVariable("id") Long Id){
        return convertToDtoItem(cart_s.getItems(Id)); 
    }
    

    @GetMapping("/carts")                                                        
    public List<CartDto> getAllCarts(){
        return convertToDto(cart_s.getCarts());
    }


    @PutMapping("/carts/{id}/user/{user_id}")  
    public CartDto setCartUser(@PathVariable("id") Long id, @PathVariable("user_id") Long userId) {
        return convertToDto(cart_s.setCartUser(id, userId));
    }


    @GetMapping("/carts/{id}/user")
    public UserDto getCartUser(@PathVariable("id") Long id){       
        return modelMapper.map(cart_s.getCartUser(id), UserDto.class);
    }

    @PostMapping("/carts")                                             //new cart
    public CartDto newCart(){ 
        Cart newCart=cart_s.addNewCart();
        return convertToDto(newCart);
    }
   

    @DeleteMapping("/carts/{id}")
        public CartDto deleteCart(@PathVariable("id") Long  Id){
           return convertToDto(cart_s.deleteCart(Id));
        }

   

    /*
    @PutMapping("/cart/{id}")                                           //edit cart
    public Cart editCart(@PathVariable("id") Long  Id,@RequestBody Cart cart){ 
        cart_s.editCart(Id,cart);
        return cart;
    }
    */

    //Items Mapping

    
   
    @DeleteMapping("/carts/{c_id}/items/{e_id}")
    public ItemDto removeItemFromCart(@PathVariable("c_id") Long  CartId,@PathVariable("e_id")Long ItemId ){
        return convertToDto(cart_s.removeItemFromCart(CartId,ItemId));
    }
        
   
    @PutMapping("/carts/{c_id}/items/{p_id}/{quantity}")
    public Item addItemToCart(@PathVariable("c_id") Long CartId,@PathVariable("p_id")Long  ProductId,@PathVariable("quantity") int quantity){
        return cart_s.addItemToCart(CartId, ProductId, quantity);
             
    }



    private CartDto convertToDto(Cart cart) {
        return  modelMapper.map(cart, CartDto.class);
        
    }
    private List<CartDto> convertToDto(List<Cart> carts) {
        List<CartDto> dtoList=new ArrayList<CartDto>();
        for (Cart cart : carts) {
            dtoList.add(convertToDto(cart));
        }
        return dtoList ;        
    }
    
    

    private ItemDto convertToDto(Item item) {
        return  modelMapper.map(item, ItemDto.class);
        
    }
    private List<ItemDto> convertToDtoItem(List<Item> items) {
        List<ItemDto> dtoList=new ArrayList<ItemDto>();
        for (Item item : items) {
            dtoList.add(convertToDto(item));
        }
        return dtoList ;        
    }

}