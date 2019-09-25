package my.task.com.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import my.task.com.dao.UserDao;
import my.task.com.model.User;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Spy
    List<User> users = new ArrayList<User>();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        users = getUserList();
    }


    @Test
    public void getUserByLogin() throws Exception {
        User user = users.get(0);
        when(userDao.getUserByLogin(anyString())).thenReturn(users);
        Assert.assertEquals(userService.getUserByLogin(user.getLogin()),users);
    }

    @Test
    public void addUser() throws Exception {
        doNothing().when(userDao).addUser(any(User.class));
        userService.addUser(any(User.class));
        verify(userDao, atLeastOnce()).addUser(any(User.class));
    }

    @Test
    public void exist() throws Exception {
        when(userDao.exist(anyString())).thenReturn(true);
        userService.exist(anyString());
        verify(userDao, atLeastOnce()).exist(anyString());

    }

    @Test
    public void edit() throws Exception {
        doNothing().when(userDao).edit(anyString(), anyString());
        userService.edit(anyString(), anyString());
        verify(userDao, atLeastOnce()).edit(anyString(), anyString());

    }

    public List<User> getUserList() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("Kirill");
        user1.setLastName("Fufelov");
        user1.setLogin("Log");
        users.add(user1);
        return users;
    }
}
