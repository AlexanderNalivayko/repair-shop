package com.nalivayko.pool.repair_shop.persistance.mappers;

import com.nalivayko.pool.repair_shop.model.RepairRequest;
import com.nalivayko.pool.repair_shop.model.enums.RepairRequestStatus;
import com.nalivayko.pool.repair_shop.persistance.dao.sql.query.RepairRequestQuery;
import com.nalivayko.pool.repair_shop.util.FormattingUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairRequestMapper implements Mapper<RepairRequest> {

    @Override
    public RepairRequest getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new RepairRequest(resultSet.getInt(RepairRequestQuery.ID),
                new SecureUserMapper().getEntity(resultSet),
                new ItemMapper().getEntity(resultSet),
                new ReviewMapper().getEntity(resultSet),
                RepairRequestStatus.valueOf(resultSet.getString(RepairRequestQuery.STATUS)),
                FormattingUtil.dateToString(resultSet.getTimestamp(RepairRequestQuery.CREATION_TIME)),
                resultSet.getString(RepairRequestQuery.DESCRIPTION));
    }
}
