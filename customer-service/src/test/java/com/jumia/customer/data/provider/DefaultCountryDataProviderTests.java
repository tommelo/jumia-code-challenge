package com.jumia.customer.data.provider;

import com.jumia.customer.data.CountryDataProvider;
import com.jumia.customer.model.Country;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class DefaultCountryDataProviderTests {

    @Test
    public void findDialCodeByCountryNameShouldReturnEmptySetWhenNoCountryIsProvided() {
        List<Country> countries = new ArrayList<>();
        CountryDataProvider provider = new DefaultCountryDataProvider(countries);
        Set<String> dialCodes = provider.findDialCodeByCountryName(null);
        assertTrue(dialCodes.isEmpty());
    }

    @Test
    public void findDialCodeByCountryNameShouldReturnEmptySetWhenCountryIsNotFound() {
        List<Country> countries = getCountries();
        CountryDataProvider provider = new DefaultCountryDataProvider(countries);
        Set<String> dialCodes = provider.findDialCodeByCountryName(new HashSet<>(Arrays.asList("invalidCountry")));
        assertTrue(dialCodes.isEmpty());
    }

    @Test
    public void findDialCodeByCountryNameShouldReturnDialCodes() {
        List<Country> countries = getCountries();
        Set<String> expectedCodes = countries.stream().map(c -> c.getDialCode()).collect(Collectors.toSet());

        CountryDataProvider provider = new DefaultCountryDataProvider(countries);
        Set<String> countryNames = countries.stream().map(c -> c.getName()).collect(Collectors.toSet());
        Set<String> dialCodes = provider.findDialCodeByCountryName(countryNames);

        assertThat(dialCodes).containsExactlyInAnyOrder(expectedCodes.toArray(new String[0]));
    }

    @Test
    public void findCountryByDialCodeShouldReturnEmptyWhenDialCodeIsNotFound() {
        List<Country> countries = getCountries();
        CountryDataProvider provider = new DefaultCountryDataProvider(countries);
        Optional<Country> optional = provider.findCountryByDialCode("9999999");
        assertFalse(optional.isPresent());
    }

    @Test
    public void findCountryByDialCodeShouldReturnCountry() {
        List<Country> countries = getCountries();
        Country country = countries.get(0);
        CountryDataProvider provider = new DefaultCountryDataProvider(countries);
        Optional<Country> optional = provider.findCountryByDialCode(country.getDialCode());
        assertTrue(optional.isPresent());
        assertEquals(optional.get(), country);
    }

    private List<Country> getCountries() {
        Country cameroon = new Country("Cameroon", "237", "\\(237\\)\\ ?[2368]\\d{7,8}$");
        Country ethiopia = new Country("Ethiopia", "251", "\\(251\\)\\ ?[1-59]\\d{8}$");
        return Arrays.asList(cameroon, ethiopia);
    }

}
