package shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import shopping.model.User;

@Component
public interface UserRepo extends CrudRepository<User, Long> {
   
}