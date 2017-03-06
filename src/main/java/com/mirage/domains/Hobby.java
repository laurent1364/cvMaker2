package com.mirage.domains;

import com.mirage.domains.User;
import com.mirage.domains.utils.AbstractDomainClass;
import com.mirage.domains.utils.Logo;
import com.mirage.enums.CssClass;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mirage on 01/03/2017.
 */
@Entity
@Table(name = "hobby")
public class Hobby extends AbstractDomainClass {

    private String name;

    @ManyToOne
    @JoinColumn(name = "logo_id", nullable = false)
    private Logo logo;

    @Enumerated(EnumType.STRING)
    private CssClass cssClass;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "resumes_hobbies",
            joinColumns = @JoinColumn(name = "hobby_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "resume_id", referencedColumnName = "id"))
    private List<Resume> resumes;

    /*******************CONSTRUCTOR ***************/


    public Hobby(String name, Logo logo, CssClass cssClass) {
        this.name = name;
        this.logo = logo;
        this.cssClass = cssClass;
    }

    public Hobby() {
    }

    /*********************** GETTER & SETTER *********************/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public CssClass getCssClass() {
        return cssClass;
    }

    public void setCssClass(CssClass cssClass) {
        this.cssClass = cssClass;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }
}
