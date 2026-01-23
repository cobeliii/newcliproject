package com.cobeliii.user;

import java.util.ArrayList;
import java.util.List;


public class UserDataAccessService implements UserDao{
    private static final List<User> users = new ArrayList<>();

    static {
        users.add(new User( "Jorge"));
        users.add(new User( "Juan"));
        users.add(new User( "Jose"));
        users.add(new User( "Javier"));

    }


    @Override
    public List<User> getUsers() {
        return users;
    }
}
