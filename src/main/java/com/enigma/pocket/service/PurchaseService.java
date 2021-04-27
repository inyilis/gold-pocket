package com.enigma.pocket.service;

import com.enigma.pocket.entity.Purchase;

import java.util.List;

public interface PurchaseService {
    public Purchase purchase(Purchase purchase, String customerId, Integer purchaseType);

    public Purchase findPurchaseById(String id);

    public List<Purchase> findAllPurchase();
}
