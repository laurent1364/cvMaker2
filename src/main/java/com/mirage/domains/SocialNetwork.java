package com.mirage.domains;

import com.mirage.domains.utils.AbstractDomainClass;

import javax.persistence.*;

/**
 * Created by Mirage on 24/02/2017.
 */
@Entity
@Table(name = "social_network")
public class SocialNetwork extends AbstractDomainClass {

    private String linkAddress;

    @OneToOne
    @JoinColumn(name = "user_information_id", nullable = false, updatable = false)
    private UserInformation userInformation;

    @ManyToOne
    @JoinColumn(name = "social_media_id", nullable = false)
    private SocialMedia socialMedia;

    /*******************CONSTRUCTOR ***************/


    public SocialNetwork(String linkAddress, UserInformation userInformation, SocialMedia socialMedia) {
        this.linkAddress = linkAddress;
        this.userInformation = userInformation;
        this.socialMedia = socialMedia;
    }

    public SocialNetwork() {
    }

    /************************ GETTER & SETTER **********************/

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }
}
