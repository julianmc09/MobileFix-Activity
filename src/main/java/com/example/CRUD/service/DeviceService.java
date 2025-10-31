package com.example.CRUD.service;

import com.example.CRUD.dto.DeviceDTO;

import java.util.List;

public interface DeviceService {
    List<DeviceDTO> getAllDevices();
    DeviceDTO createDevice(DeviceDTO deviceDTO);
    DeviceDTO updateDevice(Long id, DeviceDTO deviceDTO);
    void deleteDevice(Long id);
}
