package com.codegym.controller;

import com.codegym.model.Country;
import com.codegym.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    ICountryService countryService;

    @GetMapping("/create")
    public String showFormCreate() {
        return "/country/create";
    }
    @GetMapping("")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/country/list");
        modelAndView.addObject("countries", countryService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public String saveNew(Country country) {
        countryService.save(country);
        return "redirect:/countries";
    }



}
