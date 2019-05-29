package com.recycling.Rest.Dao;

import com.recycling.DB.repository.ChallengeRepository;
import com.recycling.production.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ChallengeSQLDao {

    @Autowired
    private ChallengeRepository challengeRepository;

    public Collection<Challenge> getAllChallenges(){
        return challengeRepository.findAll();
    }
    public Challenge getChallengeByName(String name){
        name = name.replace('+', ' ');
        for(Challenge c : challengeRepository.findAll())
            if(c.getName().equals(name))
                return c;
            return null;
    }
    public void deleteChallengeByName(String name){
        for(Challenge c : challengeRepository.findAll())
            if(c.getName().equals(name))
                challengeRepository.delete(c);
    }
    public void updateChallenge(Challenge updatedChallenge) {
//        for (Challenge c : challengeRepository.findAll()) {
//            if (c.getName().equals(updatedChallenge.getName())) {
//                c.setName(updatedChallenge.getName());
//                c.setDescription(updatedChallenge.getDescription());
//                c.setDuration(updatedChallenge.getDuration());
//                c.setImage(updatedChallenge.getImage());
//                return;
//            }
//        }
        challengeRepository.save(updatedChallenge);
    }
    public void addChallenge(Challenge newChallenge) {
        challengeRepository.save((newChallenge));
    }

    public void editChallenge(Challenge challenge){
        for(Challenge c : challengeRepository.findAll())
            if(c.getName().equals(challenge.getName())){

                if(challenge.getDescription()!=null)
                    c.setDescription(challenge.getDescription());

                if(challenge.getDuration()!=0)
                    c.setDuration(challenge.getDuration());

                if(challenge.getImage() != null)
                    c.setImage(challenge.getImage());
                challengeRepository.save(c);
            }
    }
}
