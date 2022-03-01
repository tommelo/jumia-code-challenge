package com.jumia.customer.data.provider;

import com.jumia.customer.data.CountryDataProvider;
import com.jumia.customer.model.Country;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultCountryDataProvider implements CountryDataProvider {

    private Map<String, Country> dialCodeToCountryMap = new HashMap<>();
    private Map<String, String> countryNameToDialCodeMap = new HashMap<>();

    public DefaultCountryDataProvider(List<Country> countries) {
        init(countries);
    }

    private void init(List<Country> countries) {
        for (Country country : countries) {
            dialCodeToCountryMap.put(country.getDialCode(), country);
            countryNameToDialCodeMap.put(country.getName(), country.getDialCode());
        }
    }

    @Override
    public Set<String> findDialCodeByCountryName(Set<String> countries) {
        return Objects.isNull(countries)
                ? new HashSet<>()
                : countries.stream()
                    .filter(c -> countryNameToDialCodeMap.containsKey(c))
                    .map(c -> countryNameToDialCodeMap.get(c))
                    .collect(Collectors.toSet());
    }

    @Override
    public Optional<Country> findCountryByDialCode(String dialCode) {
        return Optional.ofNullable(dialCodeToCountryMap.get(dialCode));
    }

}