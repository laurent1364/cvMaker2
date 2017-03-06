package com.mirage.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirage.domains.utils.AbstractDomainClass;
import com.mirage.domains.utils.Logo;
import javafx.beans.DefaultProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mirage on 01/03/2017.
 */
@Entity
@Table(name = "skill")
public class Skill extends AbstractDomainClass {

    private String title;
    private String description;
    private Boolean isPublic = false;

    @ManyToOne
    @JoinColumn(name = "logo_id")
    private Logo logo;

    @ManyToOne
    @JoinColumn(name = "domain_id", updatable = false, nullable = false)
    private Domain domain;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "skill")
    @JsonIgnore
    private List<UserSkill> userSkills;


    /*******************CONSTRUCTOR ***************/

    public Skill(String title, Logo logo, Boolean isPublic) {
        this.title = title;
        this.logo = logo;
        this.isPublic = isPublic;
    }

    public Skill(String title, Logo logo,String description) {
        this.title = title;
        this.logo = logo;
        this.description = description;
    }

    public Skill(String title, Logo logo, Boolean isPublic, Domain domain){
        this.title = title;
        this.logo = logo;
        this.isPublic = isPublic;
        this.domain = domain;
    }

    public Skill() {
    }

    /************************ GETTER & SETTER *************************/
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public List<UserSkill> getUserSkills() {
        return userSkills;
    }

    public void setUserSkills(List<UserSkill> userSkills) {
        this.userSkills = userSkills;
    }
}
