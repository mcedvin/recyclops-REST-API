package com.recycling.Rest.Dao;

import com.recycling.DB.repository.ChallengeAcceptedRepository;
import com.recycling.DB.repository.ChallengeRepository;
import com.recycling.production.Challenge;
import com.recycling.production.ChallengeAccepted;
import com.recycling.production.UserAccount;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public class UserAccountSQLDao {

    @Autowired
    private com.recycling.DB.repository.UserAccountsRepository UserAccountsRepository;
    @Autowired
    private ChallengeAcceptedRepository challengeAcceptedRepository;
    @Autowired
    private ChallengeRepository challengeRepository;

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    private void checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            System.out.println("The password matches.");
        else
            System.out.println("The password does not match.");
    }

    public Collection<UserAccount> getAllUserAccounts() {
        return UserAccountsRepository.findAll();
    }

    public UserAccount getUserAccountById(int id) {
        return UserAccountsRepository.findOne(id);
    }

    public void removeUserAccountById(int id) {
        UserAccountsRepository.delete(UserAccountsRepository.findOne(id));
    }

    public void updateUserAccount(UserAccount updatedUserAccount) {

    }

    public void addUserAccount(UserAccount newUserAccount) {
        newUserAccount.setPassword(hashPassword(newUserAccount.getPassword()));
        UserAccountsRepository.save(newUserAccount);
    }

    private Challenge findChallenge(String name) {
        for (Challenge c : challengeRepository.findAll())
            if (c.getName().equalsIgnoreCase(name))
                return c;
        return null;
    }

    public void completeChallenge(String[] info) {
        UserAccount ua = UserAccountsRepository.findOne(Integer.parseInt(info[0]));
        Challenge c = findChallenge(info[1]);
        for (ChallengeAccepted ca : ua.getCurrentChallenges())
            if (c != null && c.getName().equalsIgnoreCase(ca.getChallenge().getName())) {
                ua.completeChallenge(ca);
                UserAccountsRepository.save(ua);
                return;
            }
    }

    public void acceptChallenge(String[] info) {

        UserAccount ua = UserAccountsRepository.findOne(Integer.parseInt(info[0]));
        Challenge c = findChallenge(info[1]);
        if (!ua.hasAcceptedChallenge(c)) {
            ChallengeAccepted ca = new ChallengeAccepted(c, new Date());
            challengeAcceptedRepository.save(ca);
            ua.acceptChallenge(ca);
            UserAccountsRepository.save(ua);
        }
    }


    //    @Scheduled(cron = "*/10 * * * * *")
    @Scheduled(cron = "0 0 * * * *")
    public void checkChallenges() {
        Date d = new Date();
        for (UserAccount ua : UserAccountsRepository.findAll()) {
            if (ua.verifyChallenges())
                UserAccountsRepository.save(ua);
        }

    }

}
