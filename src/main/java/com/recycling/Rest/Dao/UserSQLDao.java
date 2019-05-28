package com.recycling.Rest.Dao;

import com.recycling.DB.repository.UsersRepository;
import com.recycling.production.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class UserSQLDao {

    @Autowired
    private UsersRepository usersRepository;

    public Collection<User> getAllUsers() {
        return usersRepository.findAll();
    }

    public User getUserByEmail(String mail) {
        for (User s : usersRepository.findAll()) {
            if (s.getEmail().equals(mail))
                return s;
        }
        return null;
    }

//    public User getAndAuthenticateUser(String[] login) {
//        for (User s : usersRepository.findAll()) {
//            if (s.getEmail().equals(login[0])) {
//                return (BCrypt.checkpw(login[1], s.getUserAccount().getPassword())) ? s : null;
//            }
//        }
//        return null;
//    }
    public User getAndAuthenticateUser(String credentials) {
        String[] login = credentials.split("\\+");
        for (User s : usersRepository.findAll()) {
            if (s.getEmail().equals(login[0])) {
                return (BCrypt.checkpw(login[1], s.getUserAccount().getPassword())) ? s : null;
            }
        }
        return null;
    }

    public void deleteUserByMail(String mail) {
        for (User s : getAllUsers()) {
            if (s.getEmail() != null && s.getEmail().equals(mail))
                usersRepository.delete(s);
        }
    }

    public void removeUserById(int id) {
        for (User s : getAllUsers()) {
            if (s.getUserAccount() != null && s.getUserAccount().getId() == id)
                usersRepository.delete(s);
        }
    }

    public void editUser(User editedUser) {
        for (User s : usersRepository.findAll())
            if (s.getEmail().equals(editedUser.getEmail())) {
                if (editedUser.getFirstName() != null)
                    s.setFirstName(editedUser.getFirstName());
                if (editedUser.getLastName() != null)
                    s.setLastName(editedUser.getLastName());
                if (editedUser.getUserAccount() != null)
                    s.setUserAccount(editedUser.getUserAccount());
                usersRepository.save(s);
            }
    }

    public void updateUser(User updatedUser) {
//        for (User s : usersRepository.findAll()) {
//            if (s.getEmail().equals(updatedUser.getEmail())) {
//                s.setFirstName(updatedUser.getFirstName());
//                s.setLastName(updatedUser.getLastName());
//                s.setUserAccount(updatedUser.getUserAccount());
//                return;
//            }
//        }
        usersRepository.save(updatedUser);
    }

    public void addUser(User newUser) {
        usersRepository.save((newUser));
    }
}