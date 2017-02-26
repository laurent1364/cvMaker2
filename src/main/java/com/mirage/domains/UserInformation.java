package com.mirage.domains;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Mirage on 24/02/2017.
 */
@Entity
public class UserInformation extends AbstractDomainClass{

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User user;

    private String firstName;
    private String lastName;
    private Date birthDate;
    private Boolean isMale;
    private String email;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;
    private String phoneNumber;

/*    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInformation", orphanRemoval = true)
    private List<SocialNetwork> socialNetworks;*/

    private String pictureUrl;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getMale() {
        return isMale;
    }

    public void setMale(Boolean male) {
        isMale = male;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

/*    public List<SocialNetwork> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(List<SocialNetwork> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }*/

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
