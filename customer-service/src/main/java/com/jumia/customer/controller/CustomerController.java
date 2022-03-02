package com.jumia.customer.controller;

import com.jumia.customer.controller.data.GetCustomersRequest;
import com.jumia.customer.controller.data.GetCustomersResponse;
import com.jumia.customer.converter.EntityConversionException;
import com.jumia.customer.converter.EntityConverter;
import com.jumia.customer.model.Customer;
import com.jumia.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;
    private EntityConverter<Customer, GetCustomersResponse> entityConverter;

    public CustomerController(CustomerService customerService, EntityConverter<Customer, GetCustomersResponse> entityConverter) {
        this.customerService = customerService;
        this.entityConverter = entityConverter;
    }

    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<GetCustomersResponse>> getCustomers(GetCustomersRequest request) throws EntityConversionException {
        List<Customer> customers = customerService.findAllCustomers(request.getCountries(), request.getValid());
        return ResponseEntity.ok(entityConverter.convert(customers));
    }

}