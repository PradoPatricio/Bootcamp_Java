package shopping.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shopping.models.User;
import shopping.services.UserService;

@RestController
public class UserController<UsuarioRepo> {

    @Autowired
    private UserService user_s;

    @PostMapping("/user")
    public User newUser(@RequestBody User user) {
       return user_s.addNewUser(user);
    }

   
    @GetMapping("/user")
    public List<User> getUsers(){
        return user_s.getUsers();
    }

   
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int id) {
       return user_s.getUserById(id);
    }

    
    @PutMapping("/user/{id}")
    public User editUser(@PathVariable("id") int id,@RequestBody User user){
        return user_s.editUser(id, user);       
    }


    @DeleteMapping("/user/{id}")
    public User deleteUsuario(@PathVariable("id") int id){
        return user_s.deleteUser(id);
    }
}
