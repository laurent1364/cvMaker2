package com.mirage.services;

import com.mirage.domains.Experience;
import com.mirage.domains.Organisation;
import com.mirage.domains.User;
import com.mirage.utils.CreationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Mirage on 05/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrganisationServiceTest {

    private OrganisationService organisationService;
    private UserService userService;
    private CreationUtils creationUtils;

    @Autowired
    public void setOrganisationService(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCreationUtils(CreationUtils creationUtils) {
        this.creationUtils = creationUtils;
    }

    @Test
    public void updateOrganisation() throws Exception{
        User user = creationUtils.createUser();
        Experience experience = userService.addExperience(user.getId(), creationUtils.createExperience());

        String name = "new Organisation name";
        String website = "new website";
        String logoUrl = "logo url .jpg";

        Organisation organisation = experience.getOrganisation();
        organisation.setName(name);
        organisation.setOrganisationLogoUrl(logoUrl);
        organisation.setWebsite(website);

        Organisation updatedOrganisation = organisationService.saveOrUpdate(organisation);

        assert updatedOrganisation.getId() != null;
        assert updatedOrganisation.getId().equals(organisation.getId());
        assert updatedOrganisation.getName().equals(name);
        assert updatedOrganisation.getWebsite().equals(website);
        assert updatedOrganisation.getOrganisationLogoUrl().equals(logoUrl);


    }
}
