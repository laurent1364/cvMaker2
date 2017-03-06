package com.mirage.services.reposervices;

import com.mirage.domains.Referee;
import com.mirage.repositories.RefereeRepository;
import com.mirage.services.RefereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mirage on 02/03/2017.
 */
@Service
public class RefereeServiceImpl implements RefereeService{

    private RefereeRepository refereeRepository;

    @Autowired
    public void setRefereeRepository(RefereeRepository refereeRepository) {
        this.refereeRepository = refereeRepository;
    }

    @Override
    public List<?> listAll() {
        List<Referee> referees = new ArrayList<>();
        refereeRepository.findAll().forEach(referees::add);
        return referees;
    }

    @Override
    public Referee getById(Integer id) {
        return refereeRepository.findOne(id);
    }

    @Override
    public Referee saveOrUpdate(Referee domainObject) {
        return refereeRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        refereeRepository.delete(id);
    }
}
