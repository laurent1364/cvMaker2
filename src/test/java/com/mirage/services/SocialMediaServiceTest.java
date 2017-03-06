package com.mirage.services;

import com.mirage.domains.SocialMedia;
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
@SpringBootTest
@ActiveProfiles("test")
public class SocialMediaServiceTest implements ServiceTestWithLogo {

    private SocialMediaService socialMediaService;
    private CreationUtils creationUtils;

    @Autowired
    public void setSocialMediaService(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    @Autowired
    public void setCreationUtils(CreationUtils creationUtils) {
        this.creationUtils = creationUtils;
    }

    @Override
    @Test
    public void save() throws Exception {
        String name = "social Media Name";
        Logo logo = creationUtils.createLogo();

        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setName(name);
        socialMedia.setLogo(logo);

        SocialMedia savedSocialMedia = socialMediaService.saveOrUpdate(socialMedia);

        assert savedSocialMedia.getId() != null;
        assert savedSocialMedia.getVersion() != null;
        assert savedSocialMedia.getName() != null;
        assert savedSocialMedia.getName().equals(name);
        assert savedSocialMedia.getLogo() != null;
        assert savedSocialMedia.getLogo().getId().equals(logo.getId());
    }

    @Override
    @Test
    public void saveWithLogoId() throws Exception {

        String name = "social Media Name";
        SocialMedia savedSocialMedia = socialMediaService.saveOrUpdateWithLogoId(1, new SocialMedia(name));

        assert savedSocialMedia.getId() != null;
        assert savedSocialMedia.getVersion() != null;
        assert savedSocialMedia.getName() != null;
        assert savedSocialMedia.getName().equals(name);
        assert savedSocialMedia.getLogo() != null;
        assert savedSocialMedia.getLogo().getId().equals(1);
    }

    @Override
    @Test
    public void listAll() throws Exception {
        Logo logo = creationUtils.createLogo();
        socialMediaService.saveOrUpdateWithLogoId(logo.getId(), new SocialMedia("name1"));

        List<SocialMedia> socialMedias = (List<SocialMedia>) socialMediaService.listAll();

        assert socialMedias.size() > 0;
    }

    @Override
    @Test
    public void getOne() throws Exception {

        creationUtils.createLogo();

        String name = "Get one";
        SocialMedia savedSocialMedia = socialMediaService.saveOrUpdateWithLogoId(1, new SocialMedia(name));

        SocialMedia gettedSocialMedia = socialMediaService.getById(savedSocialMedia.getId());

        assert gettedSocialMedia.getId() != null;
        assert gettedSocialMedia.getId().equals(savedSocialMedia.getId());
        assert gettedSocialMedia.getName() != null;
        assert gettedSocialMedia.getName().equals(name);
        assert gettedSocialMedia.getLogo() != null;
        assert gettedSocialMedia.getLogo().getId().equals(1);
    }

    @Override
    @Test
    public void updateWithLogoId() throws Exception {

        String name = "saved";

        creationUtils.createLogo();

        SocialMedia socialMedia = socialMediaService.saveOrUpdateWithLogoId(1, new SocialMedia(name));

        Logo logo = creationUtils.createLogo();

        SocialMedia savedSocialMedia = socialMediaService.saveOrUpdateWithLogoId(logo.getId(), socialMedia);

        assert savedSocialMedia.getId() != null;
        assert savedSocialMedia.getVersion() != null;
        assert savedSocialMedia.getVersion().equals(1);
        assert savedSocialMedia.getId().equals(socialMedia.getId());
        assert savedSocialMedia.getName() != null;
        assert savedSocialMedia.getName().equals(name);
        assert savedSocialMedia.getLogo() != null;
        assert savedSocialMedia.getLogo().getId().equals(logo.getId());
    }

    @Override
    @Test
    public void update() throws Exception {

        String name = "saved";
        creationUtils.createLogo();
        SocialMedia socialMedia = socialMediaService.saveOrUpdateWithLogoId(1, new SocialMedia(name));

        Logo logo = creationUtils.createLogo();
        name = "Nom updated";

        socialMedia.setName(name);
        socialMedia.setLogo(logo);

        SocialMedia savedSocialMedia = socialMediaService.saveOrUpdate(socialMedia);

        assert savedSocialMedia.getId() != null;
        assert savedSocialMedia.getVersion() != null;
        assert savedSocialMedia.getVersion().equals(1);
        assert savedSocialMedia.getId().equals(socialMedia.getId());
        assert savedSocialMedia.getName() != null;
        assert savedSocialMedia.getName().equals(name);
        assert savedSocialMedia.getLogo() != null;
        assert savedSocialMedia.getLogo().getId().equals(logo.getId());
    }

    @Override
    public void delete() throws Exception {
        List<SocialMedia> socialMedias = (List<SocialMedia>) socialMediaService.listAll();

        int size = socialMedias.size();

        socialMediaService.delete(1);

        socialMedias = (List<SocialMedia>) socialMediaService.listAll();

        assert socialMedias.size() == size - 1;


    }
}
