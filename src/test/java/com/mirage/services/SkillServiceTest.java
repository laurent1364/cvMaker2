package com.mirage.services;

import com.mirage.domains.Domain;
import com.mirage.domains.Skill;
import com.mirage.domains.utils.Logo;
import com.mirage.utils.CreationUtils;
import com.mirage.utils.services.ServiceTestWithLogo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Mirage on 03/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class SkillServiceTest implements ServiceTestWithLogo {

    private SkillService skillService;
    private DomainService domainService;
    private CreationUtils creationUtils;

    @Autowired
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    @Autowired
    public void setDomainService(DomainService domainService) {
        this.domainService = domainService;
    }

    @Autowired
    public void setCreationUtils(CreationUtils creationUtils) {
        this.creationUtils = creationUtils;
    }

    @Override
    @Test
    public void save() throws Exception {

        String title = "title";
        Boolean isPublic = true;
        String description = "description";
        Logo logo = creationUtils.createLogo();
        Domain domain = creationUtils.createDomain();

        Skill skill = new Skill();
        skill.setPublic(isPublic);
        skill.setTitle(title);
        skill.setDescription(description);
        skill.setDomain(domain);
        skill.setLogo(logo);

        Skill savedSkill = skillService.saveOrUpdate(skill);

        assert savedSkill.getId() != null;
        assert savedSkill.getVersion() != null;
        assert savedSkill.getPublic() == isPublic;
        assert savedSkill.getTitle() != null;
        assert savedSkill.getTitle().equals(title);
        assert savedSkill.getDescription() != null;
        assert savedSkill.getDescription().equals(description);
        assert savedSkill.getDomain().getId().equals(domain.getId());
        assert savedSkill.getLogo().getId().equals(logo.getId());

        Domain domain1 = domainService.getById(domain.getId());
        Skill skill1 = domain1.getSkills().get(0);

        assert skill1.getId().equals(savedSkill.getId());
    }

    @Override
    @Test
    public void listAll() throws Exception {

        Domain domain = creationUtils.createDomain();

        domainService.addSkill(domain.getId(), new Skill("title",null,false));

        List<Skill> skills = (List<Skill>) skillService.listAll();

        assert skills.size() > 0;

    }

    @Override
    @Test
    public void getOne() throws Exception {
        String title = "title";
        Boolean isPublic = true;
        String description = "description";
        Logo logo = creationUtils.createLogo();
        Domain domain = creationUtils.createDomain();

        Skill skill = new Skill();
        skill.setPublic(isPublic);
        skill.setTitle(title);
        skill.setDescription(description);
        skill.setDomain(domain);
        skill.setLogo(logo);

        Skill savedSkill = skillService.saveOrUpdate(skill);

        Skill gettedSkill = skillService.getById(savedSkill.getId());

        assert gettedSkill.getId() != null;
        assert gettedSkill.getVersion() != null;
        assert gettedSkill.getVersion().equals(0);
        assert gettedSkill.getPublic().equals(savedSkill.getPublic());
        assert gettedSkill.getTitle().equals(savedSkill.getTitle());
        assert gettedSkill.getDescription().equals(savedSkill.getDescription());
        assert gettedSkill.getDomain().getId().equals(savedSkill.getDomain().getId());
        assert gettedSkill.getLogo().getId().equals(savedSkill.getLogo().getId());
    }

    @Override
    @Test
    public void update() throws Exception {

        String title = "title";
        Boolean isPublic = true;
        String description = "description";
        Logo logo = creationUtils.createLogo();
        Domain domain = creationUtils.createDomain();

        Skill skill = new Skill();
        skill.setPublic(isPublic);
        skill.setTitle(title);
        skill.setDescription(description);
        skill.setDomain(domain);
        skill.setLogo(logo);

        Skill savedSkill = skillService.saveOrUpdate(skill);

        title = "title update";
        isPublic = false;
        description = "description update";
        logo = creationUtils.createLogo();

        savedSkill.setPublic(isPublic);
        savedSkill.setTitle(title);
        savedSkill.setDescription(description);
        savedSkill.setLogo(logo);

        Skill updatedSkill = skillService.saveOrUpdate(savedSkill);

        assert updatedSkill.getId() != null;
        assert updatedSkill.getId().equals(savedSkill.getId());
        assert updatedSkill.getVersion() != null;
        assert updatedSkill.getVersion().equals(1);
        assert updatedSkill.getPublic().equals(savedSkill.getPublic());
        assert updatedSkill.getTitle().equals(savedSkill.getTitle());
        assert updatedSkill.getDescription().equals(savedSkill.getDescription());
        assert updatedSkill.getDomain().getId().equals(savedSkill.getDomain().getId());
        assert updatedSkill.getLogo().getId().equals(savedSkill.getLogo().getId());
    }

    @Override
    @Test
    public void delete() throws Exception {

        Skill skill = skillService.saveOrUpdate(new Skill("title", creationUtils.createLogo(), true, creationUtils.createDomain()));
        List<Skill> skills = (List<Skill>) skillService.listAll();
        int size = skills.size();

        skillService.delete(skill.getId());

        skills = (List<Skill>) skillService.listAll();

        assert skills.size() == size - 1;
    }

    @Override
    @Test
    public void saveWithLogoId() throws Exception {

        String title = "title";
        Boolean isPublic = true;
        Logo logo = creationUtils.createLogo();
        Domain domain = creationUtils.createDomain();

        Skill skill = skillService.saveOrUpdateWithLogoId(logo.getId(), new Skill(title, null, isPublic, domain));

        assert skill.getId() != null;
        assert skill.getVersion() != null;
        assert skill.getVersion() != null;
        assert skill.getPublic().equals(isPublic);
        assert skill.getTitle().equals(title);
        assert skill.getDomain().getId().equals(domain.getId());
        assert skill.getLogo().getId().equals(logo.getId());
    }

    @Override
    @Test
    public void updateWithLogoId() throws Exception {
        String title = "title";
        Boolean isPublic = true;
        Logo logo = creationUtils.createLogo();
        Domain domain = creationUtils.createDomain();
        Skill savedSkill = skillService.saveOrUpdate(new Skill(title, logo, isPublic, domain));

        assert savedSkill.getLogo().getId().equals(logo.getId());

        logo = creationUtils.createLogo();

        Skill updatedSkill = skillService.saveOrUpdateWithLogoId(logo.getId(), savedSkill);

        assert updatedSkill.getId() != null;
        assert updatedSkill.getId().equals(savedSkill.getId());
        assert updatedSkill.getVersion().equals(1);
        assert updatedSkill.getLogo().getId().equals(logo.getId());
    }
}
