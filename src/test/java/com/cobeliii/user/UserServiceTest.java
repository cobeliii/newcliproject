package com.cobeliii.user;

import com.cobeliii.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService underTest;


    @Test
    void itShouldGetUsers(){
        List<User> users = List.of(
                new User( UUID.randomUUID(),"Jorge")
        );
        when(userDao.getUsers()).thenReturn(users);
        var expected = underTest.getUsers();
        assertThat(expected).isEqualTo(users);
    }

    @Test
    void itShouldGetUserById(){
        User user = new User( UUID.randomUUID(),"Jorge");
        when(userDao.findUserById(user.getId())).thenReturn(user);
        var expected = underTest.getUserById(user.getId());
        assertThat(expected).isEqualTo(user);
    }


    @Test
    void itShouldFindUserByName(){
        User user = new User( UUID.randomUUID(),"Jorge");
        when(userDao.getUsers()).thenReturn(List.of(user));
        underTest.findUserByName(user.getName());
        verify(userDao).getUsers();
    }

    @Test
    void itShouldNotFindUserByName(){
        User user = new User( UUID.randomUUID(),"Jorge");
        when(userDao.getUsers()).thenReturn(List.of(user));
        assertThatThrownBy(() -> underTest.findUserByName("Juan"))
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessageContaining("User not found");
    }
}