package com.mirage.services;

import com.mirage.domains.User;
import com.mirage.domains.security.Role;
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
public class RoleServiceTest implements ServiceTest {


    private RoleService roleService;
    private UserService userService;
    private CreationUtils creationUtils;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
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
    @Test
    public void save() throws Exception {
        Role role = new Role("ADMIN");
        Role savedRole = roleService.saveOrUpdate(role);

        assert savedRole.getId() != null;
        assert savedRole.getRole() != null;
        assert savedRole.getRole().equals("ADMIN");

    }

    @Override
    @Test
    public void listAll() throws Exception {
        creationUtils.createRole();

        List<Role> roles = (List<Role>) roleService.listAll();

        assert roles.size() > 0;
    }

    @Override
    @Test
    public void getOne() throws Exception {
        Role role = creationUtils.createRole();

        Role gettedRole = roleService.getById(role.getId());

        assert gettedRole.getId() != null;
        assert gettedRole.getId().equals(role.getId());
        assert gettedRole.getRole() != null;
        assert gettedRole.getRole().equals(role.getRole());
    }

    @Override
    @Test
    public void update() throws Exception {

        Role role = creationUtils.createRole();

        role.setRole("USER");

        Role savedRole = roleService.saveOrUpdate(role);

        assert savedRole.getId() != null;
        assert savedRole.getId().equals(role.getId());
        assert savedRole.getVersion().equals(1);
        assert savedRole.getRole() != null;
        assert savedRole.getRole().equals("USER");

    }

    @Override
    @Test
    public void delete() throws Exception {
        Role role = creationUtils.createRole();
        User user = creationUtils.createUser();

        userService.addRole(user.getId(), role.getId());

        List<Role> roles = (List<Role>) roleService.listAll();
        int size = roles.size();

        roleService.delete(role.getId());

        roles = (List<Role>) roleService.listAll();

        assert roles.size() == size - 1;

        User updatedUser = userService.getById(user.getId());

        assert updatedUser.getRoles().size() == 0;
    }

    @Test
    public void addRole() throws Exception{

        User user = creationUtils.createUser();
        Role role = creationUtils.createRole();

        userService.addRole(user.getId(), role.getId());

        User newUser = userService.getById(user.getId());
        Role newRole = roleService.getById(role.getId());

        assert newUser.getVersion().equals(1);
        assert newUser.getRoles().size() == 1;
        assert newUser.getRoles().get(0).getId().equals(role.getId());
        assert newRole.getUsers().size() == 1;
        assert newRole.getUsers().get(0).getId().equals(user.getId());
    }

    @Test
    public void removeRole() throws Exception{
        User user = creationUtils.createUser();
        Role role = creationUtils.createRole();
        Role roleToRemove = creationUtils.createRole();

        userService.addRole(user.getId(), role.getId());    //Version = 1
        userService.addRole(user.getId(), roleToRemove.getId()); //Version = 2

        user = userService.getById(user.getId());

        assert user.getRoles().size() == 2;
        assert user.getVersion().equals(2);

        userService.removeRole(user.getId(), roleToRemove.getId()); //Version = 3

        User newUser = userService.getById(user.getId());

        assert newUser.getVersion().equals(3);
        assert newUser.getRoles().size() == 1;
        assert newUser.getRoles().get(0).getId().equals(role.getId());
    }
}
