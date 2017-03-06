package com.mirage.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirage.domains.utils.AbstractDomainClass;
import com.mirage.domains.utils.Logo;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mirage on 28/02/2017.
 */
@Entity
@Table(name = "domain")
public class Domain extends AbstractDomainClass {

    private String title;

    @ManyToOne
    @JoinColumn(name = "logo_id", nullable = false)
    private Logo logo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "domain")
    @JsonIgnore
    private List<Skill> skills;


    /*******************CONSTRUCTOR ***************/

    public Domain(String title, Logo logo) {
        this.title = title;
        this.logo = logo;
    }

    public Domain(String title){
        this.title = title;
    }

    public Domain() {
    }

    /************************ GETTER & SETTER *************************/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
