package com.recycling.Test.Dao;

import com.recycling.DB.repository.UsersRepository;
import com.recycling.recycling.production.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class UserSQLDao {

    @Autowired
    private UsersRepository usersRepository;

    public Collection<User> getAllUsers() {
        return usersRepository.findAll();
    }

    public User getUserById(int id) {
        return null;
    }

    public void removeUserById(int id) {
        for (User s : getAllUsers()) {
            if (s.getUserAccount() != null && s.getUserAccount().getId() == id)
                usersRepository.delete(s);
        }
    }

    public void updateUser(User updatedUser) {
        for (User s : usersRepository.findAll()) {
            if (s.getEmail() == updatedUser.getEmail()) {
                s.setFirstName(updatedUser.getFirstName());
                s.setLastName(updatedUser.getLastName());
                s.setUserAccount(updatedUser.getUserAccount());
                return;
            }
        }
        usersRepository.save(updatedUser);
    }

    public void addUser(User newUser) {
            usersRepository.save((newUser));
    }
}
