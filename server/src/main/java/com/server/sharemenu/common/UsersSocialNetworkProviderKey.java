package com.server.sharemenu.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

//Обектът е напревен с цел мапване с таблица, която има връзка Many to Many

@Embeddable
public class UsersSocialNetworkProviderKey implements Serializable {
    @Column(name = "users_id")
    Long usersId;

    @Column(name = "socialnetworkprovider_id")
    Long socialNetworkProviderId;

}
