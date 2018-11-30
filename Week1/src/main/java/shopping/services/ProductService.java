package shopping.services;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import shopping.models.Product;
@Service
public class ProductService {
    private Map<Long,Product> allProducts = new HashMap<>(); //map allow easy access to products 
	private AtomicLong productCount = new AtomicLong();
	

	public Product getProductById(long id) {
			return allProducts.get(id);		
	}
    public ArrayList<Product> getProducts() {		
		return new ArrayList<>(allProducts.values());		
    }
    public Product addNewProduct(Product product) {
		if (product != null) {
            product.setId(productCount.incrementAndGet());
            allProducts.put(product.getId(), product);	
		}
        return product;	
    }
	public Product deleteProduct(long id) {        
        Product deleted=allProducts.get(id);
        allProducts.remove(id);
        return deleted;
    }
    public Product editProduct(long id,Product product){
        if(allProducts.containsKey(id)){
            allProducts.replace(id, product);
        return product;
        }
        else{
            return null;
        }
        
    }   
        
}