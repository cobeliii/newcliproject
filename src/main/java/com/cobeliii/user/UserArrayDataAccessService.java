package com.cobeliii.user;

import java.util.List;
import java.util.UUID;

public class UserArrayDataAccessService {
    private static final User[] users;

    static {
        users = new User[]{
                new User(UUID.randomUUID(), "Jorge"),
                new User(UUID.randomUUID(), "John"),
                new User(UUID.randomUUID(), "Amigoscode"),
                new User(UUID.randomUUID(), "Alex"),
        };
    }



    public User[] getUsers() {
        return users;
    }
}
