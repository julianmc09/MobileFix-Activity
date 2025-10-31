package com.example.CRUD.service;

import com.example.CRUD.dto.RepairOrderDTO;
import com.example.CRUD.model.RepairOrder;
import com.example.CRUD.model.Status;
import com.example.CRUD.model.User;
import com.example.CRUD.repository.RepairOrderRepository;
import com.example.CRUD.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepairOrderServiceImpl implements RepairOrderService {

    private final RepairOrderRepository repairOrderRepository;
    private final UserRepository userRepository;

    public RepairOrderServiceImpl(RepairOrderRepository repairOrderRepository, UserRepository userRepository) {
        this.repairOrderRepository = repairOrderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<RepairOrderDTO> getAllRepairOrders() {
        return repairOrderRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public RepairOrderDTO createRepairOrder(RepairOrderDTO repairOrderDTO) {
        RepairOrder repairOrder = convertToEntity(repairOrderDTO);
        repairOrder.setStatus(Status.PENDING);
        return convertToDTO(repairOrderRepository.save(repairOrder));
    }

    @Override
    public RepairOrderDTO assignTech(Long orderId, Long techId) {
        RepairOrder repairOrder = repairOrderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Repair order not found"));
        User tech = userRepository.findById(techId).orElseThrow(() -> new RuntimeException("Technician not found"));
        // TODO: Check if user is a TECH
        repairOrder.setAssignedTech(tech);
        return convertToDTO(repairOrderRepository.save(repairOrder));
    }

    @Override
    public RepairOrderDTO changeStatus(Long orderId, Status status, String techNotes) {
        RepairOrder repairOrder = repairOrderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Repair order not found"));
        // TODO: Validate status transition
        repairOrder.setStatus(status);
        if (techNotes != null) {
            repairOrder.setTechNotes(techNotes);
        }
        return convertToDTO(repairOrderRepository.save(repairOrder));
    }

    @Override
    public void deleteRepairOrder(Long orderId) {
        // TODO: Add validation
        repairOrderRepository.deleteById(orderId);
    }

    private RepairOrderDTO convertToDTO(RepairOrder repairOrder) {
        RepairOrderDTO dto = new RepairOrderDTO();
        dto.setId(repairOrder.getId());
        dto.setCustomerId(repairOrder.getCustomer().getId());
        dto.setDeviceId(repairOrder.getDevice().getId());
        dto.setIssueDescription(repairOrder.getIssueDescription());
        dto.setStatus(repairOrder.getStatus());
        if (repairOrder.getAssignedTech() != null) {
            dto.setAssignedTechId(repairOrder.getAssignedTech().getId());
        }
        dto.setCreatedAt(repairOrder.getCreatedAt());
        dto.setUpdatedAt(repairOrder.getUpdatedAt());
        dto.setTechNotes(repairOrder.getTechNotes());
        return dto;
    }

    private RepairOrder convertToEntity(RepairOrderDTO dto) {
        RepairOrder repairOrder = new RepairOrder();
        // For creation, we'll need to fetch the customer and device from the database
        // This will be handled in the controller or a more specific service method
        repairOrder.setIssueDescription(dto.getIssueDescription());
        return repairOrder;
    }
}
