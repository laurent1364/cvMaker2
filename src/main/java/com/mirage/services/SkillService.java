package com.mirage.services;

import com.mirage.domains.Skill;

/**
 * Created by Mirage on 02/03/2017.
 */
public interface SkillService extends CRUDService<Skill> {

    Skill saveOrUpdateWithLogoId(Integer id, Skill skill);
    Skill saveOrUpdateWithDomainId(Integer id, Skill skill);
}
