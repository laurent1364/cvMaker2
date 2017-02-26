package com.mirage.domains;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by Mirage on 24/02/2017.
 */
@Entity
public class SocialNetwork extends AbstractDomainClass{

/*    @OneToOne
    private SocialMedia socialMedia;*/
    private String url;

/*    @ManyToOne
    private UserInformation userInformation;*/

/*    public SocialMedia getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }*/

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

/*    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }*/
}
