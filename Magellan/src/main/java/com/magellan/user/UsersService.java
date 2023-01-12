package com.magellan.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.apache.commons.codec.digest.DigestUtils;

@ApplicationScoped
public class UsersService {

    public Boolean isUserLoggedIn(User user){
        for(User tempUser : User.loggedInUsers){
            if(user.getUsername().equals(tempUser.getUsername())){
                return true;
            }
        }
        return false;
    }

    public int getLoggedInUserInstanceIndexInArray(User user){
        int index = -1;
        for(User tempUser : User.loggedInUsers){
            index++;
            if(user.getUsername().equals(tempUser.getUsername())){
                return index;
            }
        }
        return index;
    }

    public Boolean logUserIn(User user){
        if(!isUserLoggedIn(user)){
            User.loggedInUsers.add(user);
            return true;
        }
        else{
            return false;
        }
    }

    public String encryptPassword(String password){
        return DigestUtils.md5Hex(password).toUpperCase();
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public User getUserFromDatabase(String username){
        return entityManager.find(User.class, username);
    }

    @Transactional
    public User insertUserIntoDatabase(User user){
        entityManager.persist(user);
        return user;
    }
}
