package com.mirage.domains.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirage.domains.Organisation;
import com.mirage.domains.UserInformation;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mirage on 24/02/2017.
 */
@Entity
public class Address extends AbstractDomainClass {

    private String streetAddress;
    private String addressComplement;
    private String city;
    private String state;
    private Integer zipCode;
    private String country;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
    @JsonIgnore
    private List<UserInformation> userInformations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
    @JsonIgnore
    private List<Organisation> organisations;

    /*******************CONSTRUCTOR ***************/

    public Address(String streetAddress, String addressComplement, String city, String state, Integer zipCode, String country) {
        this.streetAddress = streetAddress;
        this.addressComplement = addressComplement;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Address() {
    }

    /************************ GETTER & SETTER ********************************/

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getAddressComplement() {
        return addressComplement;
    }

    public void setAddressComplement(String addressComplement) {
        this.addressComplement = addressComplement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<UserInformation> getUserInformations() {
        return userInformations;
    }

    public void setUserInformations(List<UserInformation> userInformations) {
        this.userInformations = userInformations;
    }

    public List<Organisation> getOrganisations() {
        return organisations;
    }

    public void setOrganisations(List<Organisation> organisations) {
        this.organisations = organisations;
    }
}
