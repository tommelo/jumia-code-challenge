package com.jumia.customer.controller.data;

import java.util.Set;

public class GetCustomersRequest {

    private Set<String> countries;
    private Boolean valid;

    public Set<String> getCountries() {
        return countries;
    }

    public void setCountries(Set<String> countries) {
        this.countries = countries;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

}