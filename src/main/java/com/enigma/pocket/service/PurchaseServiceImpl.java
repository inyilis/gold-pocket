package com.enigma.pocket.service;

import com.enigma.pocket.entity.Customer;
import com.enigma.pocket.entity.Pocket;
import com.enigma.pocket.entity.Purchase;
import com.enigma.pocket.entity.PurchaseDetails;
import com.enigma.pocket.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    PocketService pocketService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Purchase purchase(Purchase purchase, String customerId, Integer purchaseType) {
        Customer customer = customerService.findCustomerById(customerId);
        purchase.setCustomer(customer);
        purchase.setPurchaseDate(new Date());
        purchase.setPurchaseType(purchaseType);

        BigDecimal total = BigDecimal.ZERO;

        if(purchaseType == 0){
            for (PurchaseDetails purchaseDetail: purchase.getPurchaseDetails()) {
                Pocket pocket = pocketService.findPocketById(purchaseDetail.getPocket().getId());
                pocketService.topUp(pocket, purchaseDetail.getQuantityInGram());
                purchaseDetail.setProduct(pocket.getProduct());
                purchaseDetail.setPrice(pocket.getProduct().getProductPriceSell());
                purchaseDetail.setPurchase(purchase);

                BigDecimal quantity = new BigDecimal(purchaseDetail.getQuantityInGram());
                total = total.add(purchaseDetail.getPrice().multiply(quantity));
            }

//            UriComponentsBuilder componentsBuilder = UriComponentsBuilder
//                    .fromHttpUrl("http://localhost:8888/debit")
//                    .queryParam("phoneNumber", customer.getPhoneNumber())
//                    .queryParam("amount", total);
//            System.out.println(componentsBuilder.toUriString());
//
//            restTemplate.exchange(componentsBuilder.toUriString(), HttpMethod.POST, null, String.class);

        }
        if(purchaseType == 1){
            for (PurchaseDetails purchaseDetail: purchase.getPurchaseDetails()) {
                Pocket pocket = pocketService.findPocketById(purchaseDetail.getPocket().getId());
                pocketService.selling(pocket, purchaseDetail.getQuantityInGram());
                purchaseDetail.setProduct(pocket.getProduct());
                purchaseDetail.setPrice(pocket.getProduct().getProductPriceBuy());
                purchaseDetail.setPurchase(purchase);
            }
        }
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase findPurchaseById(String id) {
        return purchaseRepository.findById(id).get();
    }

    @Override
    public List<Purchase> findAllPurchase() {
        return purchaseRepository.findAll();
    }
}
