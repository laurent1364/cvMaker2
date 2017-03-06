package com.mirage.services.reposervices;

import com.mirage.domains.*;
import com.mirage.repositories.ResumeRepository;
import com.mirage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Mirage on 02/03/2017.
 */
@Service
public class ResumeServiceImpl implements ResumeService{

    private ResumeRepository resumeRepository;
    private ExperienceService experienceService;
    private HobbyService hobbyService;
    private RefereeService refereeService;
    private UserSkillService userSkillService;



    @Autowired
    public void setResumeRepository(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Autowired
    public void setExperienceService(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @Autowired
    public void setHobbyService(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    @Autowired
    public void setRefereeService(RefereeService refereeService) {
        this.refereeService = refereeService;
    }

    @Autowired
    public void setUserSkillService(UserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }

    @Override
    public List<?> listAll() {
        List<Resume> resumes = new ArrayList<>();
        resumeRepository.findAll().forEach(resumes::add);
        return resumes;
    }

    @Override
    public Resume getById(Integer id) {
        return resumeRepository.findOne(id);
    }

    @Override
    public Resume saveOrUpdate(Resume domainObject) {
        return resumeRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        resumeRepository.delete(id);
    }

    @Override
    public void addExperience(Integer resumeId, Integer experienceId) {
        Resume resume = this.getById(resumeId);
        Experience experience = experienceService.getById(experienceId);

        resume.addExperience(experience);

        this.saveOrUpdate(resume);
    }

    @Override
    public void removeExperience(Integer resumeId, Integer experienceId) {

        Resume resume = this.getById(resumeId);
        Set<Experience> experiences = new HashSet<>();

        if (resume.getExperiences() != null){
            for (Experience exp : resume.getExperiences()){
                if(exp.getId() != experienceId){
                    experiences.add(exp);
                }
            }
        }

        resume.setExperiences(experiences);
        this.saveOrUpdate(resume);

    }

    @Override
    public void addHobby(Integer resumeId, Integer hobbyId) {
        Resume resume = this.getById(resumeId);
        Hobby hobby = hobbyService.getById(hobbyId);

        resume.addHobby(hobby);

        this.saveOrUpdate(resume);
    }

    @Override
    public void removeHobby(Integer resumeId, Integer hobbyId) {
        Resume resume = this.getById(resumeId);
        Set<Hobby> hobbies = new HashSet<>();

        if (resume.getHobbies() != null){
            for (Hobby hobby : resume.getHobbies()){
                if(hobby.getId() != hobbyId){
                    hobbies.add(hobby);
                }
            }
        }

        resume.setHobbies(hobbies);
        this.saveOrUpdate(resume);
    }

    @Override
    public void addReferee(Integer resumeId, Integer refereeId) {
        Resume resume = this.getById(resumeId);
        Referee referee = refereeService.getById(refereeId);

        resume.addReferee(referee);

        this.saveOrUpdate(resume);
    }

    @Override
    public void removeReferee(Integer resumeId, Integer refereeId) {
        Resume resume = this.getById(resumeId);
        Set<Referee> referees = new HashSet<>();

        if (resume.getUserSkills() != null){
            for (Referee ref : resume.getReferees()){
                if(ref.getId() != refereeId){
                    referees.add(ref);
                }
            }
        }

        resume.setReferees(referees);
        this.saveOrUpdate(resume);
    }

    @Override
    public void addUserSkill(Integer resumeId, Integer userSkillId) {
        Resume resume = this.getById(resumeId);
        UserSkill userSkill = userSkillService.getById(userSkillId);

        resume.addUserSkill(userSkill);

        this.saveOrUpdate(resume);
    }

    @Override
    public void removeUserSkill(Integer resumeId, Integer userSkillId) {
        Resume resume = this.getById(resumeId);
        Set<UserSkill> userSkills = new HashSet<>();

        if (resume.getUserSkills() != null){
            for (UserSkill us : resume.getUserSkills()){
                if(us.getId() != userSkillId){
                    userSkills.add(us);
                }
            }
        }

        resume.setUserSkills(userSkills);
        this.saveOrUpdate(resume);
    }

    @Override
    public void changeDisplaySocialNetwork(Integer resumeId) {
        Resume resume = this.getById(resumeId);
        resume.setDisplaySocialNetwork(!resume.getDisplaySocialNetwork());
        this.saveOrUpdate(resume);
    }

    @Override
    public void changeDisplayUserInformations(Integer resumeId) {
        Resume resume = this.getById(resumeId);
        resume.setDisplayUserInformation(!resume.getDisplayUserInformation());
        this.saveOrUpdate(resume);
    }
}
