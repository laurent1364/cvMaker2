package com.mirage.services.reposervices;

import com.mirage.domains.Achievement;
import com.mirage.domains.Experience;
import com.mirage.domains.Organisation;
import com.mirage.domains.User;
import com.mirage.repositories.ExperienceRepository;
import com.mirage.services.ExperienceService;
import com.mirage.services.OrganisationService;
import com.mirage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Mirage on 02/03/2017.
 */
@Service
public class ExperienceServiceImpl implements ExperienceService{

    private ExperienceRepository experienceRepository;
    private OrganisationService organisationService;
    private UserService userService;

    @Autowired
    public void setExperienceRepository(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Autowired
    public void setOrganisationService(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<?> listAll() {
        List<Experience> experiences = new ArrayList<>();
        experienceRepository.findAll().forEach(experiences::add);
        return experiences;
    }

    @Override
    public Experience getById(Integer id) {
        return experienceRepository.findOne(id);
    }

    @Override
    public Experience saveOrUpdate(Experience domainObject) {
        return experienceRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        experienceRepository.delete(id);
    }

    @Override
    public Experience addExperience(Integer userId, Experience experience) {
        User user = userService.getById(userId);
        experience.setUser(user);
        return  saveOrUpdate(experience);
    }

    @Override
    public Achievement addAchievement(Integer experienceId, Achievement achievement) {
        Experience experience = this.getById(experienceId);
        experience.addAchievement(achievement);

        Experience savedExperience = this.saveOrUpdate(experience);

        for (Achievement a : savedExperience.getAchievements()){
            if (a.getAchievement().equals(achievement.getAchievement())){
                return a;
            }
        }
        return null;
    }

    @Override
    public void removeAchievement(Integer experienceId, Integer achievementId) {
        Experience experience = this.getById(experienceId);
        Set<Achievement> newAchievements = new HashSet<>();

        for (Achievement a : experience.getAchievements()){
            if(!a.getId().equals(achievementId)){
                newAchievements.add(a);
            }
        }

        experience.setAchievements(newAchievements);
        this.saveOrUpdate(experience);
    }
}
