package shopping.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.model.User;
import shopping.repository.UserRepo;

@Service
public class UserService {
      
        
        @Autowired
        private UserRepo userRepo;
        


        
        //User CRUD
        public User getUserById(long id) {
            User user = this.userRepo.findById(id).get();
            if(user!=null){
                return user;
            }
            throw new IllegalArgumentException("Invalid user Id");
        }
        
        public List<User> getUsers(){
            return (List<User>)this.userRepo.findAll();
        }
        public User addNewUser(User user) {
            if (user != null) {
                return userRepo.save(user);	             
            }
            throw new IllegalArgumentException("Invalid user:null");
        }
        public User deleteUser(long id) {    

            if(userRepo.existsById(id))  {
              User user =this.userRepo.findById(id).get();
               userRepo.delete(user);
               return user;
            }
            else{
                throw new IllegalArgumentException("Invalid user Id");
            }
            
            
        }
        public User editUser(long id,User user){
            if(user!=null){
              if(userRepo.existsById(id))  {
                  User oldUser =this.userRepo.findById(id).get();
                  userRepo.delete(oldUser);
                  return user;
                 }
              else{
                 throw new IllegalArgumentException("Invalid user Id");
                 }      
             }
             else{
                throw new IllegalArgumentException("Invalid user:null");
             }
         }
}