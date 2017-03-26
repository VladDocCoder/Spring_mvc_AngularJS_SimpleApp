package web.demo.rest.tests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import web.demo.rest.domain.User;
import web.demo.rest.services.UserService;
import web.demo.rest.services.UserServiceImpl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Володимир on 11.10.16.
 */
public class UserServiceImplTest {

    @Mock
    private UserService service;

    @InjectMocks
    private UserServiceImpl serviceImpl;

    private User testUser;
    List<User> list;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testUser = new User(1L, "testName", "testLocation", "testEmail");
        list = new ArrayList<User>() {{
            add(testUser);
        }};
        serviceImpl.setUsers(list);

    }

    @Test
    public void findByIdTest() {

        assertEquals(testUser, serviceImpl.findById(1L));
    }

    @Test
    public void findByNameTest() {

        assertEquals(testUser, serviceImpl.findByName("testName"));
    }


    @Test
    public void updateUserTest() {

        testUser.setUsername("test2");
        serviceImpl.updateUser(testUser);

        assertEquals("test2", serviceImpl.findById(1L).getUsername());
    }

    @Test
    public void deleteUserByIdTest() {

        serviceImpl.deleteUserById(1L);
        assertEquals(0, list.size());
    }

    @Test
    public void getAllUsersTest() {

        assertEquals(1, list.size());
    }

    @Test
    public void isUserExistTest() {

        assertEquals(true, serviceImpl.isUserExist(testUser));
    }
}
