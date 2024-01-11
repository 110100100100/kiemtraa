package com.example.kiemtra.service;

import com.example.kiemtra.model.City;

import java.util.List;

public interface ICustomerService {
    List<City> findAll();

    void save(City customer);

    City findById(Long id);

    void remove(int id);
}
