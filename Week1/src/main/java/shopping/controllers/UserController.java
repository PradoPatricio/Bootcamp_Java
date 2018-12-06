package shopping.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shopping.dto.UserDto;
import shopping.models.User;
import shopping.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController<UsuarioRepo> {

    @Autowired
    private UserService user_s;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/user")
    public UserDto newUser(@RequestBody UserDto user) {
      User newUser =user_s.addNewUser(convertToEntity(user));
       return convertToDto(newUser);
    }

   
    @GetMapping("/user")
    public List<UserDto> getUsers(){
        return convertToDto(user_s.getUsers());
    }

   
    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
       return convertToDto(user_s.getUserById(id));
    }    


    @DeleteMapping("/user/{id}")
    public UserDto deleteUsuario(@PathVariable("id") Long id){
        return convertToDto(user_s.deleteUser(id));
    }

    /*
    @PutMapping("/user/{id}")
    public UserDto editUser(@PathVariable("id") Long id,@RequestBody User user){
        return convertToDto(user_s.editUser(id, user));       
    }
    */


    private UserDto convertToDto(User user) {
        return  modelMapper.map(user, UserDto.class);
        
    }
    private List<UserDto> convertToDto(List<User> products) {
        List<UserDto> dtoList=new ArrayList<UserDto>();
        for (User product : products) {
            dtoList.add(convertToDto(product));
        }
        return dtoList ;
        
    }
    
    private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);         
    }
}
