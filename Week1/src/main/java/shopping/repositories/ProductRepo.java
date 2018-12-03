package shopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import shopping.models.Product;

@Component
public interface ProductRepo extends JpaRepository<Product, Integer> {
}