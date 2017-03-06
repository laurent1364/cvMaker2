package com.mirage.services;

import com.mirage.domains.Achievement;
import com.mirage.domains.Experience;
import com.mirage.domains.User;
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
public class AchievementServiceTest implements ServiceToUserTest {

    private ExperienceService experienceService;
    private AchievementService achievementService;
    private UserService userService;
    private CreationUtils creationUtils;

    @Autowired
    public void setExperienceService(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @Autowired
    public void setAchievementService(AchievementService achievementService) {
        this.achievementService = achievementService;
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
    public void addElement() throws Exception {
        User user = creationUtils.createUser();

        Experience experience = userService.addExperience(user.getId(), creationUtils.createExperience());

        String strAchievement = "this is an achievement";

        Achievement achievement = new Achievement();
        achievement.setAchievement(strAchievement);

        Achievement savedAchievement = experienceService.addAchievement(experience.getId(), achievement);

        assert savedAchievement.getId() != null;
        assert savedAchievement.getAchievement().equals(strAchievement);
        assert savedAchievement.getExperience().getId().equals(experience.getId());

    }

    @Override
    @Test
    public void removeElement() throws Exception {
        User user = creationUtils.createUser();

        Experience experience = userService.addExperience(user.getId(), creationUtils.createExperience());

        experienceService.addAchievement(experience.getId(), new Achievement("this is an achievement"));

        Achievement savedAchievement = experienceService.addAchievement(experience.getId(), new Achievement("this is an achievement"));

        Experience gettedExperience = experienceService.getById(experience.getId());

        assert gettedExperience.getAchievements().size() == 2;

        experienceService.removeAchievement(experience.getId(), savedAchievement.getId());

        gettedExperience = experienceService.getById(experience.getId());

        assert gettedExperience.getAchievements().size() == 1;

    }

    @Override
    @Test
    public void updateElement() throws Exception {

        User user = creationUtils.createUser();

        Experience experience = userService.addExperience(user.getId(), creationUtils.createExperience());

        Achievement savedAchievement = experienceService.addAchievement(experience.getId(), new Achievement("this is an achievement"));

        savedAchievement.setAchievement("updated Achievement");

        Achievement updatedAchievement = achievementService.saveOrUpdate(savedAchievement);

        assert updatedAchievement.getId() != null;
        assert updatedAchievement.getId().equals(savedAchievement.getId());
        assert updatedAchievement.getVersion().equals(1);
        assert updatedAchievement.getExperience().getId().equals(experience.getId());
        assert updatedAchievement.getAchievement().equals("updated Achievement");

    }
}
