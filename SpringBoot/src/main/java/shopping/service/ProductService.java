package shopping.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.model.Product;
import shopping.repository.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo; 
	
	

	public Product getProductById(long id) {
        if(this.productRepo.existsById(id))  {
            return this.productRepo.findById(id).get();
        }
        throw new IllegalArgumentException("Invalid product Id");
    }
    
    public List<Product> getProducts() {		
		return  (List<Product>)this.productRepo.findAll();	
    }

    public Product addNewProduct(Product product) {
        if (product != null) {
            return this.productRepo.save(product);	         
        }        
        throw new IllegalArgumentException("Invalid product : null");
    }
       
    

	public Product deleteProduct(long id) {              
        if(this.productRepo.existsById(id))  {
            Product product = this.productRepo.findById(id).get();
            this.productRepo.delete(product);
            return product;
        }       
        throw new IllegalArgumentException("Invalid product Id");        
    }

    public Product editProduct(long id,Product product){       
            if(this.productRepo.existsById(id))  {
                if(product!=null){
                 Product oldProduct = this.productRepo.findById(id).get();
                  this.productRepo.delete(oldProduct);
                  return this.productRepo.save(product);
                }
                throw new IllegalArgumentException("Invalid product : null");
            }            
            throw new IllegalArgumentException("Invalid product Id");
            }
        
        
}