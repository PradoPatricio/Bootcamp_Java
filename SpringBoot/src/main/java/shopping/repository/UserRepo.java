package shopping.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import shopping.model.User;

@Component
public interface UserRepo extends CrudRepository<User, Long> {
   
}