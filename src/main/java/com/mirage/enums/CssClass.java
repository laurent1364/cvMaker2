package com.mirage.enums;

/**
 * Created by Mirage on 02/03/2017.
 */
public enum CssClass {
    PRIMARY("primary"),
    SUCCESS("success"),
    WARNING("warning"),
    DANGER("danger"),
    INFO("info"),
    DEFAULT("default");

    private String value;

    CssClass(String value) {
        this.value = value;
    }
}
