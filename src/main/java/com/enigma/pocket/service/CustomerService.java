package com.enigma.pocket.service;

import com.enigma.pocket.dto.CustomerLoginDto;
import com.enigma.pocket.dto.CustomerSearchDto;
import com.enigma.pocket.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    public Customer findCustomerById(String id);
    public Page<Customer> findCustomers(CustomerSearchDto customerSearchForm, Pageable pageable);
    public Customer customerLogin(CustomerLoginDto customerLoginDto);
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public void removeCustomer(String id);
}
