package com.nalivayko.pool.persistance.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementations of this interface should map resultSet - to some specific type of date
 * @param <T>
 */
public interface Mapper<T> {

    T getEntity(ResultSet resultSet) throws SQLException;
}
