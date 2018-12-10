package shopping.dto;


import java.util.ArrayList;
import java.util.List;


public class CartDto {   
    private Long id;   
    private UserDto user;  
    private List<CartElementDto> shopList;
   
    public CartDto(){
        
    }
    public CartDto(Long id,UserDto user){
        this.id=id;
        this.user=user;
        this.shopList=new ArrayList<>();
        
    }

    public void setId(Long id) {
        this.id=id;
    }
    public Long getId(){
        return id;
    }
    public void setUser(UserDto user){
        this.user=user;
    }
    public UserDto getUser(){
        return user;
    }
    public void setShopList(List<CartElementDto> shopList){
        this.shopList=shopList;
    }
    public List<CartElementDto> getShopList(){
        return shopList;
    }


   
}