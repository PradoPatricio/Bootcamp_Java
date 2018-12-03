package shopping.services;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import static java.lang.Math.toIntExact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.models.Product;
import shopping.repositories.ProductRepo;
@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo; 
	
	

	public Product getProductById(long id) {
        int intId = toIntExact(id);
        Product product = productRepo.findById(intId).get();
        if(product!=null){
            return product;
        }
        throw new IllegalArgumentException();
    }
    
    public List<Product> getProducts() {		
		return productRepo.findAll();	
    }

    public Product addNewProduct(Product product) {
        if (product == null) {
            product=new Product();                
        }        
        productRepo.save(product);	
        return product;
    }

	public Product deleteProduct(long id) {        
        int intId = toIntExact(id);
        if(productRepo.existsById(intId))  {
            Product product = productRepo.findById(intId).get();
            productRepo.deleteById(intId);
        return product;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public Product editProduct(long id,Product product){
        int intId = toIntExact(id);
            if(productRepo.existsById(intId))  {
                productRepo.deleteById(intId);
                productRepo.save(product);
            return product;
            }
            else{
                throw new IllegalArgumentException();
            }
        }
        
}