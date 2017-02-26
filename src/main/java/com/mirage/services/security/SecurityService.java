package com.mirage.services.security;

import org.springframework.security.core.Authentication;

/**
 * Created by Mirage on 26/02/2017.
 */
public interface SecurityService {

    Boolean isUser(Authentication auth);
    Boolean isAdmin(Authentication auth);
}
