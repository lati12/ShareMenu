package com.server.sharemenu.request;

import javax.validation.constraints.NotBlank;

/**
 * The class serves to transport Login information between client / server
 */
public class LoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
