package com.demo.domain;

import java.util.List;

import javax.persistence.*;

@Entity
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Boolean isActive = true;


    private String name;

    @ManyToMany
    private List<User> users;


    public Boolean getIsActive() {
        return isActive;
    }


    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }


    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
