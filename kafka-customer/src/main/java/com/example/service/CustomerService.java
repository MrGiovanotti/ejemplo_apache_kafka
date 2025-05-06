package com.example.service;

import com.example.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

  private  final CustomerEventsService customerEventsService;

  public Customer save(Customer customer) {
    log.info("***** Received ***** {}", customer);
    customerEventsService.publish(customer);
    return customer;
  }

}
