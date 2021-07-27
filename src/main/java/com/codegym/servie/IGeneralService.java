package com.codegym.servie;

<<<<<<< HEAD
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface IGeneralService<T> {
=======
import com.codegym.model.Category;

import java.util.Optional;

public interface IGeneralService<T> {

>>>>>>> 20aa991ec45a64338686eea10734e0cc7b04d127
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

<<<<<<< HEAD
    void deleteById(Long id);
=======
    void delete(Long id);
>>>>>>> 20aa991ec45a64338686eea10734e0cc7b04d127
}
