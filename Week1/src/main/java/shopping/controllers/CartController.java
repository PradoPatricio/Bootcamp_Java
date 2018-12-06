package shopping.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shopping.models.CartElement;
import shopping.models.User;
import shopping.services.CartService;
import shopping.dto.CartDto;
import shopping.dto.CartElementDto;
import shopping.dto.UserDto;
import shopping.models.Cart;

@RestController
@RequestMapping("/api")
public class CartController {    
    @Autowired
    private CartService cart_s;
    @Autowired
    private ModelMapper modelMapper;

    //Cart mapping
    @GetMapping("/cart/{id}")
    public List<CartElementDto> getCartElements(@PathVariable("id") Long Id){
        return convertToDtoElement(cart_s.getCartElements(Id)); 
    }
    

    @GetMapping("/cart")                                                        
    public List<CartDto> getAllCarts(){
        return convertToDto(cart_s.getCarts());
    }


    @PutMapping("/cart/{id}/user/{user_id}")  
    public CartDto setCartUser(@PathVariable("id") Long id, @PathVariable("user_id") Long userId) {
        return convertToDto(cart_s.setCartUser(id, userId));
    }


    @GetMapping("/cart/{id}/user")
    public UserDto getCartUser(@PathVariable("id") Long id){       
        return modelMapper.map(cart_s.getCartUser(id), UserDto.class);
    }

    @PostMapping("/cart")                                             //new cart
    public CartDto newCart(@RequestBody CartDto cartDto){ 
        Cart newCart=cart_s.addNewCart(convertToEntity(cartDto));
        return convertToDto(newCart);
    }
   

    @DeleteMapping("/cart/{id}")
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

    //Elements Mapping

    
   
    @DeleteMapping("/cart/{c_id}/element{p_id}")
    public CartElementDto removeElementFromCart(@PathVariable("c_id") Long  CartId,@PathVariable("e_id")Long ElementId ){
        return convertToDto(cart_s.removeElementFromCart(CartId,ElementId));
    }
        
   
    @PutMapping("/cart/{c_id}/element/{p_id}/{quantity}")
    public CartDto addElementToCart(@PathVariable("c_id") Long CartId,@PathVariable("p_id")Long  ProductId,@PathVariable("quantity") int quantity){
       return convertToDto(cart_s.addElementToCart(CartId, ProductId, quantity));       
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
    
    private Cart convertToEntity(CartDto cartDto) {
        return modelMapper.map(cartDto, Cart.class);         
    }

    private CartElementDto convertToDto(CartElement element) {
        return  modelMapper.map(element, CartElementDto.class);
        
    }
    private List<CartElementDto> convertToDtoElement(List<CartElement> elements) {
        List<CartElementDto> dtoList=new ArrayList<CartElementDto>();
        for (CartElement element : elements) {
            dtoList.add(convertToDto(element));
        }
        return dtoList ;        
    }

}