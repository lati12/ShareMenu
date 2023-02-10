package com.server.sharemenu.response.facebook;

//Класът се използва за транспортиране на данни между client - server
public class FacebookAccountsResponse {
    private String name;
    private String access_token;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
