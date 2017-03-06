package com.mirage.services.reposervices;

import com.mirage.domains.Domain;
import com.mirage.domains.Skill;
import com.mirage.domains.utils.Logo;
import com.mirage.repositories.DomainRepository;
import com.mirage.services.DomainService;
import com.mirage.services.LogoService;
import com.mirage.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mirage on 02/03/2017.
 */
@Service
public class DomainServiceImpl implements DomainService{

    private DomainRepository domainRepository;
    private SkillService skillService;
    private LogoService logoService;

    @Autowired
    public void setDomainRepository(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @Autowired
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    @Autowired
    public void setLogoService(LogoService logoService) {
        this.logoService = logoService;
    }

    @Override
    public List<?> listAll() {
        List<Domain> domains = new ArrayList<>();
        domainRepository.findAll().forEach(domains::add);
        return domains;
    }

    @Override
    public Domain getById(Integer id) {
        return domainRepository.findOne(id);
    }

    @Override
    public Domain saveOrUpdate(Domain domainObject) {
        return domainRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        domainRepository.delete(id);
    }

    @Override
    public Skill addSkill(Integer domainId, Skill skill) {
        Domain domain = getById(domainId);

        skill.setDomain(domain);

        return skillService.saveOrUpdate(skill);
    }

    @Override
    public Domain saveOrUpdateWithLogoId(Integer id, Domain domain) {
        Logo logo = logoService.getById(id);
        domain.setLogo(logo);

        return saveOrUpdate(domain);
    }
}