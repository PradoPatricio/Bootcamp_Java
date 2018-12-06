package shopping.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shopping.dto.ProductDto;
import shopping.models.Product;
import shopping.services.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController{

    
    @Autowired
    private ProductService products_s;  
    @Autowired
    private ModelMapper modelMapper;

    
    @GetMapping("/product")
    public List<ProductDto> getProducts(){       
        return  convertToDto(products_s.getProducts());		
    }
    @GetMapping("/product/{id}")
    public ProductDto getProductById(@PathVariable("id") Long  productId){
        return convertToDto(products_s.getProductById(productId));
    }

    @PostMapping("/product")
    public ProductDto newProduct(@RequestBody ProductDto product){ 
        Product newProduct=products_s.addNewProduct(convertToEntity(product));
        return convertToDto(newProduct);
    }
  
    

    @DeleteMapping("/product/{id}")
    public ProductDto deleteProduct(@PathVariable("id") Long productId){
        Product deletedP=products_s.deleteProduct(productId);
        return convertToDto(deletedP);
    }  
    /*
    @PutMapping("/product/{id}")
    public ProductDto editProduct(@PathVariable("id") Long  ProductId,@RequestBody ProductDto product){
        Product newProduct=products_s.editProduct(ProductId,convertToEntity(product));
        return product;
    }
    */


    private ProductDto convertToDto(Product product) {
        return  modelMapper.map(product, ProductDto.class);
        
    }
    private List<ProductDto> convertToDto(List<Product> products) {
        List<ProductDto> dtoList=new ArrayList<ProductDto>();
        for (Product product : products) {
            dtoList.add(convertToDto(product));
        }
        return dtoList ;
        
    }
    
    private Product convertToEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);         
    }
}