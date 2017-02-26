package com.mirage.services.security.Implements;

import com.mirage.domains.security.Role;
import com.mirage.repositories.RoleRepository;
import com.mirage.services.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by Mirage on 26/02/2017.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Boolean isUser(Authentication auth) {
        if(auth.isAuthenticated()){
            return auth.getAuthorities().contains(new SimpleGrantedAuthority("USER"));
        }
        return false;
    }

    @Override
    public Boolean isAdmin(Authentication auth) {
        if(auth.isAuthenticated()){
            return auth.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
        }
        return false;
    }
}
