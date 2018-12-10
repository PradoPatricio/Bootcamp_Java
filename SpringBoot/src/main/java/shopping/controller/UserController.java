package shopping.controller;

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
import shopping.model.User;
import shopping.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController<UsuarioRepo> {

    @Autowired
    private UserService user_s;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/users")
    public UserDto newUser(@RequestBody UserDto user) {
      User newUser =user_s.addNewUser(convertToEntity(user));
       return convertToDto(newUser);
    }

   
    @GetMapping("/users")
    public List<UserDto> getUsers(){
        return convertToDto(user_s.getUsers());
    }

   
    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
       return convertToDto(user_s.getUserById(id));
    }    


    @DeleteMapping("/users/{id}")
    public UserDto deleteUsuario(@PathVariable("id") Long id){
        return convertToDto(user_s.deleteUser(id));
    }

    @PutMapping("/users/{id}")
    public UserDto editUser(@PathVariable("id") Long id,@RequestBody User user){
        return convertToDto(user_s.editUser(id, user));       
    }
    


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
