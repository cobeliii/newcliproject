package com.cobeliii.user;

import java.util.List;
import java.util.UUID;

public class UserService {
    private final List<User> users;
    private UserDataAccessService data = new UserDataAccessService();

    public UserService() {
        users = data.getUsers();
    }

    public void printUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public User findUserById(UUID id) {
        for (User user : users) {
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public User findUserByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)){
                return user;
            }
        }

        return null;
    }
}
