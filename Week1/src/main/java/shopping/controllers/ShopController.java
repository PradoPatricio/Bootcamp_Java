package shopping.controllers;

import java.awt.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

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
import shopping.model.CartElement;
import shopping.model.Item;

@RestController
public class ShopController {
    private ArrayList<Item>itemList=new ArrayList<>();
    private ArrayList<CartElement>Cart=new ArrayList<>();
    private AtomicLong counter = new AtomicLong();
    private AtomicLong cartCounter = new AtomicLong();
  

    //Item mappings
    @GetMapping("/items")
    public ArrayList<Item> getItems(){
        return itemList; 
    }
    @GetMapping("/items/{id}")
    public Item getItemById(@PathVariable("id") long  itemId){
        for (Item i : itemList ) {
            if(i.getId()==itemId){
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    @PostMapping("/items")
    public Item newItem(@RequestBody Item item){ 
        item.setId(counter.incrementAndGet());
        itemList.add(item); 
        return item;
    }
  
    @PutMapping("/items/{id}")
    public Item editItem(@PathVariable("id") long  itemId,@RequestBody Item newItem){
        for (Item i : itemList ) {
            if(i.getId()==itemId){
                itemList.remove(i);
                newItem.setId(itemId);
                itemList.add(newItem);
                return newItem;
            }
        }
        throw new IllegalArgumentException();
    }

    //Cart mapping
    @GetMapping("/cart")
    public ArrayList<CartElement> getCartItems(){
        return Cart; 
    }
    @GetMapping("/cart/{id}")
    public CartElement getCartElementById(@PathVariable("id") long  Id){
        for (CartElement i : Cart ) {
            if(i.getId()==Id){
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    @PostMapping("/cart/{id}")
    public CartElement newCartElement(@PathVariable("id") long  Id){ 
        CartElement element=new CartElement();
        element.setId(cartCounter.incrementAndGet());
        Item newItem=getItemById(Id);   
        element.setItem(newItem);     
        Cart.add(element); 
        return element;
    }
  
    @PutMapping("/cart/{id}")
    public CartElement editCart(@PathVariable("id") long  Id,@RequestBody CartElement newCartElement){
        for (CartElement i : Cart ) {
            if(i.getId()==Id){
                Cart.remove(i);
                newCartElement.setId(Id);
                Cart.add(newCartElement);
                return newCartElement;
            }
        }
        throw new IllegalArgumentException();
    }




    @ResponseStatus(value=HttpStatus.BAD_REQUEST,reason="id not found")
    @ExceptionHandler(IllegalArgumentException.class)
    public void excHandler(){

    }

}