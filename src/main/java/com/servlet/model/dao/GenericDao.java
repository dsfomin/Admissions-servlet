package com.servlet.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    void create(T entity) throws SQLException;
    Optional<T> findById(int id) throws SQLException;
    List<T> findAll(int start, int total,String sortBy,String order);
    List<T> findAll();
    void update(T entity) throws SQLException;
    void delete(int id) throws SQLException;
    void close();
}
