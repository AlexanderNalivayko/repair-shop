package com.nalivayko.pool.repair_shop.persistance.dao.sql;

import com.nalivayko.pool.repair_shop.model.Item;
import com.nalivayko.pool.repair_shop.persistance.TransactionManager;
import com.nalivayko.pool.repair_shop.persistance.dao.ItemDAO;
import com.nalivayko.pool.repair_shop.persistance.dao.sql.query.ItemQuery;

/**
 * @see com.nalivayko.pool.repair_shop.persistance.dao.sql.AbstractSqlDAO
 */
public class ItemSqlDAO extends AbstractSqlDAO<Item> implements ItemDAO {

    public ItemSqlDAO(TransactionManager transactionManager) {
        super(transactionManager);
    }

    @Override
    public int create(Item item) {
        return create(ItemQuery.INSERT, preparedStatement -> {
            preparedStatement.setString(1, item.getItemType());
            preparedStatement.setString(2, item.getBrand());
            preparedStatement.setString(3, item.getName());
        });
    }

    @Override
    public boolean update(Item item) {
        return updateOrDelete(ItemQuery.UPDATE, preparedStatement -> {
            preparedStatement.setString(1, item.getItemType());
            preparedStatement.setString(2, item.getBrand());
            preparedStatement.setString(3, item.getName());
            preparedStatement.setInt(4, item.getId());
        });
    }
}
