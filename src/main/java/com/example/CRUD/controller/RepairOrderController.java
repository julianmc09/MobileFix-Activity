package com.example.CRUD.controller;

import com.example.CRUD.dto.RepairOrderDTO;
import com.example.CRUD.model.Status;
import com.example.CRUD.service.RepairOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class RepairOrderController {

    private final RepairOrderService repairOrderService;

    public RepairOrderController(RepairOrderService repairOrderService) {
        this.repairOrderService = repairOrderService;
    }

    @GetMapping
    public ResponseEntity<List<RepairOrderDTO>> getAllRepairOrders() {
        return ResponseEntity.ok(repairOrderService.getAllRepairOrders());
    }

    @PostMapping
    public ResponseEntity<RepairOrderDTO> createRepairOrder(@RequestBody RepairOrderDTO repairOrderDTO) {
        return ResponseEntity.ok(repairOrderService.createRepairOrder(repairOrderDTO));
    }

    @PutMapping("/{id}/assign/{techId}")
    public ResponseEntity<RepairOrderDTO> assignTech(@PathVariable Long id, @PathVariable Long techId) {
        return ResponseEntity.ok(repairOrderService.assignTech(id, techId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<RepairOrderDTO> changeStatus(@PathVariable Long id, @RequestBody StatusUpdateDTO statusUpdateDTO) {
        return ResponseEntity.ok(repairOrderService.changeStatus(id, statusUpdateDTO.getStatus(), statusUpdateDTO.getTechNotes()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepairOrder(@PathVariable Long id) {
        repairOrderService.deleteRepairOrder(id);
        return ResponseEntity.noContent().build();
    }

    // DTO for status update
    public static class StatusUpdateDTO {
        private Status status;
        private String techNotes;

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public String getTechNotes() {
            return techNotes;
        }

        public void setTechNotes(String techNotes) {
            this.techNotes = techNotes;
        }
    }
}
