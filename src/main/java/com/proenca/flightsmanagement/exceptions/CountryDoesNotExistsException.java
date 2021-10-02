package com.proenca.flightsmanagement.exceptions;

public class CountryDoesNotExistsException extends RuntimeException {

    private String countryCode;

    public CountryDoesNotExistsException(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
