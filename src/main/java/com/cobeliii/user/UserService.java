package com.cobeliii.user;

import java.util.UUID;
import java.util.stream.Stream;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void printUsers() {
        userDao.getUsers().forEach(System.out::println);
    }

    // TODO: implement exceptions and throw instead of nulls
    public User findUserById(UUID userId) {
        return  userDao.getUsers().stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }


    // TODO: same here
    public User findUserByName(String name) {
        return userDao.getUsers().stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
