package com.recycling.Rest.Service;

import com.recycling.Rest.Dao.ChallengeSQLDao;
import com.recycling.production.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeSQLDao challengeSQLDao;

    public Collection<Challenge> getAllChallenges() {
        return challengeSQLDao.getAllChallenges();
    }

    public Challenge getChallengeByName(String name) {
        return challengeSQLDao.getChallengeByName(name);
    }

    public void deleteChallengeByName(String name) {
        challengeSQLDao.deleteChallengeByName(name);
    }

    public void updateChallenge(Challenge c) {
        challengeSQLDao.updateChallenge(c);
    }

    public void addChallenge(Challenge c) {
        challengeSQLDao.addChallenge(c);
    }
    public void editChallenge(Challenge c){
        challengeSQLDao.editChallenge(c);
    }
}
