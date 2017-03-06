package com.mirage.services.reposervices;

import com.mirage.domains.Achievement;
import com.mirage.repositories.AchievementRepository;
import com.mirage.services.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mirage on 02/03/2017.
 */
@Service
public class AchievementServiceImpl implements AchievementService {

    private AchievementRepository achievementRepository;

    @Autowired
    public void setAchievementRepository(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    @Override
    public List<?> listAll() {
        List<Achievement> achievements = new ArrayList<>();
        achievementRepository.findAll().forEach(achievements::add);
        return achievements;
    }

    @Override
    public Achievement getById(Integer id) {
        return achievementRepository.findOne(id);
    }

    @Override
    public Achievement saveOrUpdate(Achievement domainObject) {
        return achievementRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        achievementRepository.delete(id);
    }
}
