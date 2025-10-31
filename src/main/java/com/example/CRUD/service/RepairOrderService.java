package com.example.CRUD.service;

import com.example.CRUD.dto.RepairOrderDTO;
import com.example.CRUD.model.Status;

import java.util.List;

public interface RepairOrderService {
    List<RepairOrderDTO> getAllRepairOrders();
    RepairOrderDTO createRepairOrder(RepairOrderDTO repairOrderDTO);
    RepairOrderDTO assignTech(Long orderId, Long techId);
    RepairOrderDTO changeStatus(Long orderId, Status status, String techNotes);
    void deleteRepairOrder(Long orderId);
}
