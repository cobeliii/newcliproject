package com.cobeliii.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDataAccessService implements UserDao{
    private static final List<User> users = new ArrayList<>();

    static {
        users.add(new User(UUID.randomUUID(), "Jorge"));
        users.add(new User(UUID.randomUUID(), "Juan"));
        users.add(new User(UUID.randomUUID(), "Jose"));
        users.add(new User(UUID.randomUUID(), "Javier"));

    }


    @Override
    public List<User> getUsers() {
        return users;
    }
}
