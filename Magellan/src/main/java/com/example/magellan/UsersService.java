package com.example.magellan;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class UsersService {

    public String encryptPassword(String password){
        return DigestUtils.md5Hex(password).toUpperCase();
    }

    @PersistenceContext
    private EntityManager entityManager;

    public User getUser(String username){
        return entityManager.find(User.class, username);
    }

    public User insertUserIntoDB(User user){
        entityManager.persist(user);
        return user;
    }
}
