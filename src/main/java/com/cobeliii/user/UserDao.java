package com.cobeliii.user;

import java.util.List;
import java.util.UUID;

public interface UserDao {
    List<User> getUsers();
    User findUserById(UUID userId);
}
