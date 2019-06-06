package com.nalivayko.pool.persistance.mappers;

import com.nalivayko.pool.model.Item;
import com.nalivayko.pool.persistance.dao.sql.query.ItemQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements Mapper<Item> {

    @Override
    public Item getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new Item(resultSet.getInt(ItemQuery.ID),
                resultSet.getString(ItemQuery.ITEM_TYPE),
                resultSet.getString(ItemQuery.BRAND),
                resultSet.getString(ItemQuery.ITEM_NAME));
    }
}
