package com.mirage.services;

import com.mirage.domains.Achievement;
import com.mirage.domains.Experience;

/**
 * Created by Mirage on 02/03/2017.
 */
public interface ExperienceService extends CRUDService<Experience> {

    Experience addExperience(Integer userId, Experience experience);

    Achievement addAchievement(Integer experienceId, Achievement achievement);
    void removeAchievement(Integer experienceId, Integer achievementId);
}
