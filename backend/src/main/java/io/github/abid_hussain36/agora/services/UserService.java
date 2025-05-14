package io.github.abid_hussain36.agora.services;

import io.github.abid_hussain36.agora.entities.User;
import io.github.abid_hussain36.agora.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    public UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // READ
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    // CREATE
    public User createUser(User user){
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());

        if(userByEmail.isPresent()){
            throw new IllegalStateException("User already present in database!");
        }

        userRepository.save(user);
        return user;
    }

    // UPDATE
    public User updateUser(User user){
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());

        if(!userByEmail.isPresent()){
            throw new IllegalStateException("No User found to update!");
        }

        userRepository.deleteById(user.getId());
        userRepository.save(user);
        return user;
    }

    // DELETE
    public User deleteUser(Long id){
        Optional<User> userById = userRepository.findById(id);

        if(!userById.isPresent()){
            throw new IllegalStateException("No User found to delete!");
        }

        userRepository.deleteById(id);
        return userById.get();
    }
}
