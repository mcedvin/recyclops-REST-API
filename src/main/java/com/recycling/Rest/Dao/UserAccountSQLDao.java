package com.recycling.Rest.Dao;

import com.recycling.production.UserAccount;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class UserAccountSQLDao {

    @Autowired
    private com.recycling.DB.repository.UserAccountsRepository UserAccountsRepository;

    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    public Collection<UserAccount> getAllUserAccounts(){
        return UserAccountsRepository.findAll();
//        return null;
    }

    public UserAccount getUserAccountById(int id){
        return UserAccountsRepository.findOne(id);
    }

    public void removeUserAccountById(int id){

    }

    public void updateUserAccount(UserAccount updatedUserAccount){

    }

    public void addUserAccount(UserAccount newUserAccount){
        newUserAccount.setPassword(hashPassword(newUserAccount.getPassword()));
        UserAccountsRepository.save(newUserAccount);
    }
    private void checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            System.out.println("The password matches.");
        else
            System.out.println("The password does not match.");
    }
    @Scheduled(cron = "0 0 * * * *")
    public void checkChallenges(){

    }

}
