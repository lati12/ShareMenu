package com.server.sharemenu.models;

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


    public SocialNetworkProvider getSocialNetworkProvider() {
        return socialNetworkProvider;
    }

    public void setSocialNetworkProvider(SocialNetworkProvider socialNetworkProvider) {
        this.socialNetworkProvider = socialNetworkProvider;
    }
}
