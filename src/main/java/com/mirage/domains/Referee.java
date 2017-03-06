package com.mirage.domains;

import com.mirage.domains.utils.AbstractDomainClass;
import com.mirage.enums.Gender;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mirage on 01/03/2017.
 */
@Entity
@Table(name = "referee")
public class Referee extends AbstractDomainClass {

    private String firstName;
    private String lastName;
    private Gender gender;
    private String function;
    private String email;
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "resumes_referees",
            joinColumns = @JoinColumn(name = "referee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "resume_id", referencedColumnName = "id"))
    private List<Resume> resumes;

    /*******************CONSTRUCTOR ***************/

    public Referee() {
    }

    /*********************** GETTER & SETTER *********************/

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
