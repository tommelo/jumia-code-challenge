package com.jumia.customer.controller;

import com.jumia.customer.data.CountryDataProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private CountryDataProvider countryDataProvider;

    public CountryController(CountryDataProvider countryDataProvider) {
        this.countryDataProvider = countryDataProvider;
    }

    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<Set<String>> getCountryNames() {
        return ResponseEntity.ok(countryDataProvider.getCountryNames());
    }

}