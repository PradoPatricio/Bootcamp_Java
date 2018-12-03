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
import shopping.services.ProductService;

@RestController
public class ProductController{
    
    @Autowired
    private ProductService products_s;  
   

    
    @GetMapping("/product")
    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = products_s.getProducts();
		return products;
    }
    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") long  productId){
        Product product=products_s.getProductById(productId);
        return product;
    }

    @PostMapping("/product")
    public Product newProduct(@RequestBody Product product){ 
        Product newProduct=products_s.addNewProduct(product);
        return newProduct;
    }
  
    @PutMapping("/product/{id}")
    public Product editProduct(@PathVariable("id") long  ProductId,@RequestBody Product product){
        Product newProduct=products_s.editProduct(ProductId,product);
        return newProduct;
    }

    @DeleteMapping("/product/{id}")
    public Product deleteProduct(@PathVariable("id") long productId){
        Product deletedP=products_s.deleteProduct(productId);
        return deletedP;
    }  
}