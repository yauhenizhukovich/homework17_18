package com.gmail.supersonicleader.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GeneralRepository<T> {

    List<T> findAll(Connection connection) throws SQLException;

    T add(Connection connection, T t) throws SQLException;

    T findById(Connection connection, Long id) throws SQLException;

    void deleteById(Connection connection, Long id) throws SQLException;

}
