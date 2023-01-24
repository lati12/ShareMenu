package com.server.sharemenu.common;

import javax.persistence.*;

@Entity
@Table(name = "socialnetworkprovider")
public class SocialNetworkProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String accessToken;

    @Column(length = 255)
    private String refreshToken;

    @Column(length = 255)
    private String link;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String acessToken) {
        this.accessToken = acessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
