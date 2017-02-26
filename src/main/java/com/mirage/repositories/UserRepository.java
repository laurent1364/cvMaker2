package com.mirage.repositories;

import com.mirage.domains.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mirage on 24/02/2017.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
