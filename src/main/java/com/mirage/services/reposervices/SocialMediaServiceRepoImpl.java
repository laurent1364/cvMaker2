package com.mirage.services.reposervices;

import com.mirage.domains.SocialMedia;
import com.mirage.domains.utils.Logo;
import com.mirage.repositories.SocialMediaRepository;
import com.mirage.services.LogoService;
import com.mirage.services.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mirage on 24/02/2017.
 */
@Service
public class SocialMediaServiceRepoImpl implements SocialMediaService {

    private SocialMediaRepository socialMediaRepository;
    private LogoService logoService;

    @Autowired
    public void setSocialMediaRepository(SocialMediaRepository socialMediaRepository) {
        this.socialMediaRepository = socialMediaRepository;
    }

    @Autowired
    public void setLogoService(LogoService logoService) {
        this.logoService = logoService;
    }

    @Override
    public List<?> listAll() {
        List<SocialMedia> socialMedias = new ArrayList<>();
        socialMediaRepository.findAll().forEach(socialMedias::add);
        return socialMedias;
    }

    @Override
    public SocialMedia getById(Integer id) {
        return socialMediaRepository.findOne(id);
    }

    @Override
    public SocialMedia saveOrUpdate(SocialMedia domainObject) {
        return socialMediaRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {

        socialMediaRepository.delete(id);
    }

    @Override
    public SocialMedia saveOrUpdateWithLogoId(Integer id, SocialMedia socialMedia) {
        Logo logo = logoService.getById(id);
        socialMedia.setLogo(logo);
        return saveOrUpdate(socialMedia);
    }
}
