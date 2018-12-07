package shopping.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import shopping.models.CartElement;


@Component
public interface ElementRepo extends CrudRepository<CartElement, Long> {
}