package com.mirage.services.reposervices;

import com.mirage.domains.Domain;
import com.mirage.domains.Skill;
import com.mirage.domains.User;
import com.mirage.domains.UserSkill;
import com.mirage.repositories.UserSkillRepository;
import com.mirage.services.DomainService;
import com.mirage.services.SkillService;
import com.mirage.services.UserService;
import com.mirage.services.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mirage on 02/03/2017.
 */
@Service
public class UserSkillServiceImpl implements UserSkillService{

    private UserSkillRepository userSkillRepository;
    private UserService userService;
    private SkillService skillService;
    private DomainService domainService;

    @Autowired
    public void setUserSkillRepository(UserSkillRepository userSkillRepository) {
        this.userSkillRepository = userSkillRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    @Autowired
    public void setDomainService(DomainService domainService) {
        this.domainService = domainService;
    }

    @Override
    public List<?> listAll() {
        List<UserSkill> userSkills = new ArrayList<>();
        userSkillRepository.findAll().forEach(userSkills::add);
        return userSkills;
    }

    @Override
    public UserSkill getById(Integer id) {
        return userSkillRepository.findOne(id);
    }

    @Override
    public UserSkill saveOrUpdate(UserSkill domainObject) {
        return userSkillRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        userSkillRepository.delete(id);
    }

    @Override
    public void addSkillToUser(Integer userId, Integer skillId, Integer mark) {
        User user = userService.getById(userId);
        Skill skill = skillService.getById(skillId);
        UserSkill userSkill = new UserSkill(user, skill, mark);
        saveOrUpdate(userSkill);
    }

    @Override
    public void addCustomSkillToUser(Integer userId, Skill skill, Domain domain, Integer mark) {

        skill.setPublic(false);
        Skill newSkill = domainService.addSkill(domain.getId(), skill);

        addSkillToUser(userId, newSkill.getId(), mark);

    }

    @Override
    public UserSkill addUserSkill(Integer userId, Integer skillId, Integer mark) {

        User user = userService.getById(userId);
        Skill skill = skillService.getById(skillId);

        UserSkill userSkill = new UserSkill();
        userSkill.setUser(user);
        userSkill.setSkill(skill);
        userSkill.setMark(mark);

        return this.saveOrUpdate(userSkill);
    }
}