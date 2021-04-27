package com.enigma.pocket.service;

import com.enigma.pocket.dto.CustomerSearchDto;
import com.enigma.pocket.entity.Customer;
import com.enigma.pocket.exception.CustomerNotFoundException;
import com.enigma.pocket.repository.CustomerRepository;
import com.enigma.pocket.specification.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final String notFoundMessage = "Customer with id: %s not found!";

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer findCustomerById(String id) {
        validatePresent(id);
        Customer customer = customerRepository.findById(id).get();
        return customer;
    }

    @Override
    public Page<Customer> findCustomers(CustomerSearchDto customerSearchForm, Pageable pageable) {
        return customerRepository.findAll(CustomerSpecification.findCustomer(customerSearchForm), pageable);
    }

    @Override
    public void createCustomer(Customer customer) {
//        validatePresent(customer.getId());
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        validatePresent(customer.getId());
        customerRepository.save(customer);
    }

    @Override
        public void removeCustomer(String id) {
            validatePresent(id);
        customerRepository.deleteById(id);
    }

    private void validatePresent(String id) {
        if(!customerRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(notFoundMessage, id));
        }
    }
}
