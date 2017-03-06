package com.mirage.services;

import com.mirage.domains.Experience;
import com.mirage.domains.Organisation;
import com.mirage.domains.User;
import com.mirage.domains.utils.Address;
import com.mirage.domains.utils.Logo;
import com.mirage.enums.ExperienceType;
import com.mirage.utils.CreationUtils;
import com.mirage.utils.services.ServiceToUserTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by Mirage on 05/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ExperienceServiceTest implements ServiceToUserTest {

    private UserService userService;
    private ExperienceService experienceService;
    private CreationUtils creationUtils;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setExperienceService(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @Autowired
    public void setCreationUtils(CreationUtils creationUtils) {
        this.creationUtils = creationUtils;
    }

    @Override
    @Test
    public void addElement() throws Exception {

        User user = creationUtils.createUser();
        String title = "title";
        String description = "this is a description";
        Logo logo = creationUtils.createLogo();
        ExperienceType experienceType = ExperienceType.EXPERIENCE;
        Date startDate = new Date();
        Date endDate = new Date();

        String streetAddress = "6 rue Leon Gozlan";
        String addressComplement = "Appt601";
        String city = "Marseille";
        String state = "PACA";
        Integer zipCode = 13003;
        String country = "FRANCE";

        Address address = new Address(streetAddress,addressComplement,city,state,zipCode,country);

        String name = "company";
        String website = "website";
        String organisationLogoUrl = "logoUrl";

        Organisation organisation = new Organisation(name,website,organisationLogoUrl,address);


        Experience experience = new Experience();
        experience.setTitle(title);
        experience.setDescription(description);
        experience.setLogo(logo);
        experience.setExperienceType(experienceType);
        experience.setStartDate(startDate);
        experience.setEndDate(endDate);
        experience.setOrganisation(organisation);

        Experience savedExperience = userService.addExperience(user.getId(), experience);

        assert savedExperience.getId() != null;
        assert savedExperience.getExperienceType().equals(experienceType);
        assert savedExperience.getDescription().equals(description);
        assert savedExperience.getLogo().getId().equals(logo.getId());
        assert savedExperience.getStartDate().equals(startDate);
        assert savedExperience.getEndDate().equals(endDate);
        assert savedExperience.getOrganisation().getId() != null;
        assert savedExperience.getOrganisation().getName().equals(name);
        assert savedExperience.getOrganisation().getOrganisationLogoUrl().equals(organisationLogoUrl);
        assert savedExperience.getOrganisation().getWebsite().equals(website);
        assert savedExperience.getOrganisation().getAddress().getId() != null;
        assert savedExperience.getOrganisation().getAddress().getStreetAddress().equals(streetAddress);
        assert savedExperience.getOrganisation().getAddress().getAddressComplement().equals(addressComplement);
        assert savedExperience.getOrganisation().getAddress().getState().equals(state);
        assert savedExperience.getOrganisation().getAddress().getCity().equals(city);
        assert savedExperience.getOrganisation().getAddress().getCountry().equals(country);
        assert savedExperience.getOrganisation().getAddress().getZipCode().equals(zipCode);
    }

    @Override
    @Test
    public void removeElement() throws Exception {
        User user = creationUtils.createUser();

        userService.addExperience(user.getId(), creationUtils.createExperience());
        Experience experience = userService.addExperience(user.getId(), creationUtils.createExperience());

        User gettedUser = userService.getById(user.getId());

        assert gettedUser.getExperiences().size() == 2;

        userService.removeExperience(user.getId(), experience.getId());

        gettedUser = userService.getById(user.getId());

        assert gettedUser.getExperiences().size() == 1;

    }

    @Override
    @Test
    public void updateElement() throws Exception {

        User user = creationUtils.createUser();

        Experience experience = userService.addExperience(user.getId(), creationUtils.createExperience());

        String title = "title updates";
        String description = "this is a description updated";
        Logo logo = creationUtils.createLogo();
        Date startDate = new Date();
        Date endDate = new Date();

        experience.setTitle(title);
        experience.setDescription(description);
        experience.setLogo(logo);
        experience.setStartDate(startDate);
        experience.setEndDate(endDate);

        Experience updatedExperience = experienceService.saveOrUpdate(experience);


        assert updatedExperience.getId().equals(experience.getId());
        assert updatedExperience.getVersion().equals(1);
        assert updatedExperience.getDescription().equals(description);
        assert updatedExperience.getLogo().getId().equals(logo.getId());
        assert updatedExperience.getStartDate().equals(startDate);
        assert updatedExperience.getEndDate().equals(endDate);
        assert updatedExperience.getOrganisation().getId().equals(experience.getOrganisation().getId());

    }
}
