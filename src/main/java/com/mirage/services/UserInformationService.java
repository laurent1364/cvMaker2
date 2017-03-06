package com.mirage.services;

import com.mirage.domains.SocialMedia;
import com.mirage.domains.SocialNetwork;
import com.mirage.domains.UserInformation;

/**
 * Created by Mirage on 24/02/2017.
 */
public interface UserInformationService extends CRUDService<UserInformation> {

    SocialNetwork addSocialNetwork(Integer userInformationId, String socialLinkAddress, Integer socialMediaId);

    void removeSocialNetwork(Integer userInformationId, Integer socialNetworkId);
}
