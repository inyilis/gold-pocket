package com.enigma.pocket.controller;

import com.enigma.pocket.entity.Purchase;
import com.enigma.pocket.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseRestController {
    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/purchases")
    public List<Purchase> findAllPurchase(){
        return purchaseService.findAllPurchase();
    }

    @PostMapping("/purchase")
    public Purchase createPurchase(@RequestParam(name = "customerId") String customerId,
                                   @RequestParam(name = "purchaseType") Integer purchaseType,
                                   @RequestBody Purchase purchase){
        return purchaseService.purchase(purchase, customerId, purchaseType);
    }

}
