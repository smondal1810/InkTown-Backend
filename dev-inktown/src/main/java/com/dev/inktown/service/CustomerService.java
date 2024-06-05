package com.dev.inktown.service;

import com.dev.inktown.entity.Customer;
import com.dev.inktown.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer getCustomerById(String customerId){
        Optional<Customer> result = customerRepository.findById(customerId);
        return result.orElseGet(Customer::new);
    }
    public Customer createCustomer(Customer newCustomer){

/*
        String customerName = newCustomer.getCustomerName();
        String customerPhn = newCustomer.getPhoneNo();
        Optional<Customer> optionalFindResult = customerRepository.findByCustomerNameAndPhoneNo(customerName,customerPhn);
*/
        String uniqueUserId = newCustomer.getUniqueUserId();
        Optional<Customer> optionalFindResult = customerRepository.findByUniqueUserId(uniqueUserId);

        return optionalFindResult.orElseGet(() -> customerRepository.save(newCustomer));
    }

}
