package com.server.sharemenu.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


/**
 * The EntityLine class serves to consume and produce line information.
 * The object is also used for database write operations.
 */
@Entity
@Table(name = "entityline")
public class EntityLine {
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
