package com.mirage.services.reposervices;

import com.mirage.domains.*;
import com.mirage.domains.security.Role;
import com.mirage.repositories.UserRepository;
import com.mirage.services.*;
import com.mirage.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Mirage on 24/02/2017.
 */
@Service
public class UserServiceRepoImpl implements UserService {

    private UserRepository userRepository;
    private ExperienceService experienceService;
    private HobbyService hobbyService;
    private EncryptionService encryptionService;
    private RoleService roleService;
    private OrganisationService organisationService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setExperienceService(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @Autowired
    public void setHobbyService(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setOrganisationService(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveOrUpdate(User domainObject) {

        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }

        if(domainObject.getUserInformation() == null){
            UserInformation userInformation = new UserInformation();
            domainObject.setUserInformation(userInformation);
        }

        return userRepository.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        userRepository.delete(id);
    }

    @Override
    public void addRole(Integer userId, Integer roleId) {
        User user = getById(userId);
        Role role = roleService.getById(roleId);
        user.addRole(role);
        saveOrUpdate(user);
    }

    @Override
    public void removeRole(Integer userId, Integer roleId) {
        User user = getById(userId);
        Role role = roleService.getById(roleId);
        user.removeRole(role);
        saveOrUpdate(user);
    }

    @Override
    public Hobby addHobby(Integer userId, Hobby hobby) {
        User user = this.getById(userId);
        user.addHobby(hobby);

        user = this.saveOrUpdate(user);

        for (Hobby h : user.getHobbies()){
            if(h.getCssClass().equals(hobby.getCssClass())
                    && h.getName().equals(hobby.getName())
                    && h.getLogo().getId().equals(hobby.getLogo().getId())){
                return h;
            }
        }
        return null;
    }

    @Override
    public void removeHobby(Integer userId, Integer hobbyId) {

        User user = this.getById(userId);
        Set<Hobby> newHobbies = new HashSet<>();

        for (Hobby h : user.getHobbies()){
            if(!h.getId().equals(hobbyId)){
                newHobbies.add(h);
            }
        }

        user.setHobbies(newHobbies);
        this.saveOrUpdate(user);

    }

    @Override
    public Experience addExperience(Integer userId, Experience experience) {
        User user = this.getById(userId);
        user.addExperience(experience);

        user = this.saveOrUpdate(user);

        for (Experience e : user.getExperiences()){
            if(e.getLogo().getId().equals(experience.getLogo().getId())
                    && e.getTitle().equals(experience.getTitle())
                    && e.getDescription().equals(experience.getDescription())
                    && e.getExperienceType().equals(experience.getExperienceType())){
                return e;
            }
        }
        return null;
    }

    @Override
    public void removeExperience(Integer userId, Integer experienceId) {
        User user = this.getById(userId);
        Set<Experience> newExperiences = new HashSet<>();

        for (Experience e : user.getExperiences()){
            if(!e.getId().equals(experienceId)){
                newExperiences.add(e);
            }
        }

        user.setExperiences(newExperiences);
        this.saveOrUpdate(user);
    }

    @Override
    public Referee addReferee(Integer userId, Referee referee, Integer organisationId) {
        User user = this.getById(userId);
        Organisation organisation = organisationService.getById(organisationId);

        referee.setOrganisation(organisation);

        user.addReferee(referee);

        user = this.saveOrUpdate(user);

        for (Referee r : user.getReferees()){
            if(r.getFirstName().equals(referee.getFirstName())
                    && r.getLastName().equals(referee.getLastName())
                    && r.getEmail().equals(referee.getEmail())
                    && r.getFunction().equals(referee.getFunction())
                    && r.getGender().equals(referee.getGender())){
                return r;
            }
        }
        return null;
    }

    @Override
    public void removeReferee(Integer userId, Integer refereeId) {
        User user = this.getById(userId);
        Set<Referee> newReferees = new HashSet<>();

        for (Referee r : user.getReferees()){
            if(!r.getId().equals(refereeId)){
                newReferees.add(r);
            }
        }

        user.setReferees(newReferees);
        this.saveOrUpdate(user);
    }

    @Override
    public void removeUserSkill(Integer userId, Integer userSkillId) {
        User user = this.getById(userId);
        Set<UserSkill> newUserSkills = new HashSet<>();

        for (UserSkill us : user.getUserSkills()){
            if(!us.getId().equals(userSkillId)){
                newUserSkills.add(us);
            }
        }

        user.setUserSkills(newUserSkills);
        this.saveOrUpdate(user);
    }

    @Override
    public Resume addResume(Integer userId, Resume resume) {
        User user = this.getById(userId);

        user.addResume(resume);

        user = this.saveOrUpdate(user);

        for (Resume r : user.getResumes()){
            if(r.getTitle().equals(resume.getTitle())
                    && r.getIntroduction().equals(resume.getIntroduction())
                    && r.getJobSeek().equals(resume.getJobSeek())
                    && r.getHobbyDescription().equals(resume.getHobbyDescription())
                    && r.getDisplaySocialNetwork() == resume.getDisplaySocialNetwork()
                    && r.getDisplayUserInformation() == resume.getDisplayUserInformation()){
                return r;
            }
        }
        return null;
    }

    @Override
    public void removeResume(Integer userId, Integer resumeId) {
        User user = this.getById(userId);
        Set<Resume> newResumes = new HashSet<>();

        for (Resume r : user.getResumes()){
            if(!r.getId().equals(resumeId)){
                newResumes.add(r);
            }
        }

        user.setResumes(newResumes);
        this.saveOrUpdate(user);
    }
}
