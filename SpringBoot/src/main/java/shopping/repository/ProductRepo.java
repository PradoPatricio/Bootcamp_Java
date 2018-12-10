package shopping.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import shopping.model.Product;

@Component
public interface ProductRepo extends CrudRepository<Product, Long> {
    
}