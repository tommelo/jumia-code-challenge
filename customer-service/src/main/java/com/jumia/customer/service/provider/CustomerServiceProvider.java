package com.jumia.customer.service.provider;

import com.jumia.customer.converter.EntityConversionException;
import com.jumia.customer.converter.EntityConverter;
import com.jumia.customer.data.CountryDataProvider;
import com.jumia.customer.entity.CustomerEntity;
import com.jumia.customer.model.Customer;
import com.jumia.customer.repository.CustomerRepository;
import com.jumia.customer.service.CustomerService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceProvider implements CustomerService {

    private CustomerRepository customerRepository;
    private CountryDataProvider countryDataProvider;
    private EntityConverter<CustomerEntity, Customer> entityConverter;

    public CustomerServiceProvider(CustomerRepository customerRepository,
                                   CountryDataProvider countryDataProvider,
                                   EntityConverter<CustomerEntity, Customer> entityConverter) {
        this.customerRepository = customerRepository;
        this.countryDataProvider = countryDataProvider;
        this.entityConverter = entityConverter;
    }

    @Override
    public List<Customer> findAllCustomers(Set<String> countries, Boolean valid) throws EntityConversionException {
        Set<String> dialCodes = countryDataProvider.findDialCodeByCountryName(countries);

        Specification<CustomerEntity> spec = CustomerRepository.phoneNumberContainingDialCode(dialCodes);
        List<CustomerEntity> entities = customerRepository.findAll(spec);

        return entityConverter
                .convert(entities)
                .stream()
                .filter(c -> matchCustomerByPhoneState(c, valid))
                .collect(Collectors.toList());
    }

    private boolean matchCustomerByPhoneState(Customer customer, Boolean valid) {
        return Objects.isNull(valid) || customer.getPhone().isValid() == valid;
    }

}