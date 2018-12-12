package shopping.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import shopping.model.Item;


@Component
public interface ItemRepo extends CrudRepository<Item, Long> {
}