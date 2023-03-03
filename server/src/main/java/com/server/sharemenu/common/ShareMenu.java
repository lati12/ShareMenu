package com.server.sharemenu.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


/**
 * The Sharemenu class serves to consume and produce information for its sharing and generation.
 * The object is also used for database write operations.
 */

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
    @JoinColumn(name = "socialnetworkconnectivity_id", nullable = false)
    SocialNetworkConnectivity socialNetworkConnectivity;

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

    public SocialNetworkConnectivity getSocialNetworkConnectivity() {
        return socialNetworkConnectivity;
    }

    public void setSocialNetworkConnectivity(SocialNetworkConnectivity socialNetworkConnectivity) {
        this.socialNetworkConnectivity = socialNetworkConnectivity;
    }
}
