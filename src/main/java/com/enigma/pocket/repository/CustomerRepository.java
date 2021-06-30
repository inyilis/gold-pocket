package com.enigma.pocket.repository;

import com.enigma.pocket.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {

    @Query(value = "SELECT * FROM " +
            "m_customers as c " +
            "WHERE " +
            "c.email=:email AND c.password=:password", nativeQuery = true)
    Customer findCustomerLogin(String email, String password);
//    public List<Customer> findAllByFirstNameStartingWithAndEmailContaining(String firstName, String email, Pageable pageable);
}
