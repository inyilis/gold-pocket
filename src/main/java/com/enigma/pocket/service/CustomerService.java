package com.enigma.pocket.service;

import com.enigma.pocket.dto.CustomerSearchDto;
import com.enigma.pocket.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    public Customer findCustomerById(String id);
    public Page<Customer> findCustomers(CustomerSearchDto customerSearchForm, Pageable pageable);
    public void createCustomer(Customer customer);
    public void updateCustomer(Customer customer);
    public void removeCustomer(String id);
}
