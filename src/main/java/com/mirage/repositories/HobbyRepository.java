package com.mirage.repositories;

import com.mirage.domains.Hobby;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mirage on 02/03/2017.
 */
public interface HobbyRepository extends CrudRepository<Hobby, Integer> {
}
