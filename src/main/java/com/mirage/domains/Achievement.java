package com.mirage.domains;

import com.mirage.domains.utils.AbstractDomainClass;

import javax.persistence.*;

/**
 * Created by Mirage on 28/02/2017.
 */
@Entity
@Table(name = "achievement")
public class Achievement extends AbstractDomainClass {

    private String achievement;
    private String result;

    @ManyToOne
    @JoinColumn(name = "experience_id", nullable = false, updatable = false)
    private Experience experience;




    /*******************CONSTRUCTOR ***************/

    public Achievement(String achievement) {
        this.achievement = achievement;
    }

    public Achievement() {
    }

    /************************ GETTER & SETTER **********************/

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }
}
