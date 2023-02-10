package com.server.sharemenu.response;

import java.util.List;

//Класът се използва за транспортиране на данни между client - server

public class JwtResponse {
    private String jwt;
    private final String type = "Bearer";
    private Long id;
    private String email;
    private List<String> roles;

    private String name;
    private String lastName;

    public JwtResponse(String jwt, Long id, String email, List<String> roles, String name, String lastName) {
        this.jwt = jwt;
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
