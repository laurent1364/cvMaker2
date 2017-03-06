package com.mirage.services;

import com.mirage.domains.Experience;
import com.mirage.domains.User;
import com.mirage.domains.utils.Address;
import com.mirage.utils.CreationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Mirage on 05/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AddressServiceTest {

    private AddressService addressService;
    private UserService userService;
    private CreationUtils creationUtils;


    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCreationUtils(CreationUtils creationUtils) {
        this.creationUtils = creationUtils;
    }

    @Test
    public void updateAddress() throws Exception{

        User user = creationUtils.createUser();
        Experience experience = userService.addExperience(user.getId(), creationUtils.createExperience());

        String streetAddress = "20 hay street";
        String addressComplement = "Oriental Bay";
        String city = "Wellington";
        String state = "Wellington";
        Integer zipCode = 6011;
        String country = "New Zealand";

        Address address = experience.getOrganisation().getAddress();

        address.setStreetAddress(streetAddress);
        address.setAddressComplement(addressComplement);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);
        address.setCountry(country);

        Address updatedAddress = addressService.saveOrUpdate(address);

        assert updatedAddress.getId() != null;
        assert updatedAddress.getId().equals(address.getId());
        assert updatedAddress.getVersion().equals(1);
        assert updatedAddress.getStreetAddress().equals(streetAddress);
        assert updatedAddress.getAddressComplement().equals(addressComplement);
        assert updatedAddress.getState().equals(state);
        assert updatedAddress.getCity().equals(city);
        assert updatedAddress.getCountry().equals(country);
        assert updatedAddress.getZipCode().equals(zipCode);
    }
}
