package com.mirage.domains.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirage.domains.Experience;
import com.mirage.domains.Hobby;
import com.mirage.domains.Domain;
import com.mirage.domains.Skill;
import com.mirage.domains.SocialMedia;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mirage on 01/03/2017.
 */
@Entity
@Table(name = "logo")
public class Logo extends AbstractDomainClass {

    private String name;
    private String cssClass;
    private String hexaCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "logo")
    @JsonIgnore
    private List<Experience> experiences;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "logo")
    @JsonIgnore
    private List<Hobby> hobbies;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "logo")
    @JsonIgnore
    private List<Skill> skills;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "logo")
    @JsonIgnore
    private List<Domain> domains;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "logo")
    @JsonIgnore
    private List<SocialMedia> socialMedias;

    /*******************CONSTRUCTOR ***************/

    public Logo(String name, String cssClass, String hexaCode) {
        this.name = name;
        this.cssClass = cssClass;
        this.hexaCode = hexaCode;
    }

    public Logo() {
    }

    /************************ GETTER & SETTER **********************/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getHexaCode() {
        return hexaCode;
    }

    public void setHexaCode(String hexaCode) {
        this.hexaCode = hexaCode;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    public List<SocialMedia> getSocialMedias() {
        return socialMedias;
    }

    public void setSocialMedias(List<SocialMedia> socialMedias) {
        this.socialMedias = socialMedias;
    }
}
