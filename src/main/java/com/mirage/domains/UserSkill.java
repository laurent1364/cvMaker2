package com.mirage.domains;

import com.mirage.domains.utils.AbstractDomainClass;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mirage on 01/03/2017.
 */
@Entity
@Table(name = "user_skill")
public class UserSkill extends AbstractDomainClass {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false, updatable = false)
    private Skill skill;

    private Integer mark;

    @ManyToMany
    @JoinTable(
            name = "resumes_user_skills",
            joinColumns = @JoinColumn(name = "user_skill_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "resume_id", referencedColumnName = "id"))
    private List<Resume> resume;

    public UserSkill(User user, Skill skill, Integer mark) {
        this.user = user;
        this.skill = skill;
        this.mark = mark;
    }

    /*******************CONSTRUCTOR ***************/



    public UserSkill() {
    }

    /*************** GETTER & SETTER *******************************/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public List<Resume> getResume() {
        return resume;
    }

    public void setResume(List<Resume> resume) {
        this.resume = resume;
    }
}

