package com.mirage.repositories;

import com.mirage.domains.Organisation;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mirage on 02/03/2017.
 */
public interface OrganisationRepository extends CrudRepository<Organisation, Integer> {
}
