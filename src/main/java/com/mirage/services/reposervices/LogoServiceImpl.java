package com.mirage.services.reposervices;

import com.mirage.domains.utils.Logo;
import com.mirage.repositories.utils.LogoRepository;
import com.mirage.services.LogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mirage on 02/03/2017.
 */
@Service
public class LogoServiceImpl implements LogoService {

    private LogoRepository logoRepository;

    @Autowired
    public void setLogoRepository(LogoRepository logoRepository) {
        this.logoRepository = logoRepository;
    }

    @Override
    public List<?> listAll() {
        List<Logo> logos = new ArrayList<>();
        logoRepository.findAll().forEach(logos::add);
        return logos;
    }

    @Override
    public Logo getById(Integer id) {
        return logoRepository.findOne(id);
    }

    @Override
    public Logo saveOrUpdate(Logo domainObject) {
        return logoRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        logoRepository.delete(id);
    }
}
