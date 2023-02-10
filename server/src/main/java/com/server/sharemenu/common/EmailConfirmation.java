package com.server.sharemenu.common;

import javax.persistence.*;

// Класът EmailConfirmation служи за консумиране и продуциране на информация за потвърждаването на имейл.
// Обекта служи и за операции със записа от базата данни.


@Entity
@Table(name = "emailconfirmation")
public class EmailConfirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean confirmed;

    @Column
    private String hash;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
