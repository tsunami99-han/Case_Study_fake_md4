package com.codegym.servie;

import org.springframework.stereotype.Service;

import java.util.Optional;


public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void deleteById(Long id);
}
