package com.nalivayko.pool.services;

import com.nalivayko.pool.model.Item;
import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.model.enums.RepairRequestStatus;
import com.nalivayko.pool.persistance.TransactionManager;
import com.nalivayko.pool.persistance.dao.ItemDAO;
import com.nalivayko.pool.persistance.dao.RepairRequestDAO;

import java.util.List;

public class DefaultRepairRequestService implements RepairRequestService {

    private RepairRequestDAO repairRequestDAO;
    private ItemDAO itemDAO;
    private TransactionManager transactionManager;

    public DefaultRepairRequestService(RepairRequestDAO repairRequestDAO, ItemDAO itemDAO,
                                       TransactionManager transactionManager) {
        this.repairRequestDAO = repairRequestDAO;
        this.itemDAO = itemDAO;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<RepairRequest> getRepairRequestsByUserId(int userId) {
        return repairRequestDAO.findByUserId(userId);
    }

    /**
     * Create RepairRequest and write it to db
     * @param user - user that create repair request
     * @param itemType - type of repair request item
     * @param itemBrand - brand of item
     * @param itemName name of item
     * @param description - description of malfunction
     */
    public void createRepairRequest(User user, String itemType, String itemBrand, String itemName, String description) {
        transactionManager.startTransaction();
        Item item = new Item(itemType, itemBrand, itemName);
        int itemId = itemDAO.create(item);
        item.setId(itemId);
        RepairRequest repairRequest = new RepairRequest(user, item, RepairRequestStatus.NEW, description);
        repairRequestDAO.create(repairRequest);
        transactionManager.endTransaction();
    }
}
