package shopping.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import shopping.model.CartElement;


@Component
public interface ElementRepo extends CrudRepository<CartElement, Long> {
}