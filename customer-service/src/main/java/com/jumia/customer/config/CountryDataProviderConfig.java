package com.jumia.customer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.jumia.customer.data.CountryDataProvider;
import com.jumia.customer.data.provider.DefaultCountryDataProvider;
import com.jumia.customer.model.Country;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class CountryDataProviderConfig {

    private static final String COUNTRIES_FILE = "classpath:countries.json";

    private Resource resourceFile;

    public CountryDataProviderConfig(@Value(COUNTRIES_FILE) Resource resourceFile) {
        this.resourceFile = resourceFile;
    }

    @Bean
    public CountryDataProvider getCountryDataProvider() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule());

        File file = resourceFile.getFile();
        List<Country> countries = Arrays.asList(mapper.readValue(file, Country[].class));

        return new DefaultCountryDataProvider(countries);
    }

}