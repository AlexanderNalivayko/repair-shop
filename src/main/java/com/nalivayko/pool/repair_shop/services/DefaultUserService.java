package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.User;
import com.nalivayko.pool.repair_shop.model.enums.UserRole;
import com.nalivayko.pool.repair_shop.persistance.repositories.CustomizedUserCrudRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class DefaultUserService implements UserService {

    @Autowired
    private CustomizedUserCrudRepository userRepo;

    @Override
    public User create(User user) {
        user.setRole(UserRole.CUSTOMER);
        return userRepo.save(user);
    }

    @Override
    public User validate(String username, String pass) {
        User user = userRepo.findByUsername(username);
        if (user == null || !user.getPassword().equals(pass)) {
            return null;
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
