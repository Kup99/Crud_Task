package my.task.com.controller;
import my.task.com.model.User;
import my.task.com.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.ModelMap;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    UserService service;

    @InjectMocks
    UserController userController;

    @Spy
    List<User> users = new ArrayList<User>();

    @Spy
    ModelMap model;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        users = testUserList();
    }

    @Test

    public void getIndexPageTest() {
        Assert.assertEquals(userController.index(model), "index");
    }

    @Test

    public void getUserIfUserExists() {

        when(service.exist(anyString())).thenReturn(true);
        Assert.assertEquals(userController.getUser(model, users.get(0)), "getUser");
        Assert.assertEquals(model.get("user"), service.getUserByLogin(anyString()));
    }

    @Test

    public void getUserIfUserNotExists() {
        when(service.exist(anyString())).thenReturn(false);
        Assert.assertEquals(userController.getUser(model, users.get(0)), "index");
        Assert.assertEquals(model.get("is_not_exists"), "user is not exists");
    }


    @Test
    public void addUserIfUserNotExists() {
        when(service.exist(anyString())).thenReturn(false);
        Assert.assertEquals(userController.addUser(model, users.get(0)), "index");
        Assert.assertNotNull(users.get(0));

    }

    @Test
    public void addUserIfUserExist() {
        when(service.exist(anyString())).thenReturn(true);
        Assert.assertEquals(userController.addUser(model, users.get(0)), "index");
    }

    @Test
    public void edit() {
        User user = users.get(0);
        doNothing().when(service).edit(anyString(), anyString());
        Assert.assertEquals(userController.edit("some", user), "redirect:/");
    }


    public List<User> testUserList() {
        User user = new User();
        user.setLogin("alien");
        user.setFirstName("test");
        user.setLastName("user");
        user.setId(1L);
        users.add(user);
        return users;
    }

}


