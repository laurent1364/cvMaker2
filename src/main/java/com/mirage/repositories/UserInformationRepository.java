package com.mirage.repositories;

import com.mirage.domains.UserInformation;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mirage on 24/02/2017.
 */
public interface UserInformationRepository extends CrudRepository<UserInformation, Integer> {
}
