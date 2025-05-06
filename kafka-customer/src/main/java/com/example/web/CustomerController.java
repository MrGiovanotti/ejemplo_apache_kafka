package com.example.web;

import com.example.entity.Customer;
import com.example.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  public Customer save(@RequestBody Customer customer) {
    return customerService.save(customer);
  }

}
