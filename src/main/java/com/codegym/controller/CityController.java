package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.service.city.ICityService;
import com.codegym.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Component
@RequestMapping("/cities")
public class CityController {

    @Autowired
    ICityService cityService;
    @Autowired
    ICountryService countryService;

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("countries", countryService.findAll()); // ném cho view 1 list country để tý còn làm xe nếch óp xừn
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView showList(@PageableDefault(size = 6) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("cities", cityService.findAll(pageable));
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView showOne(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/city/view");
        modelAndView.addObject("city", cityService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(City city, @RequestParam MultipartFile file, BindingResult result) {
        System.out.println(result);
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(),
                    new File("/Users/daonhuanh/Downloads/Codegym/nal/" + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        city.setImage(fileName);
        cityService.save(city);
        return "redirect:/cities";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showFormEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/city/edit");
        modelAndView.addObject("countries", countryService.findAll());
        modelAndView.addObject("city", cityService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public String saveEdit(@PathVariable Long id, City city, @RequestParam MultipartFile file, BindingResult result) {
        System.out.println(result);
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(),
                    new File("/Users/daonhuanh/Downloads/Codegym/nal/" + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        city.setId(id);
        if (fileName.equals("") == false) {
            city.setImage(fileName);
        } else {
            city.setImage(cityService.findById(id).get().getImage());
        }
        cityService.save(city);
        return "redirect:/cities";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        cityService.delete(id);
        return "redirect:/cities";
    }

    @GetMapping("/search")
    public ModelAndView findByName(String name) {
        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("cities", cityService.findAllByName("%" + name + "%") );
        return modelAndView;
    }

}
