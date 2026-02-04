package com.cobeliii.user;

import com.cobeliii.exceptions.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class UserDataAccessService implements UserDao{
    private static final List<User> users = new ArrayList<>();

    static {
        users.add(new User( UUID.randomUUID(),"Jorge"));
        users.add(new User( UUID.randomUUID(),"Juan"));
        users.add(new User( UUID.randomUUID(),"Jose"));
        users.add(new User( UUID.randomUUID(),"Javier"));

    }


    @Override
    public List<User> getUsers() {
        if(users.isEmpty()) throw new ObjectNotFoundException("No users found");

        return users;
    }

    @Override
    public User findUserById(UUID userId) {
        return users.stream().filter(user -> user.getId().equals(userId))
                .findFirst().orElseThrow(()-> new ObjectNotFoundException("User not found"));
    }
}
