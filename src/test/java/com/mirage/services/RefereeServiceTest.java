package com.mirage.services;

import com.mirage.domains.Experience;
import com.mirage.domains.Organisation;
import com.mirage.domains.Referee;
import com.mirage.domains.User;
import com.mirage.enums.Gender;
import com.mirage.utils.CreationUtils;
import com.mirage.utils.services.ServiceToUserTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Mirage on 05/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RefereeServiceTest implements ServiceToUserTest{

    private UserService userService;
    private RefereeService refereeService;
    private CreationUtils creationUtils;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRefereeService(RefereeService refereeService) {
        this.refereeService = refereeService;
    }

    @Autowired
    public void setCreationUtils(CreationUtils creationUtils) {
        this.creationUtils = creationUtils;
    }

    @Override
    @Test
    public void addElement() throws Exception {
        User user = creationUtils.createUser();
        Experience experience = userService.addExperience(user.getId(), creationUtils.createExperience());

        String firstName = "lolo";
        String lastName = "lolo";
         Gender gender = Gender.MALE;
        String function = "CEO";
        String email = "lolo@lolo.molo";
        String phoneNumber = "266551313135135";


        Organisation organisation = experience.getOrganisation();

        Referee referee = new Referee();
        referee.setFirstName(firstName);
        referee.setLastName(lastName);
        referee.setGender(gender);
        referee.setFunction(function);
        referee.setEmail(email);
        referee.setPhoneNumber(phoneNumber);

        Referee savedReferee = userService.addReferee(user.getId(), referee, organisation.getId());

        assert savedReferee.getId() != null;
        assert savedReferee.getFirstName().equals(firstName);
        assert savedReferee.getLastName().equals(lastName);
        assert savedReferee.getFunction().equals(function);
        assert savedReferee.getGender().equals(gender);
        assert savedReferee.getEmail().equals(email);
        assert savedReferee.getPhoneNumber().equals(phoneNumber);
        assert savedReferee.getOrganisation().getId().equals(organisation.getId());


    }

    @Override
    @Test
    public void removeElement() throws Exception {

        User user = creationUtils.createUser();
        Experience experience = userService.addExperience(user.getId(), creationUtils.createExperience());
        Organisation organisation = experience.getOrganisation();

        userService.addReferee(user.getId(),creationUtils.createReferee(), organisation.getId());

        Referee referee = userService.addReferee(user.getId(),creationUtils.createReferee(), organisation.getId());

        User gettedUser = userService.getById(user.getId());

        assert gettedUser.getReferees().size() == 2;

        userService.removeReferee(user.getId(), referee.getId());

        gettedUser = userService.getById(user.getId());

        assert gettedUser.getReferees().size() == 1;
    }

    @Override
    @Test
    public void updateElement() throws Exception {

        User user = creationUtils.createUser();
        Experience experience = userService.addExperience(user.getId(), creationUtils.createExperience());
        Organisation organisation = experience.getOrganisation();

        Referee referee = userService.addReferee(user.getId(),creationUtils.createReferee(), organisation.getId());

        String firstName = "lala";
        String lastName = "lala";
        Gender gender = Gender.FEMALE;
        String function = "employee";
        String email = "lala@kaka.com";
        String phoneNumber = "54465125846513";

        referee.setFirstName(firstName);
        referee.setLastName(lastName);
        referee.setGender(gender);
        referee.setFunction(function);
        referee.setEmail(email);
        referee.setPhoneNumber(phoneNumber);

        Referee updatedReferee = refereeService.saveOrUpdate(referee);

        assert updatedReferee.getId() != null;
        assert updatedReferee.getId().equals(referee.getId());
        assert updatedReferee.getVersion().equals(1);
        assert updatedReferee.getFirstName().equals(firstName);
        assert updatedReferee.getLastName().equals(lastName);
        assert updatedReferee.getFunction().equals(function);
        assert updatedReferee.getGender().equals(gender);
        assert updatedReferee.getEmail().equals(email);
        assert updatedReferee.getPhoneNumber().equals(phoneNumber);


    }
}
