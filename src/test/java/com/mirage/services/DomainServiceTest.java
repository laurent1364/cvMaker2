package com.mirage.services;

import com.mirage.domains.Domain;
import com.mirage.domains.Skill;
import com.mirage.domains.utils.Logo;
import com.mirage.utils.CreationUtils;
import com.mirage.utils.Implementation.CreationUtilsImpl;
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
public class DomainServiceTest implements ServiceTestWithLogo {

    private DomainService domainService;
    private CreationUtils creationUtils;

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

        Logo logo = creationUtils.createLogo();
        String title = "title";

        Domain domain = new Domain();
        domain.setTitle(title);
        domain.setLogo(logo);

        Domain savedDomain = domainService.saveOrUpdate(domain);

        assert savedDomain.getId() != null;
        assert savedDomain.getVersion() != null;
        assert savedDomain.getTitle() != null;
        assert savedDomain.getTitle().equals(title);
        assert savedDomain.getLogo() != null;
        assert savedDomain.getLogo().equals(logo);
    }

    @Override
    @Test
    public void listAll() throws Exception {
        creationUtils.createDomain();

        List<Domain> domains = (List<Domain>) domainService.listAll();

        assert domains.size() > 0;
    }

    @Override
    @Test
    public void getOne() throws Exception {

        creationUtils.createLogo();
        String title = "title";

        Domain savedDomain = domainService.saveOrUpdateWithLogoId(1, new Domain(title));

        Domain gettedDomain = domainService.getById(savedDomain.getId());

        assert gettedDomain.getId().equals(savedDomain.getId());
        assert gettedDomain.getVersion() != null;
        assert gettedDomain.getTitle() != null;
        assert gettedDomain.getTitle().equals(title);
        assert gettedDomain.getLogo() != null;
        assert gettedDomain.getLogo().getId().equals(1);

    }

    @Override
    @Test
    public void update() throws Exception {

        creationUtils.createLogo();
        String title = "title";
        Domain savedDomain = domainService.saveOrUpdateWithLogoId(1, new Domain(title));

        Logo logo = creationUtils.createLogo();
        title = "title updated";

        savedDomain.setLogo(logo);
        savedDomain.setTitle(title);

        Domain updatedDomain = domainService.saveOrUpdate(savedDomain);


        assert updatedDomain.getId().equals(savedDomain.getId());
        assert updatedDomain.getVersion() != null;
        assert updatedDomain.getVersion().equals(1);
        assert updatedDomain.getTitle() != null;
        assert updatedDomain.getTitle().equals(title);
        assert updatedDomain.getLogo() != null;
        assert updatedDomain.getLogo().getId().equals(logo.getId());


    }

    @Override
    @Test
    public void delete() throws Exception {

        creationUtils.createLogo();
        domainService.saveOrUpdateWithLogoId(1, new Domain("title"));

        List<Domain> domains = (List<Domain>) domainService.listAll();
        int size = domains.size();

        domainService.delete(1);
        domains = (List<Domain>) domainService.listAll();

        assert domains.size() == size -1;
    }

    @Override
    @Test
    public void saveWithLogoId() throws Exception {

        Logo logo = creationUtils.createLogo();
        String title = "title";

        Domain savedDomain = domainService.saveOrUpdateWithLogoId(logo.getId(), new Domain(title));

        assert savedDomain.getId() != null;
        assert savedDomain.getTitle() != null;
        assert savedDomain.getTitle().equals(title);
        assert savedDomain.getLogo() != null;
        assert savedDomain.getLogo().getId().equals(logo.getId());

    }

    @Override
    @Test
    public void updateWithLogoId() throws Exception {

        creationUtils.createLogo();
        creationUtils.createLogo();
        Domain domain = domainService.saveOrUpdateWithLogoId(1, new Domain("title"));

        Domain savedDomain = domainService.saveOrUpdateWithLogoId(2, domain);

        assert savedDomain.getId() != null;
        assert savedDomain.getVersion() != null;
        assert savedDomain.getVersion().equals(1);
        assert savedDomain.getId().equals(domain.getId());
        assert savedDomain.getTitle() != null;
        assert savedDomain.getTitle().equals(domain.getTitle());
        assert savedDomain.getLogo() != null;
        assert savedDomain.getLogo().getId().equals(2);

    }

    @Test
    public void addSkill() throws Exception{

        Domain domain = creationUtils.createDomain();

        Skill skill = domainService.addSkill(domain.getId(), new Skill("title", null, true));

        assert skill.getId() != null;
        assert skill.getVersion().equals(0);


        domain = domainService.getById(domain.getId());

        Skill gettedSkill = domain.getSkills().get(0);

        assert skill.getId().equals(gettedSkill.getId());
        assert skill.getTitle().equals(gettedSkill.getTitle());
        assert skill.getPublic().equals(gettedSkill.getPublic());

    }
}
