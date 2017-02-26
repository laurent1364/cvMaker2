package com.mirage.bootstrap;

import com.mirage.domains.Address;
import com.mirage.domains.User;
import com.mirage.domains.UserInformation;
import com.mirage.domains.security.Role;
import com.mirage.services.RoleService;
import com.mirage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mirage on 24/02/2017.
 */
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadUsers();
        loadRoles();
        assignUsersDefaultRole();
        assignUsersAdminRole();


    }

    private void loadUsers() {
        User user = new User();
        user.setUsername("lfaivre");
        user.setPassword("password");

        UserInformation userInformation = new UserInformation();
        userInformation.setEmail("laurent.faivre.64@gmail.com");
        userInformation.setFirstName("Laurent");
        userInformation.setLastName("FAIVRE");
        userInformation.setMale(true);
        userInformation.setPhoneNumber("021-028-62167");

        Address address = new Address();
        address.setStreetAddress("20 Hay Street");
        address.setAddressComplement("Oriental Bay");
        address.setCity("WELLINGTON");
        address.setState("Wellington");
        address.setCountry("New Zealand");
        address.setZipCode(6011);

        userInformation.setAddress(address);

        user.setUserInformation(userInformation);

        userService.saveOrUpdate(user);

        User user1 = new User();
        user1.setUsername("lsouby");
        user1.setPassword("password");

        UserInformation userInformation1 = new UserInformation();
        userInformation1.setEmail("lea.souby@hotmail.fr");
        userInformation1.setFirstName("Léa");
        userInformation1.setLastName("SOUBY");
        userInformation1.setMale(false);
        userInformation1.setPhoneNumber("021-028-62167");

        Address address1 = new Address();
        address1.setStreetAddress("20 Hay Street");
        address1.setAddressComplement("Oriental Bay");
        address1.setCity("WELLINGTON");
        address1.setState("Wellington");
        address1.setCountry("New Zealand");
        address1.setZipCode(6011);

        userInformation1.setAddress(address1);

        user1.setUserInformation(userInformation1);

        userService.saveOrUpdate(user1);
    }

    private void loadRoles() {

        Role role = new Role();
        role.setRole("USER");
        roleService.saveOrUpdate(role);

        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);

    }

    private void assignUsersDefaultRole() {

        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if(role.getRole().equalsIgnoreCase("USER")){
                users.forEach(user -> {
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            }
        });
    }

    private void assignUsersAdminRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("lfaivre")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }
}
