package com.maciejm.simplecrud.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> findAll();

    void create(T t);

    Optional<T> getById(int id);

    void update(T t, int id);

    void delete(int id);
}
