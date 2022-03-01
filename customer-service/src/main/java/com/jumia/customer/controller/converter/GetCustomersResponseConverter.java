package com.jumia.customer.controller.converter;

import com.jumia.customer.controller.data.GetCustomersResponse;
import com.jumia.customer.converter.EntityConversionException;
import com.jumia.customer.converter.EntityConverter;
import com.jumia.customer.model.Country;
import com.jumia.customer.model.Customer;
import com.jumia.customer.model.Phone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetCustomersResponseConverter implements EntityConverter<Customer, GetCustomersResponse> {

    @Override
    public GetCustomersResponse convert(Customer entity) throws EntityConversionException {
        Phone phone = entity.getPhone();
        Country country = phone.getCountry();
        return new GetCustomersResponse(phone.getNumber(),
                country.getName(),
                country.getDialCode(),
                phone.isValid());
    }

    @Override
    public List<GetCustomersResponse> convert(List<Customer> entities) throws EntityConversionException {
        List<GetCustomersResponse> list = new ArrayList<>();
        for (Customer customer : entities)
            list.add(convert(customer));
        return list;
    }

}