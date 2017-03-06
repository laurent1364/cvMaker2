package com.mirage.services.reposervices;

import com.mirage.domains.*;
import com.mirage.repositories.UserInformationRepository;
import com.mirage.services.SocialMediaService;
import com.mirage.services.SocialNetworkService;
import com.mirage.services.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Mirage on 24/02/2017.
 */
@Service
public class UserInformationServiceRepoImpl implements UserInformationService {

    private UserInformationRepository userInformationRepository;
    private SocialNetworkService socialNetworkService;
    private SocialMediaService socialMediaService;

    @Autowired
    public void setUserInformationRepository(UserInformationRepository userInformationRepository) {
        this.userInformationRepository = userInformationRepository;
    }

    @Autowired
    public void setSocialNetworkService(SocialNetworkService socialNetworkService) {
        this.socialNetworkService = socialNetworkService;
    }

    @Autowired
    public void setSocialMediaService(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public UserInformation getById(Integer id) {
        return userInformationRepository.findOne(id);
    }

    @Override
    public UserInformation saveOrUpdate(UserInformation domainObject) {
        return userInformationRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        userInformationRepository.delete(id);
    }

    @Override
    public SocialNetwork addSocialNetwork(Integer userInformationId, String socialLinkAddress, Integer socialMediaId) {

        UserInformation userInformation = this.getById(userInformationId);
        SocialMedia socialMedia = socialMediaService.getById(socialMediaId);
        return socialNetworkService.saveOrUpdate(new SocialNetwork(socialLinkAddress, userInformation, socialMedia));
    }

    @Override
    public void removeSocialNetwork(Integer userInformationId, Integer socialNetworkId) {

        UserInformation userInformation = this.getById(userInformationId);
        Set<SocialNetwork> newSocialNetworks = new HashSet<>();

        for (SocialNetwork sn : userInformation.getSocialNetworks()){
            if(!sn.getId().equals(socialNetworkId)){
                newSocialNetworks.add(sn);
            }
        }

        userInformation.setSocialNetworks(newSocialNetworks);
        this.saveOrUpdate(userInformation);

    }
}
