package com.nalivayko.pool.persistance.mappers;

import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.enums.RepairStatus;
import com.nalivayko.pool.persistance.dao.sql.query.RepairQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairRequestMapper implements Mapper<RepairRequest> {

    @Override
    public RepairRequest getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new RepairRequest(resultSet.getInt(RepairQuery.ID),
                RepairStatus.valueOf(resultSet.getString(RepairQuery.STATUS)),
                new UserMapper().getEntity(resultSet),
                new ItemMapper().getEntity(resultSet),
                resultSet.getString(RepairQuery.DESCRIPTION),
                resultSet.getLong(RepairQuery.COST));
    }
}
