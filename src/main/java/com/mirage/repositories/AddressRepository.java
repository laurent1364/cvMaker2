package com.mirage.repositories;

import com.mirage.domains.Address;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mirage on 24/02/2017.
 */
public interface AddressRepository extends CrudRepository<Address, Integer> {
}
