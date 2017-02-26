package com.mirage.services;

import com.mirage.domains.User;

/**
 * Created by Mirage on 24/02/2017.
 */
public interface UserService extends CRUDService<User> {

    User findByUsername(String username);
}
