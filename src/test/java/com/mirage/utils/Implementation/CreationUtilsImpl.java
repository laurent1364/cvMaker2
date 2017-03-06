package com.mirage.utils.Implementation;

import com.mirage.domains.*;
import com.mirage.domains.security.Role;
import com.mirage.domains.utils.Address;
import com.mirage.domains.utils.Logo;
import com.mirage.enums.CssClass;
import com.mirage.enums.ExperienceType;
import com.mirage.enums.Gender;
import com.mirage.services.*;
import com.mirage.utils.CreationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Mirage on 03/03/2017.
 */
@Service
@Profile("test")
public class CreationUtilsImpl implements CreationUtils {

    private LogoService logoService;
    private DomainService domainService;
    private UserService userService;
    private RoleService roleService;
    private SocialMediaService socialMediaService;
    private UserInformationService userInformationService;
    private UserSkillService userSkillService;

    @Autowired
    public void setLogoService(LogoService logoService) {
        this.logoService = logoService;
    }

    @Autowired
    public void setDomainService(DomainService domainService) {
        this.domainService = domainService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setSocialMediaService(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    @Autowired
    public void setUserInformationService(UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

    @Autowired
    public void setUserSkillService(UserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }

    /**************************IMPLEMENTATION **********************************/

    @Override
    public Logo createLogo(){
        return logoService.saveOrUpdate(new Logo("name", "cssClass", "HexaCode"));
    }

    @Override
    public Domain createDomain() {
        Logo logo = createLogo();

        return domainService.saveOrUpdate(new Domain("Domain title", logo));
    }

    @Override
    public Domain createDomainWithSkills() {
        Domain domain = createDomain();
        domainService.addSkill(domain.getId(), new Skill("title", createLogo(), true));
        domainService.addSkill(domain.getId(), new Skill("title", createLogo(), true));
        domainService.addSkill(domain.getId(), new Skill("title", createLogo(), true));
        domainService.addSkill(domain.getId(), new Skill("title", createLogo(), true));
        domainService.addSkill(domain.getId(), new Skill("title", createLogo(), true));

        return domainService.getById(domain.getId());

    }

    @Override
    public User createUser() {

        return userService.saveOrUpdate(new User("username", "password"));
    }

    @Override
    public Role createRole() {
        return roleService.saveOrUpdate(new Role("ADMIN"));
    }

    @Override
    public SocialMedia createSocialMedia() {

        return socialMediaService.saveOrUpdate(new SocialMedia("Social Media", createLogo()));
    }

    @Override
    public Experience createExperience() {
        String title = "title";
        String description = "this is a description";
        Logo logo = this.createLogo();
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

        return experience;
    }

    @Override
    public Referee createReferee() {
        String firstName = "lolo";
        String lastName = "lolo";
        Gender gender = Gender.MALE;
        String function = "CEO";
        String email = "lolo@lolo.molo";
        String phoneNumber = "266551313135135";

        Referee referee = new Referee();
        referee.setFirstName(firstName);
        referee.setLastName(lastName);
        referee.setGender(gender);
        referee.setFunction(function);
        referee.setEmail(email);
        referee.setPhoneNumber(phoneNumber);

        return referee;
    }

    @Override
    public User createAllStuff() {
        User user = createUser();

        UserInformation userInformation = user.getUserInformation();
        userInformation.setFirstName("lolo");
        userInformation.setLastName("lolo");
        userInformation.setBirthDate(new Date());
        userInformation.setAddress(new Address("street", "complement", "city", "state", 123456, "country"));

        userInformation = userInformationService.saveOrUpdate(userInformation);

        Role ADMIN = createRole();
        userService.addRole(user.getId(), ADMIN.getId());

        SocialMedia socialMedia = createSocialMedia();
        SocialMedia socialMedia1 = createSocialMedia();

        userInformationService.addSocialNetwork(userInformation.getId(), "address 1", socialMedia.getId());
        userInformationService.addSocialNetwork(userInformation.getId(), "address 2", socialMedia1.getId());

        Experience experience = userService.addExperience(user.getId(), createExperience());
        Experience experience1 = userService.addExperience(user.getId(), createExperience());
        Experience experience2 = userService.addExperience(user.getId(), createExperience());

        userService.addReferee(user.getId(), createReferee(), experience.getOrganisation().getId());
        userService.addReferee(user.getId(), createReferee(), experience1.getOrganisation().getId());
        userService.addReferee(user.getId(), createReferee(), experience2.getOrganisation().getId());

        userService.addHobby(user.getId(), new Hobby("name", createLogo(), CssClass.DANGER));
        userService.addHobby(user.getId(), new Hobby("success", createLogo(), CssClass.SUCCESS));
        userService.addHobby(user.getId(), new Hobby("warnign", createLogo(), CssClass.WARNING));

        Domain domain = createDomainWithSkills();
        Domain domain1 = createDomainWithSkills();
        Domain domain2 = createDomainWithSkills();

        userSkillService.addSkillToUser(user.getId(), domain.getSkills().get(0).getId(), 8);
        userSkillService.addSkillToUser(user.getId(), domain.getSkills().get(1).getId(), 9);
        userSkillService.addSkillToUser(user.getId(), domain1.getSkills().get(0).getId(), 5);
        userSkillService.addSkillToUser(user.getId(), domain2.getSkills().get(0).getId(), 5);
        userSkillService.addSkillToUser(user.getId(), domain2.getSkills().get(1).getId(), 5);

        return userService.getById(user.getId());
    }

    @Override
    public Resume createResume() {
        Resume resume = new Resume();
        String title = "title";
        String introduction = "this is my introducation";
        String jobSeek = "job seek";
        String hobbyDescription = "hobby descripycsbdcskfcj";
        Boolean displaySocialNetwork = true;
        Boolean displayUserInformation = true;

        resume.setTitle(title);
        resume.setIntroduction(introduction);
        resume.setJobSeek(jobSeek);
        resume.setHobbyDescription(hobbyDescription);
        resume.setDisplaySocialNetwork(displaySocialNetwork);
        resume.setDisplayUserInformation(displayUserInformation);

        return resume;
    }
}
