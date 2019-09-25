package my.task.com.controller;

import my.task.com.model.User;
import my.task.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "index";
    }


    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public String getUser(ModelMap model, User user)   {
        boolean isExists = userService.exist(user.getLogin());
        if (isExists == true) {
            model.addAttribute("user", userService.getUserByLogin(user.getLogin()));
            return "getUser";
        } else {
            model.addAttribute("is_not_exists", "user is not exists");
            return "index";
        }
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(ModelMap model, User user)  {
        boolean isExists = userService.exist(user.getLogin());
        if (isExists != true) {
            userService.addUser(user);
            model.addAttribute("has_added", "user has been added");
            return "index";
        } else {
            model.addAttribute("is_exists", "user already exists");
            return "index";
        }
    }

    @RequestMapping(value = "/edit/{login}", method = RequestMethod.POST)
    public String edit(@PathVariable String login, User user) {
        userService.edit(login, user.getLastName());
        return "redirect:/";
    }
}


