package com.server.sharemenu.models;

import javax.persistence.*;

@Entity
@Table(name = "enitiyline")
public class Entityline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private Double price;

    @Column(length = 255)
    private Double quantity;
    @ManyToOne
    @JoinColumn(name = "entityheader_id", nullable = false)
    private EntityHeader entityHeader;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    public EntityHeader getEntityHeader() {
        return entityHeader;
    }

    public void setEntityHeader(EntityHeader entityHeader) {
        this.entityHeader = entityHeader;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }


}
