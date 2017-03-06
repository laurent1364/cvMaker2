package com.mirage.services;

import com.mirage.domains.Domain;
import com.mirage.domains.Skill;
import com.mirage.domains.UserSkill;

/**
 * Created by Mirage on 02/03/2017.
 */
public interface UserSkillService extends CRUDService<UserSkill> {
    void addSkillToUser(Integer userId, Integer skillId, Integer mark);

    void addCustomSkillToUser(Integer userId, Skill skill, Domain domain, Integer mark);

    UserSkill addUserSkill(Integer userId, Integer skillId, Integer mark);
}
