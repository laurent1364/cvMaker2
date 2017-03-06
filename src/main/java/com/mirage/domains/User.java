package com.mirage.domains;

import com.mirage.domains.security.Role;
import com.mirage.domains.utils.AbstractDomainClass;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Mirage on 24/02/2017.
 */
@Entity
@Table(name = "user")
public class User extends AbstractDomainClass {

    private String username;

    @Transient
    private String password;

    private String encryptedPassword;
    private Boolean enabled = true;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @PrimaryKeyJoinColumn
    private UserInformation userInformation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Set<Experience> experiences = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Set<UserSkill> userSkills = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Set<Hobby> hobbies = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Set<Referee> referees = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Set<Resume> resumes = new HashSet<>();

    /*******************CONSTRUCTOR ***************/

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    /************************ GETTER & SETTER *************************/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
        userInformation.setUser(this);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }

    public Set<UserSkill> getUserSkills() {
        return userSkills;
    }

    public void setUserSkills(Set<UserSkill> userSkills) {
        this.userSkills = userSkills;
    }

    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public Set<Referee> getReferees() {
        return referees;
    }

    public void setReferees(Set<Referee> referees) {
        this.referees = referees;
    }

    public Set<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(Set<Resume> resumes) {
        this.resumes = resumes;
    }

    public void addRole(Role role){
        if(!this.roles.contains(role)){
            this.roles.add(role);
        }

        if(!role.getUsers().contains(this)){
            role.getUsers().add(this);
        }
    }

    public void removeRole(Role role){
        int i = 0;
        for(Role r : this.roles){

            if(r.getId() == role.getId()){
                this.roles.remove(i);
                break;
            }
            i++;
        }
        i = 0;
        for (User u : role.getUsers()){
            if (u.getId() == this.getId()){
                role.getUsers().remove(i);
                break;
            }
            i++;
        }
    }


    public void addHobby(Hobby hobby) {
        if(!this.hobbies.contains(hobby)){
            this.hobbies.add(hobby);
        }

        if(hobby.getUser() == null){
            hobby.setUser(this);
        }
    }

    public void addExperience(Experience experience) {
        if(!this.experiences.contains(experience)){
            this.experiences.add(experience);
        }

        if(experience.getUser() == null){
            experience.setUser(this);
        }
    }

    public void addReferee(Referee referee) {
        if(!this.referees.contains(referee)){
            this.referees.add(referee);
        }

        if(referee.getUser() == null){
            referee.setUser(this);
        }
    }

    public void addResume(Resume resume) {
        if(!this.resumes.contains(resume)){
            this.resumes.add(resume);
        }

        if(resume.getUser() == null){
            resume.setUser(this);
        }
    }
}
