package com.mirage.domains;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * Created by Mirage on 24/02/2017.
 */
@Entity
public class SocialMedia extends AbstractDomainClass{

    private String name;
    private String logoName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }
}
