package com.mirage.services.reposervices;

import com.mirage.domains.Template;
import com.mirage.repositories.TemplateRepository;
import com.mirage.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mirage on 02/03/2017.
 */
@Service
public class TemplateServiceImpl implements TemplateService{

    private TemplateRepository templateRepository;

    @Autowired
    public void setTemplateRepository(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public List<?> listAll() {
        List<Template> templates = new ArrayList<>();
        templateRepository.findAll().forEach(templates::add);
        return templates;
    }

    @Override
    public Template getById(Integer id) {
        return templateRepository.findOne(id);
    }

    @Override
    public Template saveOrUpdate(Template domainObject) {
        return templateRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        templateRepository.delete(id);
    }
}