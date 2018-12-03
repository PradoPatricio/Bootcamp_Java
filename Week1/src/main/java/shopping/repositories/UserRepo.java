package shopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import shopping.models.User;

@Component
public interface UserRepo extends JpaRepository<User, Integer> {
}