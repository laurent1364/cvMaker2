package com.mirage.services.reposervices;

import com.mirage.domains.SocialNetwork;
import com.mirage.repositories.SocialNetworkRepository;
import com.mirage.services.SocialNetworkService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Mirage on 24/02/2017.
 */
public class SocialNetworkServiceRepoImpl implements SocialNetworkService {

    private SocialNetworkRepository socialNetworkRepository;

    @Autowired
    public void setSocialNetworkRepository(SocialNetworkRepository socialNetworkRepository) {
        this.socialNetworkRepository = socialNetworkRepository;
    }

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public SocialNetwork getById(Integer id) {
        return socialNetworkRepository.findOne(id);
    }

    @Override
    public SocialNetwork saveOrUpdate(SocialNetwork domainObject) {
        return socialNetworkRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {

        socialNetworkRepository.delete(id);
    }
}
