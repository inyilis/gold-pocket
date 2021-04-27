package com.enigma.pocket.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "t_purchase_details")
public class PurchaseDetails {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "purchase_detail_id")
    private String id;

    private BigDecimal price;
    private Double quantityInGram;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;
    @ManyToOne
    @JoinColumn(name = "pocket_id")
    private Pocket pocket;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getQuantityInGram() {
        return quantityInGram;
    }

    public void setQuantityInGram(Double quantityInGram) {
        this.quantityInGram = quantityInGram;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Pocket getPocket() {
        return pocket;
    }

    public void setPocket(Pocket pocket) {
        this.pocket = pocket;
    }
}
