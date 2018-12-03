package shopping.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import shopping.models.Cart;


@Component
public interface CartRepo extends JpaRepository<Cart, Integer> {
}