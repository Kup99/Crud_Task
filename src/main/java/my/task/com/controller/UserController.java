package my.task.com.controller;

import my.task.com.model.User;
import my.task.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {


    @Autowired
    UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "index";
    }


    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public String getUser(ModelMap model, User user) {
        boolean isExists = userService.exist(user.getLogin());
        if (isExists == true) {
            model.addAttribute("user", userService.getUserByLogin(user.getLogin()));
            return "getUser";
        } else {
            //TODO: to add display
            return "redirect:/"+"isNotExists";
        }


    }


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(ModelMap model, User user) {

        boolean isExists = userService.exist(user.getLogin());
        if (isExists != true) {

            userService.addUser(user);
            return "redirect:/";
        } else {
            model.addAttribute("exists", "user already exists");
            return "redirect:/" + "isExists";
        }
    }


    @RequestMapping(value = "/{isExists}", method = RequestMethod.GET)
    public String getIndexIfNotExists(@PathVariable String isExists, ModelMap model) {
        if (isExists.equals("isExists")) {
            model.addAttribute("login", "Login already exists");
            return "index";
        } else {
            model.addAttribute("login", "Login is not exists");
            return "index";
        }
    }


    @RequestMapping(value = "/edit/{login}", method = RequestMethod.POST)
    public String edit(@PathVariable String login, ModelMap model,User user) {
        userService.edit(login,user.getLastName());
        return "redirect:/";


    }}


// TODO:Возможно вы имели ввиду
//TODO:Логин уже занят

