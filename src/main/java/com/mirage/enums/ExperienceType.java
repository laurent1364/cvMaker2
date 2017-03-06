package com.mirage.enums;

import javax.persistence.Entity;

/**
 * Created by Mirage on 01/03/2017.
 */
public enum ExperienceType{

    EXPERIENCE("Experience"),
    EDUCATION("Education");

    private String value;

    ExperienceType(String value) {
        this.value = value;
    }
}
