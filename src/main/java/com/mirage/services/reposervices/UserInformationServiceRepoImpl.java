package com.mirage.services.reposervices;

import com.mirage.domains.UserInformation;
import com.mirage.repositories.UserInformationRepository;
import com.mirage.services.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Mirage on 24/02/2017.
 */
public class UserInformationServiceRepoImpl implements UserInformationService {

    private UserInformationRepository userInformationRepository;

    @Autowired
    public void setUserInformationRepository(UserInformationRepository userInformationRepository) {
        this.userInformationRepository = userInformationRepository;
    }

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public UserInformation getById(Integer id) {
        return userInformationRepository.findOne(id);
    }

    @Override
    public UserInformation saveOrUpdate(UserInformation domainObject) {
        return userInformationRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        userInformationRepository.delete(id);
    }
}
