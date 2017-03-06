package com.mirage.services.reposervices;

import com.mirage.domains.Organisation;
import com.mirage.repositories.OrganisationRepository;
import com.mirage.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mirage on 02/03/2017.
 */
@Service
public class OrganisationServiceImpl implements OrganisationService{

    private OrganisationRepository organisationRepository;

    @Autowired
    public void setOrganisationRepository(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @Override
    public List<?> listAll() {
        List<Organisation> organisations = new ArrayList<>();
        organisationRepository.findAll().forEach(organisations::add);
        return organisations;
    }

    @Override
    public Organisation getById(Integer id) {
        return organisationRepository.findOne(id);
    }

    @Override
    public Organisation saveOrUpdate(Organisation domainObject) {
        return organisationRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        organisationRepository.delete(id);
    }
}
