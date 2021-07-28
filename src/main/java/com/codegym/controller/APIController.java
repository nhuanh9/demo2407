package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.service.city.ICityService;
import com.codegym.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    ICityService cityService;
    @Autowired
    ICountryService countryService;

    @GetMapping("/cities")
    public ResponseEntity<Iterable<City>> findAllApi() {
        Iterable<City> cities = cityService.findAll();
        return new ResponseEntity(cities, HttpStatus.OK);
    }

    @PostMapping("/cities")
    public ResponseEntity<Void> create(@RequestBody City city) {
        cityService.save(city);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<City> findOne(@PathVariable Long id) {
        return new ResponseEntity(cityService.findById(id).get(), HttpStatus.OK);

    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<Void> create(@PathVariable Long id,@RequestBody City city) {
        Optional<City> lastCity = cityService.findById(id);
        if (!lastCity.isPresent()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        city.setId(id);
        cityService.save(city);
        return new ResponseEntity(HttpStatus.OK);
    }
}
