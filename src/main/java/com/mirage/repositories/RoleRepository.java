package com.mirage.repositories;

import com.mirage.domains.security.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mirage on 24/02/2017.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByRole(String role);
}
