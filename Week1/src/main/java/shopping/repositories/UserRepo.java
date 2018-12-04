package shopping.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import shopping.models.User;

@Component
public interface UserRepo extends CrudRepository<User, Integer> {
    List<User> findAll();
}