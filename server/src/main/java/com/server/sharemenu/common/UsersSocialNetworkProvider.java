package com.server.sharemenu.common;

import javax.persistence.*;

@Entity
@Table(name = "usersocialnetworkprovider")
public class UsersSocialNetworkProvider {
    @EmbeddedId
    UsersSocialNetworkProviderKey id;

    @ManyToOne
    @MapsId("usersId")
    @JoinColumn(name = "users_id")
    Users users;

    @ManyToOne
    @MapsId("socialNetworkProviderId")
    @JoinColumn(name = "socialnetworkprovider_id")
    SocialNetworkProvider socialNetworkProvider;


    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public SocialNetworkProvider getSocialNetworkProvider() {
        return socialNetworkProvider;
    }

    public void setSocialNetworkProvider(SocialNetworkProvider socialNetworkProvider) {
        this.socialNetworkProvider = socialNetworkProvider;
    }

}
