package shopping.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import shopping.model.Cart;


@Component
public interface CartRepo extends CrudRepository<Cart, Long> {
    
}