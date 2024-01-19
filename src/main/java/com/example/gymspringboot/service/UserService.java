package com.example.gymspringboot.service;

import com.example.gymspringboot.domain.User;
import com.example.gymspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public boolean login(String userName, String password) {
        User user = findByUserName(userName);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    public boolean changePassword(String userName, String oldPassword, String newPassword) {
        User user = findByUserName(userName);
        if (user != null) {
            if (user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public User save(User user) {
        user.setUsername(usernameGenerator(user.getFirstName(), user.getLastName()));
        user.setPassword(passwordGenerator());
        return userRepository.save(user);
    }

    public boolean existsByUsername(String userName) {
        return userRepository.existsByUsername(userName);
    }

    public User update(User user) {
        User updateUser = userRepository.findByUsername(user.getUsername());
        if (updateUser != null) {
            updateUser.setFirstName(user.getFirstName());
            updateUser.setLastName(user.getLastName());
            updateUser.setActive(user.getActive());
            save(updateUser);
            return updateUser;
        }
        return null;
    }

    public String usernameGenerator(String firstName, String lastName) {
        String username = firstName.toLowerCase() + "." + lastName.toLowerCase();
        if (existsByUsername(username)) {
            int i = 1;
            while (existsByUsername(username + i)) {
                i++;
            }
            username = username + i;
        }
        return username;
    }

    public String passwordGenerator() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            password.append((char) (Math.random() * 26 + 97));
        }
        return password.toString();
    }

    public void delete(String userName) {
        userRepository.deleteByUsername(userName);
    }

}
