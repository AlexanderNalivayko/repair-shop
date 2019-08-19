package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.User;
import com.nalivayko.pool.repair_shop.persistance.repositories.CustomizedUserCrudRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@NoArgsConstructor
public class DefaultUserService implements UserService {

    @Autowired
    private CustomizedUserCrudRepository userRepo;

    @Override
    @Transactional
    public void create(String username, String pass,
                       String firstName, String lastName, String email, String phone) {
        userRepo.save(User.builder()
                .username(username)
                .password(pass)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .build());
    }

    @Override
    @Transactional
    public User validate(String username, String pass) {
        User user = userRepo.findByUsername(username);
        if (user == null || !user.getPassword().equals(pass)) {
            return null;
        }
        return user;
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
