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

    public User findUserById(UUID id) {
        Stream<User> user = userDao.getUsers().stream()
                .filter(u -> u.getId().equals(id));

        return user.findFirst().orElse(null);
    }

    public User findUserByName(String name) {

        Stream<User> user = userDao.getUsers().stream()
                .filter(u -> u.getName().equalsIgnoreCase(name));

        return user.findFirst().orElse(null);
    }
}
