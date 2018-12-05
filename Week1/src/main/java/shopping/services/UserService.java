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
            User user = userRepo.findById(id);
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
            
            if(userRepo.existsById(id))  {
              User user =userRepo.findById(id);
              userRepo.delete(user);
              return user;
            }
            else{
                throw new IllegalArgumentException();
            }
            
            
        }
        public User editUser(long id,User user){
            
            if(userRepo.existsById(id))  {
                User oldUser =userRepo.findById(id);
                userRepo.delete(oldUser);
            return user;
            }
            else{
                throw new IllegalArgumentException();
            }
                   
    }
}