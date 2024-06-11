package com.example.ConexionDB.services;



import com.example.ConexionDB.models.User;
import com.example.ConexionDB.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository repository;

    public User save(User user) {
        repository.save(user);
        return user;
    }

    public void retrieve() {
        List<User> listUser = repository.findAll();
        for (User user : listUser) {
            System.out.println(user.toString());
        }
    }

    public List<User> getAll() {
        return repository.findAll(); // Este método obtiene todos los usuarios de la base de datos usando Spring Data JPA
    }

}
