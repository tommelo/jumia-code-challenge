package com.jumia.customer.model;

public class Country {

    private String name;
    private String dialCode;
    private String phoneNumberPattern;

    public Country(String name, String dialCode, String phoneNumberPattern) {
        this.name = name;
        this.dialCode = dialCode;
        this.phoneNumberPattern = phoneNumberPattern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }

    public String getPhoneNumberPattern() {
        return phoneNumberPattern;
    }

    public void setPhoneNumberPattern(String phoneNumberPattern) {
        this.phoneNumberPattern = phoneNumberPattern;
    }

}