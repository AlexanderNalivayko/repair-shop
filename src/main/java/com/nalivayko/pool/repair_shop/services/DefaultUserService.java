package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.User;
import com.nalivayko.pool.repair_shop.model.enums.UserRole;
import com.nalivayko.pool.repair_shop.persistance.repositories.CustomizedFeedBackCrudRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides methods to work with User (create, validate, find ...)
 */
@Service
@NoArgsConstructor
public class DefaultUserService implements UserService {

    @Autowired
    private CustomizedFeedBackCrudRepository repository;

    /**
     * Create and save user
     * @param username - users username
     * @param pass - users pass
     * @param firstName - users firstname
     * @param lastName - users lastname
     * @param email - users email
     * @param phone - users phone
     */
    @Override
    @Transactional
    public void create(String username, String pass,
                       String firstName, String lastName, String email, String phone) {
            User user = new User(null, UserRole.CUSTOMER, username,
                    pass, firstName, lastName, email, phone, null, null);
            repository.save(user);
    }

    /**
     * Check if user exists and if it`s password equal to parameter pass
     * @param username - user you want to check
     * @param pass - pass of the user
     * @return User with username that was passed in parameters.
     *         or null - if user not exists of if password is not correct
     */
    @Override
    @Transactional
    public User validate(String username, String pass) {
            User user = repository.findByUsername(username);
            if (user == null || !user.getPassword().equals(pass)) {
                return null;
            }
            return user;
    }

    /**
     * Find user by it`s username
     * @param username - username of user you want to find
     * @return user with passed username
     */
    @Override
    @Transactional
    public User getUserByUsername(String username) {
            return repository.findByUsername(username);
    }
}
