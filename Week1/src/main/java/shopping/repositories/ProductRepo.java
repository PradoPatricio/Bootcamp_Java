package shopping.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import shopping.models.Product;

@Component
public interface ProductRepo extends CrudRepository<Product, Integer> {
    List<Product> findAll();
}