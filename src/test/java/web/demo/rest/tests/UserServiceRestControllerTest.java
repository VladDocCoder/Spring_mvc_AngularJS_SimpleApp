package web.demo.rest.tests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import web.demo.rest.controllers.UserServiceRestController;
import web.demo.rest.domain.User;
import web.demo.rest.exceptions.ConflictException;
import web.demo.rest.services.UserService;

import java.util.ArrayList;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class UserServiceRestControllerTest {

    @InjectMocks
    private UserServiceRestController restController;

    @Mock
    private UserService service;

    private ArgumentCaptor<User> userCaptor;

    private MockMvc mockMvc;

    private User testUser;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(restController).build();
        userCaptor = ArgumentCaptor.forClass(User.class);

    }

    @Test
    public void getAllUsersTest() throws Exception {
        testUser = new User(1L, "testName", "testLocation", "testEmail");
        when(service.getAllUsers()).thenReturn(new ArrayList<User>() {{
            add(testUser);
        }});
        mockMvc.perform(get("/user/"))
                .andDo(print())
                .andExpect(content().string("[{\"id\":1,\"username\":\"testName\",\"location\":\"testLocation\",\"email\":\"testEmail\"}]"))
                .andExpect(status().isOk());

        verify(service).getAllUsers();
        assertEquals(1, service.getAllUsers().size());
    }

    @Test
    public void getNonExistingAllUsersTest() throws Exception {
        when(service.getAllUsers()).thenReturn(new ArrayList<User>());
        mockMvc.perform(get("/user/"))
                .andExpect(status().isNoContent());

        verify(service).getAllUsers();
        assertEquals(0, service.getAllUsers().size());
    }

    @Test
    public void getUserTest() throws Exception {

        testUser = new User(1L, "testName", "testLocation", "testEmail");
        when(service.findById(1L)).thenReturn(testUser);
        mockMvc.perform(get("/user/1"))
                .andDo(print())
                .andExpect(content().string("{\"id\":1,\"username\":\"testName\",\"location\":\"testLocation\",\"email\":\"testEmail\"}"))
                .andExpect(status().isOk());

        verify(service).findById(1L);
        assertEquals("testLocation", service.findById(1L).getLocation());
    }

    @Test
    public void getNonExistingUser() throws Exception {

        when(service.findById(1L)).thenReturn(null);

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isNotFound());

        verify(service).findById(1L);
        assertEquals(null, service.findById(1L));
    }

    @Test
    public void createUserTest() throws Exception {

        testUser = new User(1L, "testName", "testLocation", "testEmail");
        when(!service.isUserExist(any(User.class))).thenReturn(false);

        mockMvc.perform(post("/user/")
                .content("{\"id\":1,\"username\":\"testName\",\"location\":\"testLocation\",\"email\":\"testEmail\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(header().string("Location", org.hamcrest.Matchers.notNullValue()))
                .andExpect(status().isCreated());

        verify(service).isUserExist(userCaptor.capture());
        assertEquals("testLocation", userCaptor.getValue().getLocation());
    }

    @Test
    public void createExistingUser() throws Exception {

        testUser = new User(1L, "testName", "testLocation", "testEmail");
        when(service.isUserExist(Matchers.any(User.class))).thenThrow(new ConflictException());
        mockMvc.perform(post("/user/")
                .content("{\"username\":\"testName\", \"location\":\"testLocation\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());

        verify(service).isUserExist(userCaptor.capture());
        assertEquals("testLocation", userCaptor.getValue().getLocation());
    }

    @Test
    public void updateUserTest() throws Exception {
        testUser = new User(1L, "testName", "testLocation", "testEmail");
        when(service.findById(1L)).thenReturn(testUser);
        mockMvc.perform(put("/user/1")
                .content("{\"username\":\"Donald\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().string("{\"id\":1,\"username\":\"Donald\",\"location\":null,\"email\":null}"))
                .andExpect(status().isOk());


        assertEquals("Donald", service.findById(1L).getUsername());
    }

    @Test
    public void deleteUserTest() throws Exception {
        testUser = new User(1L, "testName", "testLocation", "testEmail");
        when(service.findById(1L)).thenReturn(testUser);
        mockMvc.perform(delete("/user/1"))
                .andDo(print())
                .andExpect(header().string("Location", org.hamcrest.Matchers.nullValue()))
                .andExpect(status().isNoContent());

        assertEquals("testLocation", service.findById(1L).getLocation());
    }

    @Test
    public void isUserExistTest() {

        testUser = new User(1L, "testName", "testLocation", "testEmail");
        when(service.isUserExist(any(User.class))).thenReturn(true);
        assertEquals(true, service.isUserExist(testUser));
        verify(service).isUserExist(testUser);
    }

    @Test
    public void findUserByNameTest() {

        testUser = new User(1L, "testName", "testLocation", "testEmail");
        when(service.findByName("testName")).thenReturn(testUser);
        assertEquals(testUser, service.findByName("testName"));
        verify(service).findByName("testName");
    }

    @Test
    public void findUserByIdTest() {

        testUser = new User(1L, "testName", "testLocation", "testEmail");
        when(service.findById(1L)).thenReturn(testUser);
        assertEquals(testUser, service.findById(1L));
        verify(service).findById(1L);
    }

}

