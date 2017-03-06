package com.mirage.utils;

import com.mirage.domains.*;
import com.mirage.domains.security.Role;
import com.mirage.domains.utils.Logo;

/**
 * Created by Mirage on 03/03/2017.
 */
public interface CreationUtils {

    Logo createLogo();
    Domain createDomain();
    Domain createDomainWithSkills();
    User createUser();
    Role createRole();
    SocialMedia createSocialMedia();
    Experience createExperience();
    Referee createReferee();

    User createAllStuff();

    Resume createResume();
}
