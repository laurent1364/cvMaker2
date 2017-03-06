package com.mirage.repositories;

import com.mirage.domains.Domain;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mirage on 02/03/2017.
 */
public interface DomainRepository extends CrudRepository<Domain, Integer> {
}
