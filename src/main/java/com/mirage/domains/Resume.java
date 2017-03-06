package com.mirage.domains;

import com.mirage.domains.utils.AbstractDomainClass;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Mirage on 01/03/2017.
 */
@Entity
@Table(name = "resume")
public class Resume extends AbstractDomainClass {

    private String title;
    private String introduction;
    private String jobSeek;
    private String hobbyDescription;
    private Boolean displaySocialNetwork;
    private Boolean displayUserInformation;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "resumes_hobbies",
            joinColumns = @JoinColumn(name = "resume_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "hobby_id", referencedColumnName = "id"))
    private Set<Hobby> hobbies;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "resumes_experiences",
            joinColumns = @JoinColumn(name = "resume_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "experience_id", referencedColumnName = "id"))
    @OrderBy("endDate DESC")
    private Set<Experience> experiences;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "resumes_user_skills",
            joinColumns = @JoinColumn(name = "resume_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_skill_id", referencedColumnName = "id"))
    private Set<UserSkill> userSkills;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "resumes_referees",
            joinColumns = @JoinColumn(name = "resume_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "referee_id", referencedColumnName = "id"))
    private Set<Referee> referees;


    /*******************CONSTRUCTOR ***************/

    public Resume() {
    }

    /**************** GETTER & SETTER *************/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getJobSeek() {
        return jobSeek;
    }

    public void setJobSeek(String jobSeek) {
        this.jobSeek = jobSeek;
    }

    public String getHobbyDescription() {
        return hobbyDescription;
    }

    public void setHobbyDescription(String hobbyDescription) {
        this.hobbyDescription = hobbyDescription;
    }

    public Boolean getDisplaySocialNetwork() {
        return displaySocialNetwork;
    }

    public void setDisplaySocialNetwork(Boolean displaySocialNetwork) {
        this.displaySocialNetwork = displaySocialNetwork;
    }

    public Boolean getDisplayUserInformation() {
        return displayUserInformation;
    }

    public void setDisplayUserInformation(Boolean displayUserInformation) {
        this.displayUserInformation = displayUserInformation;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }

    public Set<UserSkill> getUserSkills() {
        return userSkills;
    }

    public void setUserSkills(Set<UserSkill> userSkills) {
        this.userSkills = userSkills;
    }

    public Set<Referee> getReferees() {
        return referees;
    }

    public void setReferees(Set<Referee> referees) {
        this.referees = referees;
    }

    public void addExperience(Experience experience) {
        if(!this.experiences.contains(experience)){
            this.experiences.add(experience);
        }
    }

    public void addHobby(Hobby hobby) {
        if(!this.hobbies.contains(hobby)){
            this.hobbies.add(hobby);
        }
    }


    public void addReferee(Referee referee) {
        if(!this.referees.contains(referee)){
            this.referees.add(referee);
        }
    }

    public void addUserSkill(UserSkill userSkill) {
        if(!this.userSkills.contains(userSkill)){
            this.userSkills.add(userSkill);
        }
    }
}
