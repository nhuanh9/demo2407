package com.codegym.service.city;

import com.codegym.model.City;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICityService extends IGeneralService<City> {
    Page<City> findAll(Pageable pageable);
    Iterable<City> findAllByName(String name);
    Iterable<City> findAllByCountryId(Long id);
}
