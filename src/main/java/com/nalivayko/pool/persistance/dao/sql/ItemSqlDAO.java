package com.nalivayko.pool.persistance.dao.sql;

import com.nalivayko.pool.model.Item;
import com.nalivayko.pool.persistance.dao.ItemDAO;
import com.nalivayko.pool.persistance.dao.sql.query.ItemQuery;
import com.nalivayko.pool.persistance.dbcp.ConnectionManager;

public class ItemSqlDAO extends AbstractSqlDAO<Item> implements ItemDAO {

    public ItemSqlDAO(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public int create(Item item) {
        return create(ItemQuery.INSERT, preparedStatement -> {
            preparedStatement.setString(1, item.getProductType());
            preparedStatement.setString(2, item.getBrand());
            preparedStatement.setString(3, item.getName());
        });
    }

    @Override
    public boolean update(Item item) {
        return updateDelete(ItemQuery.UPDATE, preparedStatement -> {
            preparedStatement.setString(1, item.getProductType());
            preparedStatement.setString(2, item.getBrand());
            preparedStatement.setString(3, item.getName());
            preparedStatement.setInt(4, item.getId());
        });
    }
}
