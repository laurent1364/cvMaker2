package com.mirage.domains;

import com.mirage.domains.utils.AbstractDomainClass;
import com.mirage.domains.utils.Address;

import javax.persistence.*;

/**
 * Created by Mirage on 28/02/2017.
 */
@Entity
@Table(name = "organisation")
public class Organisation extends AbstractDomainClass {

    private String name;
    private String website;
    private String organisationLogoUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "experience_id")
    private Experience experience;

    /*******************CONSTRUCTOR ***************/

    public Organisation(String name, String website, String organisationLogoUrl, Address address) {
        this.name = name;
        this.website = website;
        this.organisationLogoUrl = organisationLogoUrl;
        this.address = address;
    }

    public Organisation() {
    }

    /************************ GETTER & SETTER **********************/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOrganisationLogoUrl() {
        return organisationLogoUrl;
    }

    public void setOrganisationLogoUrl(String organisationLogoUrl) {
        this.organisationLogoUrl = organisationLogoUrl;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }
}
