package com.dev.inktown.repository;

import com.dev.inktown.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,String>{
     Optional<Customer> findByCustomerNameAndPhoneNo(String customerName, String phoneNo);
     Optional<Customer> findByUniqueUserId(String uniqueUserId);
}
