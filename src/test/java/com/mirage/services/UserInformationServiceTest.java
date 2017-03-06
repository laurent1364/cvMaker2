package com.mirage.services;

import com.mirage.domains.User;
import com.mirage.domains.UserInformation;
import com.mirage.enums.Gender;
import com.mirage.utils.CreationUtils;
import com.mirage.utils.services.ServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by Mirage on 03/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserInformationServiceTest implements ServiceTest {

    private UserInformationService userInformationService;
    private UserService userService;
    private CreationUtils creationUtils;

    @Autowired
    public void setUserInformationService(UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCreationUtils(CreationUtils creationUtils) {
        this.creationUtils = creationUtils;
    }

    @Override
    @Test//Never used
    public void save() throws Exception {

    }

    @Override
    @Test//Never used
    public void listAll() throws Exception {

    }

    @Override
    @Test
    public void getOne() throws Exception {

        User user = creationUtils.createUser();

        UserInformation userInformation = userInformationService.getById(user.getUserInformation().getId());

        assert userInformation.getId() != null;
        assert userInformation.getId().equals(user.getUserInformation().getId());
        assert userInformation.getVersion() != null;
        assert userInformation.getVersion().equals(0);
        assert userInformation.getUser().getId().equals(user.getId());
    }

    @Override
    @Test
    public void update() throws Exception {
        User user = creationUtils.createUser();

        UserInformation userInformation = user.getUserInformation();

        String firstName = "laurent";
        String lastName = "FAIVRE";
        Date dateOfBirth = new Date();
        String phoneNumber = "025789632145";
        String email = "laurent.faivre@gmail.com";
        Gender gender = Gender.MALE;

        userInformation.setFirstName(firstName);
        userInformation.setLastName(lastName);
        userInformation.setGender(gender);
        userInformation.setBirthDate(dateOfBirth);
        userInformation.setPhoneNumber(phoneNumber);
        userInformation.setEmail(email);

        UserInformation savedUserInformation = userInformationService.saveOrUpdate(userInformation);

        assert savedUserInformation.getId() != null;
        assert savedUserInformation.getId().equals(userInformation.getId());
        assert savedUserInformation.getUser().getId().equals(user.getId());
        assert savedUserInformation.getFirstName() != null;
        assert savedUserInformation.getFirstName().equals(firstName);
        assert savedUserInformation.getLastName() != null;
        assert savedUserInformation.getLastName().equals(lastName);
        assert savedUserInformation.getBirthDate() != null;
        assert savedUserInformation.getBirthDate().equals(dateOfBirth);
        assert savedUserInformation.getEmail() != null;
        assert savedUserInformation.getEmail().equals(email);
        assert savedUserInformation.getPhoneNumber() != null;
        assert savedUserInformation.getPhoneNumber().equals(phoneNumber);
        assert savedUserInformation.getGender() != null;
        assert savedUserInformation.getGender().equals(gender);
    }

    @Override
    @Test//Never used
    public void delete() throws Exception {

    }
}
