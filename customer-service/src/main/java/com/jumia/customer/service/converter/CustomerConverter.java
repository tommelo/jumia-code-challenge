package com.jumia.customer.service.converter;

import com.jumia.customer.converter.EntityConversionException;
import com.jumia.customer.converter.EntityConverter;
import com.jumia.customer.data.CountryDataProvider;
import com.jumia.customer.entity.CustomerEntity;
import com.jumia.customer.model.Country;
import com.jumia.customer.model.Customer;
import com.jumia.customer.model.Phone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomerConverter implements EntityConverter<CustomerEntity, Customer> {

    private final Pattern DIAL_CODE_EXTRACTOR_PATTERN = Pattern.compile("\\((.*?)\\)");

    private CountryDataProvider countryDataProvider;

    public CustomerConverter(CountryDataProvider countryDataProvider) {
        this.countryDataProvider = countryDataProvider;
    }

    @Override
    public Customer convert(CustomerEntity entity) throws EntityConversionException {
        String dialCode = extractDialCode(entity.getPhone());
        Country country = countryDataProvider.findCountryByDialCode(dialCode)
                .orElseThrow(() -> new EntityConversionException("Country not found"));
        Phone phone = new Phone(entity.getPhone(), country);
        return new Customer(entity.getId(), entity.getName(), phone);
    }

    @Override
    public List<Customer> convert(List<CustomerEntity> entities) throws EntityConversionException {
        List<Customer> customers = new ArrayList<>();
        for (CustomerEntity entity : entities)
            customers.add(convert(entity));
        return customers;
    }

    private String extractDialCode(String phone) throws EntityConversionException {
        Matcher matcher = DIAL_CODE_EXTRACTOR_PATTERN.matcher(phone);
        if (matcher.find())
            return matcher.group(1);
        throw new EntityConversionException("Invalid phone number pattern");
    }

}