package com.enigma.pocket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "m_history_prices")
public class ProductHistoryPrice {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private Date historyDate;
    private BigDecimal priceBuy;
    private BigDecimal priceSell;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"productHistoryPrices"})
    private Product product;

    public ProductHistoryPrice() {
    }

    public ProductHistoryPrice(Product product) {
        this.historyDate = product.getUpdatedAt();
        this.priceBuy = product.getProductPriceBuy();
        this.priceSell = product.getProductPriceSell();
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(Date historyDate) {
        this.historyDate = historyDate;
    }

    public BigDecimal getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(BigDecimal priceBuy) {
        this.priceBuy = priceBuy;
    }

    public BigDecimal getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(BigDecimal priceSell) {
        this.priceSell = priceSell;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductHistoryPrice{" +
                "id='" + id + '\'' +
                ", historyDate=" + historyDate +
                ", priceBuy=" + priceBuy +
                ", priceSell=" + priceSell +
                ", product=" + product +
                '}';
    }
}
