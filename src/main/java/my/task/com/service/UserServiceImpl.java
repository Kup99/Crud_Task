package my.task.com.service;

import my.task.com.dao.UserDao;
import my.task.com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;

    public List<User> getUserByLogin(String login) {

        return userDao.getUserByLogin(login);

    }

    public void addUser(User user) {
        userDao.addUser(user);
    }


    public boolean exist(String login) {
        return userDao.exist(login);
    }

    public void edit(String login, String lastName) {
        userDao.edit(login, lastName);
    }
}



