package com.enigma.pocket.service;

import com.enigma.pocket.entity.Customer;
import com.enigma.pocket.entity.Pocket;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface PocketService {

    public Pocket findPocketById(String id);
    public List<Pocket> findAllPocket();
    public List<Pocket> findPocketByCustomer(String customerId);
    public Pocket createPocket(Pocket pocket);
    public void updatePocket(Customer customer);
    public void removePocket(String id);
    void topUp(Pocket pocket, Double qty);
    void selling(Pocket pocket, Double qty);
}
