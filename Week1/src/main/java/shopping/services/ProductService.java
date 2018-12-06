package shopping.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.models.Product;
import shopping.repositories.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo; 
	
	

	public Product getProductById(long id) {
        Product product = productRepo.findById(id);
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
        return productRepo.save(product);	
       
    }

	public Product deleteProduct(long id) {        
      
        if(productRepo.existsById(id))  {
            Product product = productRepo.findById(id);
            productRepo.delete(product);
            return product;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public Product editProduct(long id,Product product){
       
            if(productRepo.existsById(id))  {
                Product oldProduct = productRepo.findById(id);
                productRepo.delete(oldProduct);
                productRepo.save(product);
            return product;
            }
            else{
                throw new IllegalArgumentException();
            }
        }
        
}