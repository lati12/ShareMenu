package com.server.sharemenu.common;

import javax.persistence.*;

// Класът UsersSocialNetworkProvider служи за консумиране и продуциране на информация за Потребителската социална мрежа.
// Обекта служи и за операции със записа от базата данни.


@Entity
@Table(name = "usersocialnetworkprovider")
public class UsersSocialNetworkProvider {
    @EmbeddedId
    UsersSocialNetworkProviderKey id;

    @ManyToOne
    @MapsId("usersId")
    @JoinColumn(name = "users_id")
    User users;

    @ManyToOne
    @MapsId("socialNetworkProviderId")
    @JoinColumn(name = "socialnetworkprovider_id")
    SocialNetworkProvider socialNetworkProvider;


    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public SocialNetworkProvider getSocialNetworkProvider() {
        return socialNetworkProvider;
    }

    public void setSocialNetworkProvider(SocialNetworkProvider socialNetworkProvider) {
        this.socialNetworkProvider = socialNetworkProvider;
    }

}
