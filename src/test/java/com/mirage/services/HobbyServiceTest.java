package com.mirage.services;

import com.mirage.domains.Hobby;
import com.mirage.domains.User;
import com.mirage.domains.utils.Logo;
import com.mirage.enums.CssClass;
import com.mirage.utils.CreationUtils;
import com.mirage.utils.services.ServiceToUserTest;
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
public class HobbyServiceTest implements ServiceToUserTest{

    private HobbyService hobbyService;
    private CreationUtils creationUtils;
    private UserService userService;

    @Autowired
    public void setHobbyService(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    @Autowired
    public void setCreationUtils(CreationUtils creationUtils) {
        this.creationUtils = creationUtils;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Test
    public void addElement() throws Exception {

        String name = "Hobby Name";
        CssClass cssClass = CssClass.DANGER;
        Logo logo = creationUtils.createLogo();

        User user = creationUtils.createUser();
        Hobby hobby = new Hobby();
        hobby.setName(name);
        hobby.setCssClass(cssClass);
        hobby.setLogo(logo);

        Hobby createdHobby = userService.addHobby(user.getId(), hobby);

        assert createdHobby.getId() != null;
        assert createdHobby.getVersion().equals(0);
        assert createdHobby.getCssClass().equals(cssClass);
        assert createdHobby.getLogo().getId().equals(logo.getId());
        assert createdHobby.getName().equals(name);
        assert createdHobby.getUser().getId().equals(user.getId());


    }

    @Override
    @Test
    public void removeElement() throws Exception {


        User user = creationUtils.createUser();

        userService.addHobby(user.getId(), new Hobby("name", creationUtils.createLogo(), CssClass.DANGER));
        Hobby hobby = userService.addHobby(user.getId(), new Hobby("name", creationUtils.createLogo(), CssClass.DANGER));

        User gettedUser = userService.getById(user.getId());

        assert gettedUser.getHobbies().size() == 2;

        userService.removeHobby(user.getId(), hobby.getId());

        gettedUser = userService.getById(user.getId());

        assert gettedUser.getHobbies().size() == 1;


    }

    @Override
    @Test
    public void updateElement() throws Exception {

        User user = creationUtils.createUser();

        Hobby hobby = userService.addHobby(user.getId(), new Hobby("name", creationUtils.createLogo(), CssClass.DANGER));

        String name = "updated Name";
        CssClass cssClass = CssClass.PRIMARY;
        Logo logo = creationUtils.createLogo();
        hobby.setLogo(logo);
        hobby.setName(name);
        hobby.setCssClass(cssClass);

        Hobby savedHobby = hobbyService.saveOrUpdate(hobby);

        assert savedHobby.getId() != null;
        assert savedHobby.getId().equals(hobby.getId());
        assert savedHobby.getVersion().equals(1);
        assert savedHobby.getName().equals(name);
        assert savedHobby.getLogo().getId().equals(logo.getId());
        assert savedHobby.getCssClass().equals(cssClass);
        assert savedHobby.getUser().getId().equals(user.getId());

    }
}
