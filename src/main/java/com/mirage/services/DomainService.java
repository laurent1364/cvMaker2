package com.mirage.services;

import com.mirage.domains.Domain;
import com.mirage.domains.Skill;

/**
 * Created by Mirage on 02/03/2017.
 */
public interface DomainService extends CRUDService<Domain> {

    Skill addSkill(Integer domainId, Skill skill);

    Domain saveOrUpdateWithLogoId(Integer id, Domain domain);
}
