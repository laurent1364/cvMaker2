package com.mirage.services.reposervices;

import com.mirage.domains.Hobby;
import com.mirage.domains.User;
import com.mirage.repositories.HobbyRepository;
import com.mirage.services.HobbyService;
import com.mirage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mirage on 02/03/2017.
 */
@Service
public class HobbyServiceImpl implements HobbyService{

    private HobbyRepository hobbyRepository;
    private UserService userService;

    @Autowired
    public void setHobbyRepository(HobbyRepository hobbyRepository) {
        this.hobbyRepository = hobbyRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<?> listAll() {
        List<Hobby> hobbies = new ArrayList<>();
        hobbyRepository.findAll().forEach(hobbies::add);
        return hobbies;
    }

    @Override
    public Hobby getById(Integer id) {
        return hobbyRepository.findOne(id);
    }

    @Override
    public Hobby saveOrUpdate(Hobby domainObject) {
        return hobbyRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        hobbyRepository.delete(id);
    }

    @Override
    public Hobby addHobby(Integer userId, Hobby hobby) {
        User user = userService.getById(userId);
        hobby.setUser(user);
        return saveOrUpdate(hobby);
    }
}
