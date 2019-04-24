package com.nalivayko.pool;

import com.nalivayko.pool.domain.Repair;
import com.nalivayko.pool.domain.RepairStatus;
import com.nalivayko.pool.repositories.ConnectionManager;
import com.nalivayko.pool.repositories.RepairRepo;
import com.nalivayko.pool.repositories.sql.MySqlConnectionManager;
import com.nalivayko.pool.repositories.sql.SQLRepairRepo;

import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {

        ConnectionManager connectionManager = new MySqlConnectionManager();
        RepairRepo repairRepo = new SQLRepairRepo(connectionManager);
        System.out.println(repairRepo.findByUserId(2).toString());
//
//        repairRepo.add(new Repair.Builder()
//                .setId(10L)
//                .setUserId(2)
//                .setStatus(RepairStatus.NEW)
//                .setItem("item")
//                .setDescription("broken").build());
//        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
    }
}
