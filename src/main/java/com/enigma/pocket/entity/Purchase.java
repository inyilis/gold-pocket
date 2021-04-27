package com.enigma.pocket.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_purchases")
public class Purchase {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "purchase_id")
    private String id;
    @CreatedDate
    private Date purchaseDate;
    private Integer purchaseType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("purchase")
    private Set<PurchaseDetails> purchaseDetails = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<PurchaseDetails> getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(Set<PurchaseDetails> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }

}
