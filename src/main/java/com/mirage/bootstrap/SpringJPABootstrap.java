package com.mirage.bootstrap;

import com.mirage.domains.*;
import com.mirage.domains.utils.Address;
import com.mirage.domains.security.Role;
import com.mirage.domains.utils.Logo;
import com.mirage.enums.CssClass;
import com.mirage.enums.ExperienceType;
import com.mirage.enums.Gender;
import com.mirage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mirage on 24/02/2017.
 */
@Component
@Profile({"dev"})
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserService userService;
    private UserInformationService userInformationService;
    private RoleService roleService;
    private LogoService logoService;
    private SocialMediaService socialMediaService;
    private SkillService skillService;
    private DomainService domainService;
    private UserSkillService userSkillService;
    private HobbyService hobbyService;
    private ExperienceService experienceService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserInformationService(UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setLogoService(LogoService logoService) {
        this.logoService = logoService;
    }

    @Autowired
    public void setSocialMediaService(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    @Autowired
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    @Autowired
    public void setDomainService(DomainService domainService) {
        this.domainService = domainService;
    }

    @Autowired
    public void setUserSkillService(UserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }

    @Autowired
    public void setHobbyService(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    @Autowired
    public void setExperienceService(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    /******************************* CREATION OF BOOTSTRAP *********************************/

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        loadLogos();
        loadSocialMedias();
        loadSkillsAndDomain();
        loadUsers();
        loadRoles();
        assignUsersDefaultRole();
        assignUsersAdminRole();
        addSocialNetworks();
        addExperiencesToUser();
        addAchievementToExperience(); //TODO
        addSkillToUser();
        addHobbyToUser();
        addRefereeToUser(); //TODO
        createResume(); //TODO


    }

    /******************************* METHODS****************************************/

    private void createResume() {

    }

    private void addRefereeToUser() {

    }

    private void addHobbyToUser() {

        String name = "rugby";
        Logo logo = logoService.getById(12);
        Integer userId = 1;

        Hobby hobby = new Hobby("rugby", logo, CssClass.WARNING);

        hobbyService.addHobby(userId, hobby);


    }

    private void addAchievementToExperience(){

    }

    private void addSkillToUser() {

        Integer userId = 1;
        Integer mark = 5;
        Integer skillId = 4;


        userSkillService.addSkillToUser(userId, skillId, mark);
        Skill skill = new Skill("Agile method", logoService.getById(11), "ceci est une description");

        Domain domain = domainService.getById(3);
        userSkillService.addCustomSkillToUser(userId, skill, domain, mark);
    }

    private void loadSkillsAndDomain() {

        Logo logo1 = logoService.getById(9);
        Logo logo = logoService.getById(5);

        domainService.saveOrUpdate(new Domain("Development", logo));

        domainService.addSkill(1, new Skill("CSS", logo1, true));
        domainService.addSkill(1, new Skill("HTML", logo1, true));
        domainService.addSkill(1, new Skill("JavaScript", logo1, true));
        domainService.addSkill(1, new Skill("Java", logo1, true));
        domainService.addSkill(1, new Skill("PHP", logo1, true));

        domainService.saveOrUpdate(new Domain("Framework", logo1));

        domainService.addSkill(2, new Skill("AngularJS", logo, true));
        domainService.addSkill(2, new Skill("Synfony2", logo, true));
        domainService.addSkill(2, new Skill("SpringMVC", logo, true));
        domainService.addSkill(2, new Skill("NodeJS", logo, true));
        domainService.addSkill(2, new Skill("ReactJS", logo, true));

        domainService.saveOrUpdate(new Domain("Management", logo1));



    }

    private void addExperiencesToUser() {

        String title = "Internship CampusGroups";
        String description = "Ceci est un descirption";
        String location = "Paris and New York";
        Integer userId = 1;

        Logo logo = logoService.getById(1);

        Address address = new Address("4 Trelisick Crescent", "Ngaio", "Wellington", "Wellington", 6018, "New Zealand");

        Organisation organisation = new Organisation("CampusGroups", "www.campusgroups.com", "campus_groups.jpg", address);

        Experience experience = new Experience(title,location, description, "", ExperienceType.EXPERIENCE, logo, organisation);

        experienceService.addExperience(userId, experience);

        title = "Centrale Marseille Engineering School";
        location = "marseille, France";
        logo = logoService.getById(6);
        Address address1 = new Address("36rue des chene", "", "Marseille", "PACA", 13003, "France");

        Organisation organisation1 = new Organisation("Centrale Marseille", "www.centrale-marseille.fr", "ecm.png", address1);

        Experience education = new Experience(title, location, description, "top of the pop", ExperienceType.EDUCATION, logo, organisation);


    }

    private void addSocialNetworks() {

        SocialMedia facebook = socialMediaService.getById(1);
        SocialMedia linkedin = socialMediaService.getById(2);

        userInformationService.addSocialNetwork(1, "facebook/lolo", facebook.getId());
        userInformationService.addSocialNetwork(1, "linkedin/lolo", linkedin.getId());
        userInformationService.addSocialNetwork(2, "facebook/lelou", facebook.getId());
    }

    private void loadSocialMedias() {

        Logo facebook = logoService.getById(9);
        socialMediaService.saveOrUpdate(new SocialMedia("Facebook", facebook));
        Logo linkedin = logoService.getById(10);
        socialMediaService.saveOrUpdate(new SocialMedia("Linkedin", linkedin));
        Logo googlePlus = logoService.getById(11);
        socialMediaService.saveOrUpdate(new SocialMedia("Google Plus", googlePlus));

    }

    private void loadLogos() {

        logoService.saveOrUpdate(new Logo("Cogs", "fa fa-cogs", "ox085"));
        logoService.saveOrUpdate(new Logo("Lock", "fa fa-lock", "ox023"));
        logoService.saveOrUpdate(new Logo("Unlock", "fa fa-unlock", "ox09c"));
        logoService.saveOrUpdate(new Logo("Users", "fa fa-users", "ox0c0"));
        logoService.saveOrUpdate(new Logo("User", "fa fa-user", "ox007"));
        logoService.saveOrUpdate(new Logo("Male", "fa fa-male", "ox183"));
        logoService.saveOrUpdate(new Logo("Female", "fa fa-female", "ox182"));
        logoService.saveOrUpdate(new Logo("Briefcase", "fa fa-briefcase", "ox0b1"));
        logoService.saveOrUpdate(new Logo("Facebook", "fa fa-facebook-square", "ox082"));
        logoService.saveOrUpdate(new Logo("Linkedin", "fa fa-linkedin-square", "ox08c"));
        logoService.saveOrUpdate(new Logo("Google Plus", "fa fa-google-plus-square", "ox0d4"));
        logoService.saveOrUpdate(new Logo("Gradution Cap", "fa fa-graduation-cap", "ox19d"));
        logoService.saveOrUpdate(new Logo("Bar chart", "fa fa-bar-chart ", "ox080"));
        logoService.saveOrUpdate(new Logo("Gamepad", "fa fa-gamepad ", "ox11b"));
        logoService.saveOrUpdate(new Logo("Trophy", "fa fa-trophy", "ox091"));
        logoService.saveOrUpdate(new Logo("Like", "fa fa-thumbs-o-up", "ox087"));
    }

    private void loadUsers() {
        User user = new User();
        user.setUsername("lfaivre");
        user.setPassword("password");

        UserInformation userInformation = new UserInformation();
        userInformation.setEmail("laurent.faivre.64@gmail.com");
        userInformation.setFirstName("Laurent");
        userInformation.setLastName("FAIVRE");
        userInformation.setGender(Gender.MALE);
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
        userInformation1.setGender(Gender.FEMALE);
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
