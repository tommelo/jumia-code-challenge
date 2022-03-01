package com.jumia.customer.service;

import com.jumia.customer.model.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    /**
     * Searches for registered customers.
     *
     * The searching rule will filter customers that fall into the following constraints:
     * <ul>
     *     <li>Returns all customers when no country name and no phone state is given</li>
     *     <li>When only country names are given, return customers that have a phone number matching the country dial code</li>
     *     <li>When only phone state is given, return customers that have a phone state matching the given state</li>
     *     <li>
     *         When country names and phone state are given, return customers that have a phone number matching
     *         the country dial code and phone state matching the given state.
     *     </li>
     * </ul>
     *
     * @param countries A set of country names to filter phone numbers by country
     * @param valid Boolean flag to filter valid or invalid phone numbers
     * @return customers The list of customers
     */
    List<Customer> findAllCustomers(Set<String> countries, Boolean valid);

}