package shopping.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import shopping.models.Cart;


@Component
public interface CartRepo extends CrudRepository<Cart, Integer> {
    List<Cart> findAll();
    Cart findById(Long id);
    Boolean existsById(Long id);
}