package com.enigma.pocket.service;

import com.enigma.pocket.entity.Customer;
import com.enigma.pocket.entity.Pocket;
import com.enigma.pocket.repository.PocketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PocketServiceImpl implements PocketService{

    private final String pocketQtyMinus = "Pocket quantity is not enough!";

    @Autowired
    PocketRepository pocketRepository;

    @Autowired
    CustomerService customerService;

    @Override
    public Pocket findPocketById(String id) {
        return pocketRepository.findById(id).get();
    }

    @Override
    public List<Pocket> findPocketByCustomer(String customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        return customer.getPockets();
    }

    @Override
    public List<Pocket> findAllPocket() {
        return pocketRepository.findAll();
    }

    @Override
    public Pocket createPocket(Pocket pocket) {
        pocket.setPocketQty(0.0);
        return pocketRepository.save(pocket);
    }

    @Override
    public void topUp(Pocket pocket, Double qty) {
        pocket.setPocketQty(pocket.getPocketQty()+qty);
        pocketRepository.save(pocket);
    }

    @Override
    public void selling(Pocket pocket, Double qty) {
        validateQty(pocket.getPocketQty()-qty);
        pocket.setPocketQty(pocket.getPocketQty()-qty);
        pocketRepository.save(pocket);
    }

    @Override
    public Pocket updatePocket(Pocket pocket) {
        return pocketRepository.save(pocket);
    }

    @Override
    public void removePocket(String id) {
        pocketRepository.deleteById(id);
    }

    private void validateQty(Double checkQty) {
        if(!(checkQty >= 0)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, pocketQtyMinus);
        }
    }

}
