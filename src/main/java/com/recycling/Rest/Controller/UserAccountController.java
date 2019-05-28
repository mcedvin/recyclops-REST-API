package com.recycling.Rest.Controller;


import com.recycling.Rest.Service.UserAccountService;
import com.recycling.production.ChallengeAccepted;
import com.recycling.production.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/useraccounts")
public class UserAccountController {

    @Autowired
    private UserAccountService UserAccountService;

    //        @GetMapping(value = "/all")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<UserAccount> getAllUserAccounts() {
        return UserAccountService.getAllUserAccounts();
    }

    //TODO: lägga till en challenge baserat på useraccount-id, tar emot en challenge
    //    @RequestMapping(value = "/{id}/addChallenge", method = RequestMethod.GET)

    //TODO: complete:a en challenge baserat på useraccount-id, tar emot en challenge
    //    @RequestMapping(value = "/{id}/addChallenge", method = RequestMethod.GET)


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserAccount getUserAccountById(@PathVariable("id") int id) {
        return UserAccountService.getUserAccountById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUserAccountById(@PathVariable("id") int id) {
        UserAccountService.removeUserAccountById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUserAccount(@RequestBody final UserAccount UserAccount) {
        UserAccountService.updateUserAccount(UserAccount);
    }
    @RequestMapping(value = "/acceptchallenge", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void acceptChallenge(@RequestBody final String[] info){
        UserAccountService.acceptChallenge(info);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUserAccount(@RequestBody UserAccount UserAccount) {
        UserAccountService.addUserAccount(UserAccount);
    }
}
