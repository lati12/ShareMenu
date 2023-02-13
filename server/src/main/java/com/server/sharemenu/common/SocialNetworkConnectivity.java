package com.server.sharemenu.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

// Класът SocialNetworkProvider служи за консумиране и продуциране на информация за социална мрежа.
// Обекта служи и за операции със записа от базата данни.

@Entity
@Table(name = "socialnetworkconnectivity")
public class SocialNetworkConnectivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255)
    private String name;

    @Column()
    private String accessToken;

    @Column
    private String key;

    @Column
    private String appId;

    @Column
    private String secretId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "users_id",nullable = false)
    private User users;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
