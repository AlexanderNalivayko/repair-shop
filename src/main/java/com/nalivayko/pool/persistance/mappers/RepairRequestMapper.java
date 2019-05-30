package com.nalivayko.pool.persistance.mappers;

import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.enums.RepairRequestStatus;
import com.nalivayko.pool.persistance.dao.sql.query.RepairRequestQuery;
import com.nalivayko.pool.util.FormattingUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairRequestMapper implements Mapper<RepairRequest> {

    @Override
    public RepairRequest getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new RepairRequest(resultSet.getInt(RepairRequestQuery.ID),
                new UserMapper().getEntity(resultSet),
                new ItemMapper().getEntity(resultSet),
                new ReviewMapper().getEntity(resultSet),
                RepairRequestStatus.valueOf(resultSet.getString(RepairRequestQuery.STATUS)),
                FormattingUtil.dateToString(resultSet.getTimestamp(RepairRequestQuery.CREATION_TIME)),
                resultSet.getString(RepairRequestQuery.DESCRIPTION));
    }
}
