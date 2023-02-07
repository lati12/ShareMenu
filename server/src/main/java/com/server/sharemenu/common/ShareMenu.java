package com.server.sharemenu.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "sharemenu")
public class ShareMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(length = 255)
    private Double documentStatus;

    @ManyToOne
    @JoinColumn(name = "entityheader_id", nullable = false)
    EntityHeader entityHeader;
    @ManyToOne
    @JoinColumn(name = "socialnetworkprovider_id", nullable = false)
    SocialNetworkProvider socialNetworkProvider;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "users_id",nullable = false)
    private User users;



    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(Double documentStatus) {
        this.documentStatus = documentStatus;
    }

    public EntityHeader getEntityHeader() {
        return entityHeader;
    }

    public void setEntityHeader(EntityHeader entityHeader) {
        this.entityHeader = entityHeader;
    }

    public SocialNetworkProvider getSocialNetworkProvider() {
        return socialNetworkProvider;
    }

    public void setSocialNetworkProvider(SocialNetworkProvider socialNetworkProvider) {
        this.socialNetworkProvider = socialNetworkProvider;
    }
}
