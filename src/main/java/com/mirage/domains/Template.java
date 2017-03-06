package com.mirage.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirage.domains.utils.AbstractDomainClass;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mirage on 01/03/2017.
 */
@Entity
@Table(name = "template")
public class Template extends AbstractDomainClass{

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "template")
    @JsonIgnore
    private List<Resume> resumes;

    /*******************CONSTRUCTOR ***************/


    public Template() {
    }

    /************** GETTER & SETTER *************/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }
}
