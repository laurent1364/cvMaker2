package com.mirage.services;

import java.util.List;

/**
 * Created by Mirage on 24/02/2017.
 */
public interface CRUDService<T> {

    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);
}
