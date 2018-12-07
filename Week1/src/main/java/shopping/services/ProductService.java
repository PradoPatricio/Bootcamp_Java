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
        Product product = this.productRepo.findById(id).get();
        if(product!=null){
            return product;
        }
        throw new IllegalArgumentException();
    }
    
    public List<Product> getProducts() {		
		return  (List<Product>)this.productRepo.findAll();	
    }

    public Product addNewProduct(Product product) {
        if (product == null) {
            product=new Product();                
        }        
        return this.productRepo.save(product);	
       
    }

	public Product deleteProduct(long id) {        
      
        if(this.productRepo.existsById(id))  {
            Product product = this.productRepo.findById(id).get();
            this.productRepo.delete(product);
            return product;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public Product editProduct(long id,Product product){
       
            if(this.productRepo.existsById(id))  {
                Product oldProduct = this.productRepo.findById(id).get();
                this.productRepo.delete(oldProduct);
                this.productRepo.save(product);
            return product;
            }
            else{
                throw new IllegalArgumentException();
            }
        }
        
}