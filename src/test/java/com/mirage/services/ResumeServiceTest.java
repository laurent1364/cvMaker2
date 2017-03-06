package com.mirage.services;

import com.mirage.domains.*;
import com.mirage.utils.CreationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mirage on 07/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ResumeServiceTest {

    private ResumeService resumeService;
    private UserService userService;
    private CreationUtils creationUtils;

    @Autowired
    public void setResumeService(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCreationUtils(CreationUtils creationUtils) {
        this.creationUtils = creationUtils;
    }

    @Test
    public void createResume() throws Exception{
        User user = creationUtils.createAllStuff();

        Resume resume = new Resume();
        String title = "title";
        String introduction = "this is my introducation";
        String jobSeek = "job seek";
        String hobbyDescription = "hobby descripycsbdcskfcj";
        Boolean displaySocialNetwork = true;
        Boolean displayUserInformation = true;

        resume.setTitle(title);
        resume.setIntroduction(introduction);
        resume.setJobSeek(jobSeek);
        resume.setHobbyDescription(hobbyDescription);
        resume.setDisplaySocialNetwork(displaySocialNetwork);
        resume.setDisplayUserInformation(displayUserInformation);

        Resume savedResume = userService.addResume(user.getId(), resume);

        assert savedResume.getId() != null;
        assert savedResume.getTitle().equals(title);
        assert savedResume.getIntroduction().equals(introduction);
        assert savedResume.getJobSeek().equals(jobSeek);
        assert savedResume.getHobbyDescription().equals(hobbyDescription);
        assert savedResume.getDisplaySocialNetwork() == displaySocialNetwork;
        assert savedResume.getDisplayUserInformation() == displayUserInformation;
    }

    @Test
    public void addExperience() throws Exception{

        User user = creationUtils.createAllStuff();
        Resume resume = creationUtils.createResume();

        Resume savedResume = userService.addResume(user.getId(), resume);

        Experience experience = null;

        for (Experience exp : user.getExperiences()){
            experience = exp;
            break;
        }

        resumeService.addExperience(savedResume.getId(), experience.getId());

        Resume gettedResume = resumeService.getById(savedResume.getId());

        assert gettedResume.getExperiences().size() == 1;

    }
    @Test
    public void removeExperience() throws Exception{

        User user = creationUtils.createAllStuff();
        Resume resume = creationUtils.createResume();

        Resume savedResume = userService.addResume(user.getId(), resume);

        List<Experience> experiences = new ArrayList<>();
        experiences.addAll(user.getExperiences());

        resumeService.addExperience(savedResume.getId(), experiences.get(0).getId());
        resumeService.addExperience(savedResume.getId(), experiences.get(1).getId());

        Resume gettedResume = resumeService.getById(savedResume.getId());

        assert gettedResume.getExperiences().size() == 2;

        resumeService.removeExperience(savedResume.getId(), experiences.get(1).getId());

        gettedResume = resumeService.getById(savedResume.getId());

        assert gettedResume.getExperiences().size() == 1;
    }
    @Test
    public void addHobby() throws Exception{

        User user = creationUtils.createAllStuff();
        Resume resume = creationUtils.createResume();

        Resume savedResume = userService.addResume(user.getId(), resume);

        Hobby hobby = null;

        for (Hobby hob : user.getHobbies()){
            hobby = hob;
            break;
        }

        resumeService.addHobby(savedResume.getId(), hobby.getId());

        Resume gettedResume = resumeService.getById(savedResume.getId());

        assert gettedResume.getHobbies().size() == 1;
    }
    @Test
    public void removeHobby() throws Exception{
        User user = creationUtils.createAllStuff();
        Resume resume = creationUtils.createResume();

        Resume savedResume = userService.addResume(user.getId(), resume);

        List<Hobby> hobbies = new ArrayList<>();
        hobbies.addAll(user.getHobbies());


        resumeService.addHobby(savedResume.getId(), hobbies.get(0).getId());
        resumeService.addHobby(savedResume.getId(), hobbies.get(1).getId());

        Resume gettedResume = resumeService.getById(savedResume.getId());

        assert gettedResume.getHobbies().size() == 2;

        resumeService.removeHobby(savedResume.getId(), hobbies.get(1).getId());

        gettedResume = resumeService.getById(savedResume.getId());

        assert gettedResume.getHobbies().size() == 1;
    }
    @Test
    public void addReferee() throws Exception{
        User user = creationUtils.createAllStuff();
        Resume resume = creationUtils.createResume();

        Resume savedResume = userService.addResume(user.getId(), resume);

        Referee referee = null;

        for (Referee ref : user.getReferees()){
            referee = ref;
            break;
        }

        resumeService.addReferee(savedResume.getId(), referee.getId());

        Resume gettedResume = resumeService.getById(savedResume.getId());

        assert gettedResume.getReferees().size() == 1;
    }
    @Test
    public void removeReferee() throws Exception{
        User user = creationUtils.createAllStuff();
        Resume resume = creationUtils.createResume();

        Resume savedResume = userService.addResume(user.getId(), resume);

        List<Referee> referees = new ArrayList<>();

        referees.addAll(user.getReferees());

        resumeService.addReferee(savedResume.getId(), referees.get(0).getId());
        resumeService.addReferee(savedResume.getId(), referees.get(1).getId());

        Resume gettedResume = resumeService.getById(savedResume.getId());

        assert gettedResume.getReferees().size() == 2;

        resumeService.removeReferee(savedResume.getId(), referees.get(1).getId());

        gettedResume = resumeService.getById(savedResume.getId());

        assert gettedResume.getReferees().size() == 1;
    }
    @Test
    public void addUserSkill() throws Exception{
        User user = creationUtils.createAllStuff();
        Resume resume = creationUtils.createResume();

        Resume savedResume = userService.addResume(user.getId(), resume);

        UserSkill userSkill = null;

        for (UserSkill us : user.getUserSkills()){
            userSkill = us;
            break;
        }

        resumeService.addUserSkill(savedResume.getId(), userSkill.getId());

        Resume gettedResume = resumeService.getById(savedResume.getId());

        assert gettedResume.getUserSkills().size() == 1;
    }
    @Test
    public void removeUserSkill() throws Exception{
        User user = creationUtils.createAllStuff();
        Resume resume = creationUtils.createResume();

        Resume savedResume = userService.addResume(user.getId(), resume);

        List<UserSkill> userSkills = new ArrayList<>();
        userSkills.addAll(user.getUserSkills());

        resumeService.addUserSkill(savedResume.getId(), userSkills.get(0).getId());
        resumeService.addUserSkill(savedResume.getId(), userSkills.get(1).getId());

        Resume gettedResume = resumeService.getById(savedResume.getId());

        assert gettedResume.getUserSkills().size() == 2;

        resumeService.removeUserSkill(savedResume.getId(), userSkills.get(1).getId());

        gettedResume = resumeService.getById(savedResume.getId());

        assert gettedResume.getUserSkills().size() == 1;
    }
    @Test
    public void changeDisplaySocialNetWork() throws Exception{
        User user = creationUtils.createAllStuff();
        Resume resume = creationUtils.createResume();
        //displaySocialNetwork = true

        Resume savedResume = userService.addResume(user.getId(), resume);

        assert savedResume.getDisplaySocialNetwork() == true;

        resumeService.changeDisplaySocialNetwork(savedResume.getId());

        savedResume = resumeService.getById(savedResume.getId());

        assert savedResume.getDisplaySocialNetwork() == false;

        resumeService.changeDisplaySocialNetwork(savedResume.getId());

        savedResume = resumeService.getById(savedResume.getId());

        assert savedResume.getDisplaySocialNetwork() == true;
    }
    @Test
    public void changeDisplayUserInformation() throws Exception{
        User user = creationUtils.createAllStuff();
        Resume resume = creationUtils.createResume();
        //displayUserInformation = true

        Resume savedResume = userService.addResume(user.getId(), resume);

        assert savedResume.getDisplayUserInformation() == true;

        resumeService.changeDisplayUserInformations(savedResume.getId());

        savedResume = resumeService.getById(savedResume.getId());

        assert savedResume.getDisplayUserInformation() == false;

        resumeService.changeDisplayUserInformations(savedResume.getId());

        savedResume = resumeService.getById(savedResume.getId());

        assert savedResume.getDisplayUserInformation() == true;
    }
    @Test
    public void deleteResume() throws Exception{
        User user = creationUtils.createAllStuff();
        Resume resume = creationUtils.createResume();

        Resume savedResume = userService.addResume(user.getId(), resume);

        List<Experience> experiences = new ArrayList<>();
        experiences.addAll(user.getExperiences());
        List<Hobby> hobbies = new ArrayList<>();
        hobbies.addAll(user.getHobbies());
        List<Referee> referees = new ArrayList<>();
        referees.addAll(user.getReferees());
        List<UserSkill> userSkills = new ArrayList<>();
        userSkills.addAll(user.getUserSkills());

        resumeService.addExperience(savedResume.getId(), experiences.get(0).getId());
        resumeService.addExperience(savedResume.getId(), experiences.get(1).getId());
        resumeService.addHobby(savedResume.getId(), hobbies.get(0).getId());
        resumeService.addHobby(savedResume.getId(), hobbies.get(1).getId());
        resumeService.addReferee(savedResume.getId(), referees.get(0).getId());
        resumeService.addReferee(savedResume.getId(), referees.get(1).getId());
        resumeService.addUserSkill(savedResume.getId(), userSkills.get(0).getId());
        resumeService.addUserSkill(savedResume.getId(), userSkills.get(1).getId());

        savedResume = resumeService.getById(savedResume.getId());

        User gettedUser = userService.getById(user.getId());

        assert gettedUser.getResumes().size() == 1;
        assert savedResume.getExperiences().size() == 2;
        assert savedResume.getHobbies().size() == 2;
        assert savedResume.getReferees().size() == 2;
        assert savedResume.getUserSkills().size() == 2;

        userService.removeResume(user.getId(), savedResume.getId());

        gettedUser = userService.getById(user.getId());

        assert gettedUser.getResumes().size() == 0;

    }
}
