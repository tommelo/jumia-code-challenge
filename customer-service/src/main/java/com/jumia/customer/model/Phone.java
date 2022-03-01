package com.jumia.customer.model;

public class Phone {

    private String number;
    private Country country;
    private boolean valid;

    public Phone(String number, Country country, boolean valid) {
        this.number = number;
        this.country = country;
        this.valid = valid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}