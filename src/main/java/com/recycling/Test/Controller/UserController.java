package com.recycling.Test.Controller;


import com.recycling.Test.Dao.UserAccountSQLDao;
import com.recycling.Test.Service.UserService;
import com.recycling.recycling.production.User;
import com.recycling.recycling.production.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    UserAccountSQLDao userAccountSQLDao;

    //        @GetMapping(value = "/all")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public User getUserById(@PathVariable("id") int id) {
//        return userService.getUserById(id);
//    }

    @RequestMapping(value = "/{mail}", method =  RequestMethod.GET)
    public User getUserByEmail(@PathVariable("mail") String mail){
    return userService.getUserByEmail(mail);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable("id") int id) {
        userService.removeUserById(id);
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
}
