package com.mirage.services;

import com.mirage.domains.Resume;

/**
 * Created by Mirage on 02/03/2017.
 */
public interface ResumeService extends CRUDService<Resume>{

    void addExperience(Integer resumeId, Integer experienceId);
    void removeExperience(Integer resumeId, Integer experienceId);

    void addHobby(Integer resumeId, Integer hobbyId);
    void removeHobby(Integer resumeId, Integer hobbyId);

    void addReferee(Integer resumeId, Integer refereeId);
    void removeReferee(Integer resumeId, Integer refereeId);

    void addUserSkill(Integer resumeId, Integer userSkillId);
    void removeUserSkill(Integer resumeId, Integer userSkillId);

    void changeDisplaySocialNetwork(Integer resumeId);
    void changeDisplayUserInformations(Integer resumeId);
}
