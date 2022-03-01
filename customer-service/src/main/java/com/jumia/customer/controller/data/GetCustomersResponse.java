package com.jumia.customer.controller.data;

public class GetCustomersResponse {

    private String number;
    private String country;
    private String dialCode;
    private boolean valid;

    public GetCustomersResponse(String number, String country, String dialCode, boolean valid) {
        this.number = number;
        this.country = country;
        this.dialCode = dialCode;
        this.valid = valid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}