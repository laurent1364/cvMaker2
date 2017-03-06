package com.mirage.services.reposervices;

import com.mirage.domains.Domain;
import com.mirage.domains.Skill;
import com.mirage.domains.utils.Logo;
import com.mirage.repositories.SkillRepository;
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
public class SkillServiceImpl implements SkillService {

    private SkillRepository skillRepository;
    private DomainService domainService;
    private LogoService logoService;

    @Autowired
    public void setSkillRepository(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Autowired
    public void setDomainService(DomainService domainService) {
        this.domainService = domainService;
    }

    @Autowired
    public void setLogoService(LogoService logoService) {
        this.logoService = logoService;
    }

    @Override
    public List<?> listAll() {
        List<Skill> skills = new ArrayList<>();
        skillRepository.findAll().forEach(skills::add);
        return skills;
    }

    @Override
    public Skill getById(Integer id) {
        return skillRepository.findOne(id);
    }

    @Override
    public Skill saveOrUpdate(Skill domainObject) {

        return skillRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        skillRepository.delete(id);
    }

    @Override
    public Skill saveOrUpdateWithLogoId(Integer id, Skill skill) {
        Logo logo = logoService.getById(id);
        skill.setLogo(logo);
        return saveOrUpdate(skill);

    }

    @Override
    public Skill saveOrUpdateWithDomainId(Integer id, Skill skill) {
        Domain domain = domainService.getById(id);
        skill.setDomain(domain);
        return saveOrUpdate(skill);

    }
}
