package com.nalivayko.pool.services;

import com.nalivayko.pool.model.enums.UserRole;

public interface AccountService {

    public void create(String username, String firstName, String lastName,
                       UserRole userRole, String email, String password);





}
