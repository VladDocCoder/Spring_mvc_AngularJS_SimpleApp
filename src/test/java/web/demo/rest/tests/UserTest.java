package web.demo.rest.tests;

import org.junit.Before;
import org.junit.Test;
import web.demo.rest.domain.User;

import static org.junit.Assert.*;

/**
 * Created by Володимир on 12.10.16.
 */
public class UserTest {

    private User testUser;

    @Before
    public void setup() {
        testUser = new User(1L, "testName", "testLocation", "testEmail");
    }

    @Test
    public void equalsTest() {
        User eqUser = testUser;
        assertEquals(true, testUser.equals(eqUser));
    }

    @Test
    public void setGetUsernameTest() {
        testUser.setUsername("2test");
        assertEquals("2test", testUser.getUsername());
    }

    @Test
    public void setGetLocationTest() {
        testUser.setLocation("2test");
        assertEquals("2test", testUser.getLocation());
    }

    @Test
    public void setGetEmailTest() {
        testUser.setEmail("2test");
        assertEquals("2test", testUser.getEmail());
    }

    @Test
    public void setGetIdTest() {
        testUser.setId(2L);
        assertEquals(2L, testUser.getId());
    }
}
