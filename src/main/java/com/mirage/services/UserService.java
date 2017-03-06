package com.mirage.services;

import com.mirage.domains.*;

import java.util.List;

/**
 * Created by Mirage on 24/02/2017.
 */
public interface UserService extends CRUDService<User> {

    User findByUsername(String username);

    void addRole(Integer userId, Integer roleId);

    void removeRole(Integer userId, Integer roleId);

    Hobby addHobby(Integer userId, Hobby hobby);

    void removeHobby(Integer userId, Integer hobbyId);

    Experience addExperience(Integer userId, Experience experience);

    void removeExperience(Integer userId, Integer experienceId);

    Referee addReferee(Integer userId, Referee referee, Integer organisationId);

    void removeReferee(Integer userId, Integer refereeId);

    void removeUserSkill(Integer userId, Integer userSkillId);

    Resume addResume(Integer userId, Resume resume);

    void removeResume(Integer userId, Integer resumeId);
}
