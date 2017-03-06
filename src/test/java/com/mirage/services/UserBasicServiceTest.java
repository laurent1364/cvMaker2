package com.mirage.services;

import com.mirage.domains.User;
import com.mirage.domains.UserInformation;
import com.mirage.enums.Gender;
import com.mirage.utils.services.ServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Mirage on 03/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserBasicServiceTest implements ServiceTest {

    private UserService userService;
    private UserInformationService userInformationService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserInformationService(UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

    @Override
    @Test
    public void save() throws Exception {
        String username = "username";
        String password = "password";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getEncryptedPassword() != null;
        assert savedUser.getUserInformation().getId() != null;
        assert savedUser.getUsername().equalsIgnoreCase(username);
        assert savedUser.getUserInformation().getUser().getId().equals(user.getId());
    }

    @Override
    @Test
    public void listAll() throws Exception {

        String username = "username";
        String password = "password";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.saveOrUpdate(user);

        List<User> users = (List<User>) userService.listAll();

        assert users.size() > 0;
    }

    @Override
    @Test
    public void getOne() throws Exception {
        String username = "username";
        String password = "password";

        User user = userService.saveOrUpdate(new User(username, password));

        User gettedUser = userService.getById(user.getId());

        assert gettedUser.getId() != null;
        assert gettedUser.getId().equals(user.getId());
        assert gettedUser.getUsername() != null;
        assert gettedUser.getUsername().equals(username);

    }

    @Override
    @Test
    public void update() throws Exception {

        String username = "username";
        String password = "password";

        User user = userService.saveOrUpdate(new User(username, password));

        String encryptedPassword = user.getEncryptedPassword();

        username = "username Updated";
        password = "drowssap";

        user.setPassword(password);
        user.setUsername(username);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getId().equals(user.getId());
        assert savedUser.getUserInformation().getId().equals(user.getUserInformation().getId());
        assert savedUser.getEncryptedPassword() != null;
        assert !savedUser.getEncryptedPassword().equals(encryptedPassword);
        assert savedUser.getUsername().equals(username);

    }

    @Override
    @Test
    public void delete() throws Exception {

        String username = "username";
        String password = "password";

        User user = userService.saveOrUpdate(new User(username, password));

        Integer userInfoId = user.getUserInformation().getId();

        List<User> users = (List<User>) userService.listAll();

        int size = users.size();

        userService.delete(user.getId());

        users = (List<User>) userService.listAll();

        assert users.size() == size - 1;

        UserInformation userInformation = new UserInformation("lolo", "lolo", "12346789", "lolo@lolo.colo");

        userInformation = userInformationService.getById(userInfoId);

        assert userInformation == null;


    }
}
