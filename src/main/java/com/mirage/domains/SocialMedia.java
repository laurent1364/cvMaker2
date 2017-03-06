package com.mirage.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirage.domains.utils.AbstractDomainClass;
import com.mirage.domains.utils.Logo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mirage on 24/02/2017.
 */
@Entity
@Table(name = "social_media")
public class SocialMedia extends AbstractDomainClass {

    private String name;

    @ManyToOne
    @JoinColumn(name = "logo_id", nullable = false)
    private Logo logo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "socialMedia", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SocialNetwork> socialNetworks = new ArrayList<>();

    /*******************CONSTRUCTOR ***************/

    public SocialMedia(String name, Logo logo) {
        this.name = name;
        this.logo = logo;
    }

    public SocialMedia(String name){
        this.name = name;
    }

    public SocialMedia() {
    }

    /************************ GETTER & SETTER **********************/

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

    public List<SocialNetwork> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(List<SocialNetwork> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }
}
