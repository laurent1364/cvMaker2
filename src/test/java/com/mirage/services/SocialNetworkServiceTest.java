package com.mirage.services;

import com.mirage.domains.SocialMedia;
import com.mirage.domains.SocialNetwork;
import com.mirage.domains.User;
import com.mirage.domains.UserInformation;
import com.mirage.utils.CreationUtils;
import com.mirage.utils.services.ServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Mirage on 04/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SocialNetworkServiceTest implements ServiceTest {

    private SocialNetworkService socialNetworkService;
    private UserInformationService userInformationService;
    private SocialMediaService socialMediaService;
    private CreationUtils creationUtils;

    @Autowired
    public void setSocialNetworkService(SocialNetworkService socialNetworkService) {
        this.socialNetworkService = socialNetworkService;
    }

    @Autowired
    public void setCreationUtils(CreationUtils creationUtils) {
        this.creationUtils = creationUtils;
    }

    @Autowired
    public void setSocialMediaService(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    @Autowired
    public void setUserInformationService(UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

    @Override
    @Test//The save method is done by adding a social network to a user information
    public void save() throws Exception {
        SocialMedia socialMedia = creationUtils.createSocialMedia();
        User user = creationUtils.createUser();
        UserInformation userInformation = user.getUserInformation();
        String address = "address social media";

        SocialNetwork socialNetwork = userInformationService.addSocialNetwork(userInformation.getId(), address, socialMedia.getId());

        assert socialNetwork.getId() != null;
        assert socialNetwork.getLinkAddress() != null;
        assert socialNetwork.getLinkAddress().equals(address);
        assert socialNetwork.getSocialMedia().getId().equals(socialMedia.getId());
        assert socialNetwork.getUserInformation().getId().equals(userInformation.getId());
    }

    @Override
    @Test//Never Used
    public void listAll() throws Exception {

    }

    @Override
    @Test
    public void getOne() throws Exception {
        SocialMedia socialMedia = creationUtils.createSocialMedia();
        User user = creationUtils.createUser();
        UserInformation userInformation = user.getUserInformation();
        String address = "address social media";

        SocialNetwork socialNetwork = userInformationService.addSocialNetwork(userInformation.getId(), address, socialMedia.getId());

        SocialNetwork gettedSocialNetwork = socialNetworkService.getById(socialNetwork.getId());

        assert gettedSocialNetwork.getId() != null;
        assert gettedSocialNetwork.getId().equals(socialNetwork.getId());
        assert gettedSocialNetwork.getLinkAddress() != null;
        assert gettedSocialNetwork.getLinkAddress().equals(socialNetwork.getLinkAddress());
        assert gettedSocialNetwork.getSocialMedia().getId().equals(socialMedia.getId());
        assert gettedSocialNetwork.getUserInformation().getId().equals(userInformation.getId());

    }

    @Override
    @Test
    public void update() throws Exception {

        SocialMedia socialMedia = creationUtils.createSocialMedia();
        User user = creationUtils.createUser();
        UserInformation userInformation = user.getUserInformation();
        String address = "address social media";

        SocialNetwork socialNetwork = userInformationService.addSocialNetwork(userInformation.getId(), address, socialMedia.getId());

        address = "updated address social";
        socialNetwork.setLinkAddress(address);

        SocialNetwork updatedSocialNetwork = socialNetworkService.saveOrUpdate(socialNetwork);

        assert updatedSocialNetwork.getId() != null;
        assert updatedSocialNetwork.getId().equals(socialNetwork.getId());
        assert updatedSocialNetwork.getLinkAddress() != null;
        assert updatedSocialNetwork.getLinkAddress().equals(address);
    }

    @Override
    @Test
    public void delete() throws Exception {

        SocialMedia socialMedia = creationUtils.createSocialMedia();
        User user = creationUtils.createUser();
        UserInformation userInformation = user.getUserInformation();
        String address = "address social media";

        userInformationService.addSocialNetwork(userInformation.getId(), address, socialMedia.getId());
        SocialNetwork socialNetwork = userInformationService.addSocialNetwork(userInformation.getId(), address, socialMedia.getId());

        userInformationService.removeSocialNetwork(userInformation.getId(), socialNetwork.getId());


        UserInformation newUserInformation = userInformationService.getById(userInformation.getId());

        assert newUserInformation.getSocialNetworks().size() == 1;
    }


}
