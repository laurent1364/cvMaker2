package com.mirage.domains.security;

import com.mirage.domains.utils.AbstractDomainClass;
import com.mirage.domains.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mirage on 24/02/2017.
 */
@Entity
public class Role extends AbstractDomainClass{

    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users = new ArrayList<>();



    /************************* CONSTRUCTOR *****************************************/

    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }

    /*****************************GETTER & SETTER ********************************/

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        if(!this.users.contains(user)){
            this.users.add(user);
        }

        if(!user.getRoles().contains(this)){
            user.getRoles().add(this);
        }
    }

    public void removeUser(User user){
        this.users.remove(user);
        user.getRoles().remove(this);

    }
}
