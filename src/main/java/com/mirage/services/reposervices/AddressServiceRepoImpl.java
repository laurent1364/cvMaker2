package com.mirage.services.reposervices;

import com.mirage.domains.utils.Address;
import com.mirage.repositories.utils.AddressRepository;
import com.mirage.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mirage on 24/02/2017.
 */
@Service
public class AddressServiceRepoImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public Address getById(Integer id) {
        return addressRepository.findOne(id);
    }

    @Override
    public Address saveOrUpdate(Address domainObject) {
        return addressRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        addressRepository.delete(id);
    }
}
