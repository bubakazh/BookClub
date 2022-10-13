package com.codingdojo.troy.bookclubtm.services;

import java.util.Optional;

import com.codingdojo.troy.bookclubtm.models.LoginUser;
import com.codingdojo.troy.bookclubtm.models.User;
import com.codingdojo.troy.bookclubtm.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User register(User newUser, BindingResult result) {

        Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
        if(potentialUser.isPresent()){
            result.rejectValue("email", "Matches", "An account with that email already exists.");
        }


        if(!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "Matches", "Passwords must match.");
        }

        if(result.hasErrors()) {
            return null;
        }

        String hashword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashword);
        return userRepo.save(newUser);
    }

    public User login(LoginUser newUser, BindingResult result) {
        // TO-DO: Additional validations!
        Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());

        if(!potentialUser.isPresent()) {
            result.rejectValue("email", "Matches", "User not found!");
            return null;
        }

        User user = potentialUser.get();

        // Reject if BCrypt password match fails
        if(!BCrypt.checkpw(newUser.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
            return null;
        }

        // Return null if result has errors
        if(result.hasErrors()) {
            return null;
        }

        return user;
    }
    public User findById(Long id) {
        Optional<User> potentialUser = userRepo.findById(id);
        return potentialUser.orElse(null);
    }
}

