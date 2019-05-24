package com.recycling.Rest.Controller;


import com.recycling.Rest.Dao.UserAccountSQLDao;
import com.recycling.Rest.Service.UserService;
import com.recycling.production.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountSQLDao userAccountSQLDao;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/{mail}", method = RequestMethod.GET)
    public User getUserByEmail(@PathVariable("mail") String mail) {
        return userService.getUserByEmail(mail);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User getAndAuthenticateUser(@RequestBody User user){
        return userService.getAndAuthenticateUser(user);
    }

    @RequestMapping(value = "/{mail}", method = RequestMethod.DELETE)
    public void deleteUserByMail(@PathVariable("mail") String mail) {
        userService.deleteUserByMail(mail);
    }

    @RequestMapping(value = "/put", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody User user) {
        if (user.getUserAccount() != null) {
            userAccountSQLDao.addUserAccount(user.getUserAccount());
        }
        userService.updateUser(user);
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody final User user) {
        if (user.getUserAccount() != null) {
            userAccountSQLDao.addUserAccount(user.getUserAccount());
        }
        userService.addUser(user);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editUser(@RequestBody final User user) {
        userService.editUser(user);
    }
}
