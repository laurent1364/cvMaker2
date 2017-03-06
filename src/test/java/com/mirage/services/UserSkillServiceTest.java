package com.mirage.services;

import com.mirage.domains.Domain;
import com.mirage.domains.User;
import com.mirage.domains.UserSkill;
import com.mirage.utils.CreationUtils;
import com.mirage.utils.services.ServiceToUserTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Mirage on 06/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserSkillServiceTest implements ServiceToUserTest {

    private UserSkillService userSkillService;
    private CreationUtils creationUtils;
    private UserService userService;

    @Autowired
    public void setUserSkillService(UserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }

    @Autowired
    public void setCreationUtils(CreationUtils creationUtils) {
        this.creationUtils = creationUtils;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void saveElement() throws Exception {
        User user = creationUtils.createUser();
        Domain domain = creationUtils.createDomainWithSkills();

        UserSkill userSkill = new UserSkill();
        userSkill.setUser(user);
        userSkill.setSkill(domain.getSkills().get(0));
        userSkill.setMark(3);

        UserSkill savedUserSkill = userSkillService.saveOrUpdate(userSkill);

        assert savedUserSkill.getId() != null;
        assert savedUserSkill.getUser().getId().equals(user.getId());
        assert savedUserSkill.getSkill().getId().equals(domain.getSkills().get(0).getId());

        User gettedUser = userService.getById(user.getId());

        assert gettedUser.getUserSkills().size() == 1;

    }

    @Override
    @Test
    public void addElement() throws Exception {
        User user = creationUtils.createUser();
        Domain domain = creationUtils.createDomainWithSkills();


        UserSkill savedUserSkill = userSkillService.addUserSkill(user.getId(), domain.getSkills().get(0).getId(), 9);

        assert savedUserSkill.getId() != null;
        assert savedUserSkill.getUser().getId().equals(user.getId());
        assert savedUserSkill.getSkill().getId().equals(domain.getSkills().get(0).getId());

        User gettedUser = userService.getById(user.getId());

        assert gettedUser.getUserSkills().size() == 1;

    }

    @Override
    @Test
    public void removeElement() throws Exception {
        User user = creationUtils.createUser();
        Domain domain = creationUtils.createDomainWithSkills();


        userSkillService.addUserSkill(user.getId(), domain.getSkills().get(1).getId(), 9);
        UserSkill savedUserSkill = userSkillService.addUserSkill(user.getId(), domain.getSkills().get(0).getId(), 9);

        User gettedUser = userService.getById(user.getId());

        assert gettedUser.getUserSkills().size() == 2;

        userService.removeUserSkill(user.getId(), savedUserSkill.getId());

        gettedUser = userService.getById(user.getId());

        assert gettedUser.getUserSkills().size() == 1;


    }

    @Override
    @Test
    public void updateElement() throws Exception {

        User user = creationUtils.createUser();
        Domain domain = creationUtils.createDomainWithSkills();

        UserSkill savedUserSkill = userSkillService.addUserSkill(user.getId(), domain.getSkills().get(0).getId(), 9);

        savedUserSkill.setMark(8);

        UserSkill updatedUserSkill = userSkillService.saveOrUpdate(savedUserSkill);

        assert updatedUserSkill.getId() != null;
        assert updatedUserSkill.getId().equals(savedUserSkill.getId());
        assert updatedUserSkill.getMark().equals(8);

    }
}
