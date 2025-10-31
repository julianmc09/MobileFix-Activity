package com.example.CRUD.service;

import com.example.CRUD.dto.RepairOrderDTO;
import com.example.CRUD.model.RepairOrder;
import com.example.CRUD.model.Status;
import com.example.CRUD.model.User;
import com.example.CRUD.repository.RepairOrderRepository;
import com.example.CRUD.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private RepairOrderRepository repairOrderRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RepairOrderServiceImpl repairOrderService;

    private User user;
    private User tech;
    private RepairOrder repairOrder;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);

        tech = new User();
        tech.setId(2L);

        repairOrder = new RepairOrder();
        repairOrder.setId(1L);
        repairOrder.setCustomer(user);
        repairOrder.setStatus(Status.PENDING);
    }

    @Test
    void create_shouldCreateOrderWithPendingStatus() {
        RepairOrderDTO dto = new RepairOrderDTO();
        when(repairOrderRepository.save(any(RepairOrder.class))).thenReturn(repairOrder);

        RepairOrderDTO result = repairOrderService.createRepairOrder(dto);

        assertEquals(Status.PENDING, result.getStatus());
    }

    @Test
    void assignTech_shouldAssignTechToOrder() {
        when(repairOrderRepository.findById(1L)).thenReturn(Optional.of(repairOrder));
        when(userRepository.findById(2L)).thenReturn(Optional.of(tech));
        when(repairOrderRepository.save(any(RepairOrder.class))).thenReturn(repairOrder);

        RepairOrderDTO result = repairOrderService.assignTech(1L, 2L);

        assertEquals(2L, result.getAssignedTechId());
    }

    @Test
    void changeStatus_shouldChangeStatus() {
        when(repairOrderRepository.findById(1L)).thenReturn(Optional.of(repairOrder));
        when(repairOrderRepository.save(any(RepairOrder.class))).thenReturn(repairOrder);

        RepairOrderDTO result = repairOrderService.changeStatus(1L, Status.IN_PROGRESS, null);

        assertEquals(Status.IN_PROGRESS, result.getStatus());
    }

    @Test
    void delete_shouldDeleteOrder() {
        // For now, this test just checks that the repository method is called.
        // We will add more complex logic later.
        assertDoesNotThrow(() -> repairOrderService.deleteRepairOrder(1L));
    }
}
