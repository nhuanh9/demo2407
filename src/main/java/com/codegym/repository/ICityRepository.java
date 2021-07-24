package com.codegym.repository;

import com.codegym.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends PagingAndSortingRepository<City, Long> {
    Page<City> findAll(Pageable pageable);
    @Query("select c from City c where c.name like ?1")
    Iterable<City> findAllByName(String name);
}
