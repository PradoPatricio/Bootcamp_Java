package shopping.services;

import java.util.List;

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
            int intId = (int)(id);
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
            int intId = (int)(id);
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
            int intId = (int)(id);
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