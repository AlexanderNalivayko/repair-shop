package com.nalivayko.pool;

import com.nalivayko.pool.persistance.dao.sql.RepairRequestSqlDAO;
import com.nalivayko.pool.persistance.dbcp.ConnectionManager;
import com.nalivayko.pool.persistance.dao.RepairRequestDAO;
import com.nalivayko.pool.persistance.dbcp.MySqlConnectionManager;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        ConnectionManager connectionManager = new MySqlConnectionManager();
        RepairRequestDAO repairRequestDAO = new RepairRequestSqlDAO(connectionManager);
        System.out.println(repairRequestDAO.findByUserId(2).toString());
//
//        repairRequestDAO.add(new RepairRequest.Builder()
//                .setId(10L)
//                .setUserId(2)
//                .setStatus(RepairStatus.NEW)
//                .setItem("item")
//                .setDescription("broken").build());
//        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
    }
}
