package com.recycling.Rest.Service;

import com.recycling.Rest.Dao.UserSQLDao;
import com.recycling.production.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    private UserSQLDao userDao;

    public Collection<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    public User getUserByEmail(String mail){
        return userDao.getUserByEmail(mail);
    }
    public User getAndAuthenticateUser(String credentials){
        return userDao.getAndAuthenticateUser(credentials);
    }
    public void editUser(User editedUser){userDao.editUser(editedUser);}
    public void deleteUserByMail(String mail){
        userDao.deleteUserByMail(mail);
    }
    public void updateUser(User user){
        userDao.updateUser(user);
    }
    public void addUser(User user){
        userDao.addUser(user);
    }
}
