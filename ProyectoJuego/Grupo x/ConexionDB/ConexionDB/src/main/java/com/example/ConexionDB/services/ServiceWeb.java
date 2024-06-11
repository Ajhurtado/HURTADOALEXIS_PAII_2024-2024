package com.example.ConexionDB.services;



import com.example.ConexionDB.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ServiceWeb {//capa controlador

    @Autowired
    private UserService userService;

    private List<User> userList = new ArrayList<>();

    @PostMapping(value = "/createUser")
    private void createUser(@RequestBody User user) {
        userService.save(user);
    }


    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAll();
    }


}
