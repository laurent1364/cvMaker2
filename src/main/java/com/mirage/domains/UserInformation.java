package com.mirage.domains;

import com.mirage.domains.utils.Address;
import com.mirage.domains.utils.AbstractDomainClass;
import com.mirage.enums.Gender;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Mirage on 24/02/2017.
 */
@Entity
@Table(name = "user_information")
public class UserInformation extends AbstractDomainClass {

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @PrimaryKeyJoinColumn
    private User user;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    private String email;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Boolean hasDrivingLicense;
    private String profilePictureUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "userInformation", orphanRemoval = true)
    private Set<SocialNetwork> socialNetworks = new HashSet<>();


    public UserInformation(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /*******************CONSTRUCTOR ***************/



    public UserInformation() {
    }

    /************************ GETTER & SETTER **********************/
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Boolean getHasDrivingLicense() {
        return hasDrivingLicense;
    }

    public void setHasDrivingLicense(Boolean hasDrivingLicense) {
        this.hasDrivingLicense = hasDrivingLicense;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<SocialNetwork> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(Set<SocialNetwork> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }

    public SocialNetwork addSocialNetwork(SocialNetwork socialNetwork) {
        this.socialNetworks.add(socialNetwork);
        socialNetwork.setUserInformation(this);
        return socialNetwork;
    }
}
