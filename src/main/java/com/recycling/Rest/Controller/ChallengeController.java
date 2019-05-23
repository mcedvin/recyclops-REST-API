package com.recycling.Rest.Controller;


import com.recycling.Rest.Dao.ChallengeSQLDao;
import com.recycling.Rest.Service.ChallengeService;
import com.recycling.production.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    //TODO: returnera http 400-kod ifall Challenge redan finns vid PUT/POST
    //TODO: hantera ifall Challenge
    @Autowired
    private ChallengeService challengeService;
    @Autowired
    ChallengeSQLDao challengeSQLDao;

    //        @GetMapping(value = "/all")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Challenge> getAllChallenges() {
        return challengeService.getAllChallenges();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public Challenge getChallengeByEmail(@PathVariable("name") String name) {
        return challengeService.getChallengeByName(name);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public void deleteChallengeByMail(@PathVariable("name") String name) {
        challengeService.deleteChallengeByName(name);
    }

    @RequestMapping(value = "/put", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateChallenge(@RequestBody final Challenge challenge) {

        challengeService.updateChallenge(challenge);
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addChallenge(@RequestBody final Challenge challenge) {
        challengeService.addChallenge(challenge);
    }

    @RequestMapping(value = "/patch", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editChallenge(@RequestBody final Challenge challenge) {
        challengeService.editChallenge(challenge);
    }
}
