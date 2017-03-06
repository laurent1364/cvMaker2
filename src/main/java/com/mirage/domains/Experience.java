package com.mirage.domains;

import com.mirage.domains.utils.AbstractDomainClass;
import com.mirage.domains.utils.Logo;
import com.mirage.enums.ExperienceType;
import com.mirage.enums.Gender;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Mirage on 28/02/2017.
 */
@Entity
@Table(name = "experience")
public class Experience extends AbstractDomainClass {

    private String title;
    private String location;
    private String description;
    private String honor;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private ExperienceType experienceType;

    @ManyToOne
    @JoinColumn(name = "logo_id")
    private Logo logo;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "experience")
    private Organisation organisation;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "experience", orphanRemoval = true)
    private Set<Achievement> achievements = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "resumes_experiences",
            joinColumns = @JoinColumn(name = "experience_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "resume_id", referencedColumnName = "id"))
    private List<Resume> resumes;

    /*******************CONSTRUCTOR ***************/

    public Experience(String title, String location, String description, String honor, ExperienceType experienceType, Logo logo, Organisation organisation) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.honor = honor;
        this.experienceType = experienceType;
        this.logo = logo;
        this.organisation = organisation;
    }

    public Experience() {
    }

    /******************************* GETTER & SETTER ***********************/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ExperienceType getExperienceType() {
        return experienceType;
    }

    public void setExperienceType(ExperienceType experienceType) {
        this.experienceType = experienceType;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(Set<Achievement> achievements) {
        this.achievements = achievements;
    }

    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }

    public void addAchievement(Achievement achievement) {
        if(!this.achievements.contains(achievement)){
            this.achievements.add(achievement);
        }

        if(achievement.getExperience() == null){
            achievement.setExperience(this);
        }
    }


}
