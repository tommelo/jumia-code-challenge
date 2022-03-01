package com.jumia.customer.controller.data;

import javax.validation.constraints.Pattern;
import java.util.Set;

public class GetCustomersRequest {

    private Set<String> countries;

    @Pattern(regexp = "^true$|^false$", message = "Value must be true or false")
    private String valid;

    public Set<String> getCountries() {
        return countries;
    }

    public void setCountries(Set<String> countries) {
        this.countries = countries;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

}