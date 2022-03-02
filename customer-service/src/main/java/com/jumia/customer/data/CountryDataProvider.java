package com.jumia.customer.data;

import com.jumia.customer.model.Country;

import java.util.Optional;
import java.util.Set;

/**
 * Country data provider interface.
 *
 * Provides helper methods to translate country names
 * into dial codes and dial codes into {@code Country} objects.
 */
public interface CountryDataProvider {

    /**
     * Translates country names into dial codes.
     *
     * Given a set of country names:
     * <pre>{@code
     * ["Cameroon", "Ethiopia"]
     * }</pre>
     * The method will convert it into a set of dial codes:
     * <pre>{@code
     * ["237", "251"]
     * }</pre>
     *
     * @param countries A set of country names
     * @return dialCodes A set of dial codes
     */
    Set<String> findDialCodeByCountryName(Set<String> countries);

    /**
     * Searches for a country based on the given dial code.
     * Returns a {@code Country} object if the dial code is found.
     *
     * @param dialCode The country dial code
     * @return country The country object
     */
    Optional<Country> findCountryByDialCode(String dialCode);

    /**
     * Returns a set of country names.
     *
     * @return countryNames Set of country names
     */
    Set<String> getCountryNames();

}