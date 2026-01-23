package com.cobeliii.user;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService underTest;


    @Test
    void itShouldPrintUsers(){
        List<User> users = List.of(
                new User( "Jorge")
        );
        when(userDao.getUsers()).thenReturn(users);
        underTest.printUsers();
        verify(userDao).getUsers();
    }

    @Test
    void itShouldFindUserById(){
        User user = new User( "Jorge");
        when(userDao.getUsers()).thenReturn(List.of(user));
        underTest.findUserById(user.getId());
        verify(userDao).getUsers();
    }

    @Test
    void itShouldFindUserByName(){
        User user = new User( "Jorge");
        when(userDao.getUsers()).thenReturn(List.of(user));
        underTest.findUserByName(user.getName());
        verify(userDao).getUsers();
    }
}