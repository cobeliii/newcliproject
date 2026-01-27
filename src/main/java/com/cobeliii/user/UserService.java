package com.cobeliii.user;

import com.cobeliii.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    // TODO: implement exceptions and throw instead of nulls
    public User findUserById(UUID userId) {
        return  userDao.getUsers().stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(()-> new ObjectNotFoundException("User not found"));
    }




    // TODO: same here
    public User findUserByName(String name) {
        return userDao.getUsers().stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(()-> new ObjectNotFoundException("User not found"));
    }
}
