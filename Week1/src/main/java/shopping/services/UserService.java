package shopping.services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import static java.lang.Math.toIntExact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.models.User;
import shopping.repositories.UserRepo;

@Service
public class UserService {
      
        
        @Autowired
        private UserRepo userRepo;
        


        
        //User CRUD
        public User getUserById(long id) {
            int intId = toIntExact(id);
            User user = userRepo.findById(intId).get();
            if(user!=null){
                return user;
            }
            throw new IllegalArgumentException();
        }
        
        public List<User> getUsers(){
            return userRepo.findAll();
        }
        
        public User addNewUser(User user) {
            if (user == null) {
                user=new User();                
            }
            
            userRepo.save(user);	
            return user;	
        }
        public User deleteUser(long id) {    
            int intId = toIntExact(id);
            if(userRepo.existsById(intId))  {
                User user = userRepo.findById(intId).get();
            userRepo.deleteById(intId);
            return user;
            }
            else{
                throw new IllegalArgumentException();
            }
            
            
        }
        public User editUser(long id,User user){
            int intId = toIntExact(id);
            if(userRepo.existsById(intId))  {
                userRepo.deleteById(intId);
                userRepo.save(user);
            return user;
            }
            else{
                throw new IllegalArgumentException();
            }
                   
    }
}