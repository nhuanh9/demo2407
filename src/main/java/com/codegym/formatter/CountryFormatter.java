package com.codegym.formatter;

import com.codegym.model.Country;
import com.codegym.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class CountryFormatter implements Formatter<Country> {

    ICountryService countryService;

    @Autowired
    public CountryFormatter(ICountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Country parse(String text, Locale locale) throws ParseException {
        return this.countryService.findById(Long.valueOf(text)).get();
    }

    @Override
    public String print(Country object, Locale locale) {
        return null;
    }
}
