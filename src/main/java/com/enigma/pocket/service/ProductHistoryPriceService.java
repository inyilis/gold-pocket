package com.enigma.pocket.service;

import com.enigma.pocket.entity.ProductHistoryPrice;

import java.util.List;

public interface ProductHistoryPriceService {

    public ProductHistoryPrice logPrice(ProductHistoryPrice productHistoryPrice);
    public List<ProductHistoryPrice> findAllPrice();
    public List<ProductHistoryPrice> findAllByProduct(String productId);
}
