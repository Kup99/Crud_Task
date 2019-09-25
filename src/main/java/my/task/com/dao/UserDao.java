package my.task.com.dao;

import my.task.com.model.User;

import java.util.List;

/**
 * Created by 813632 on 19.09.2019.
 */
public interface UserDao {

    List<User> getUserByLogin(String login);

    void addUser(User user);

    boolean exist(String login);

    void edit(String login,String lastName);


}
