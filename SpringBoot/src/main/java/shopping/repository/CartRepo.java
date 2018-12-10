package shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import shopping.model.Cart;


@Component
public interface CartRepo extends CrudRepository<Cart, Long> {
    
}