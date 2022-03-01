package com.jumia.customer.service;

import com.jumia.customer.converter.EntityConversionException;
import com.jumia.customer.converter.EntityConverter;
import com.jumia.customer.data.CountryDataProvider;
import com.jumia.customer.entity.CustomerEntity;
import com.jumia.customer.model.Country;
import com.jumia.customer.model.Customer;
import com.jumia.customer.model.Phone;
import com.jumia.customer.repository.CustomerRepository;
import com.jumia.customer.service.provider.CustomerServiceProvider;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class CustomerServiceTests {

    @Test
    public void findAllCustomersShouldReturnEmptyListWhenNoCustomerIsFound() throws EntityConversionException {
        Set<String> countries = new HashSet<>(Arrays.asList("Portugal"));

        CustomerRepository customerRepository = mock(CustomerRepository.class);
        CountryDataProvider countryDataProvider = mock(CountryDataProvider.class);
        EntityConverter<CustomerEntity, Customer> entityConverter = mock(EntityConverter.class);

        when(countryDataProvider.findDialCodeByCountryName(countries)).thenReturn(new HashSet<>());
        when(customerRepository.findAll(any(Specification.class))).thenReturn(new ArrayList());
        when(entityConverter.convert(anyList())).thenReturn(new ArrayList<>());

        CustomerService service = new CustomerServiceProvider(customerRepository, countryDataProvider, entityConverter);
        List<Customer> customers = service.findAllCustomers(countries, null);

        ArgumentCaptor<Specification<CustomerEntity>> specificationArgumentCaptor = ArgumentCaptor.forClass(Specification.class);
        verify(customerRepository).findAll(specificationArgumentCaptor.capture());
        Specification<CustomerEntity> spec = specificationArgumentCaptor.getValue();

        assertTrue(customers.isEmpty());

        verify(countryDataProvider, times(1)).findDialCodeByCountryName(countries);
        verify(customerRepository, times(1)).findAll(spec);
        verify(entityConverter, times(1)).convert(anyList());
    }

    @Test
    public void findAllCustomersShouldReturnListOfCustomers() throws EntityConversionException {
        Set<String> countries = new HashSet<>(Arrays.asList("Cameroon", "Ethiopia"));

        CustomerRepository customerRepository = mock(CustomerRepository.class);
        CountryDataProvider countryDataProvider = mock(CountryDataProvider.class);
        EntityConverter<CustomerEntity, Customer> entityConverter = mock(EntityConverter.class);

        Set<String> dialCodes = new HashSet<>(Arrays.asList("237", "251"));
        List<CustomerEntity> entities = getCustomerEntities();
        List<Customer> customers = getCustomers();

        when(countryDataProvider.findDialCodeByCountryName(countries)).thenReturn(dialCodes);
        when(customerRepository.findAll(any(Specification.class))).thenReturn(entities);
        when(entityConverter.convert(entities)).thenReturn(customers);

        CustomerService service = new CustomerServiceProvider(customerRepository, countryDataProvider, entityConverter);
        List<Customer> result = service.findAllCustomers(countries, null);

        ArgumentCaptor<Specification<CustomerEntity>> specificationArgumentCaptor = ArgumentCaptor.forClass(Specification.class);
        verify(customerRepository).findAll(specificationArgumentCaptor.capture());
        Specification<CustomerEntity> spec = specificationArgumentCaptor.getValue();

        assertFalse(customers.isEmpty());
        assertThat(result).containsExactlyInAnyOrder(customers.get(0));

        verify(countryDataProvider, times(1)).findDialCodeByCountryName(countries);
        verify(customerRepository, times(1)).findAll(spec);
        verify(entityConverter, times(1)).convert(anyList());
    }

    private List<CustomerEntity> getCustomerEntities() {
        return Arrays.asList(new CustomerEntity(1, "Cameroon", "699209115"));
    }

    private List<Customer> getCustomers() {
        Country country = new Country("Cameroon", "237", "\\(237\\)\\ ?[2368]\\d{7,8}$");
        Phone phone = new Phone("(237) 699209115", country);
        Customer customer = new Customer(1, "Test", phone);
        return Arrays.asList(customer);
    }
}
