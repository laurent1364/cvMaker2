package com.mirage.services;

import com.mirage.domains.Hobby;

/**
 * Created by Mirage on 02/03/2017.
 */
public interface HobbyService extends CRUDService<Hobby> {
    Hobby addHobby(Integer userId, Hobby hobby);
}
