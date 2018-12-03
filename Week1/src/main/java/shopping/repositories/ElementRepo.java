package shopping.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import shopping.models.CartElement;


@Component
public interface ElementRepo extends JpaRepository<CartElement, Integer> {
}