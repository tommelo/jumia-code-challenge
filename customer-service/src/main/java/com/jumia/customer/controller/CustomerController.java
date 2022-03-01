package com.jumia.customer.controller;

import com.jumia.customer.controller.data.GetCustomersRequest;
import com.jumia.customer.controller.data.GetCustomersResponse;
import com.jumia.customer.converter.EntityConversionException;
import com.jumia.customer.converter.EntityConverter;
import com.jumia.customer.model.Customer;
import com.jumia.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<GetCustomersResponse>> getCustomers(@Valid GetCustomersRequest request) throws EntityConversionException {
        Boolean valid = Objects.isNull(request.getValid()) ? null : Boolean.valueOf(request.getValid());
        List<Customer> customers = customerService.findAllCustomers(request.getCountries(), valid);
        return ResponseEntity.ok(entityConverter.convert(customers));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ BindException.class })
    public Map<String, String> handleValidationException(BindException e) {
        Map<String, String> response = new HashMap<>();

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        for (ObjectError error : errors) {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            response.put(field, message);
        }

        return response;
    }

}