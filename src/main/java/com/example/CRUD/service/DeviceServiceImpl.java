package com.example.CRUD.service;

import com.example.CRUD.dto.DeviceDTO;
import com.example.CRUD.model.Device;
import com.example.CRUD.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<DeviceDTO> getAllDevices() {
        return deviceRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public DeviceDTO createDevice(DeviceDTO deviceDTO) {
        Device device = convertToEntity(deviceDTO);
        return convertToDTO(deviceRepository.save(device));
    }

    @Override
    public DeviceDTO updateDevice(Long id, DeviceDTO deviceDTO) {
        Device device = deviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Device not found"));
        device.setBrand(deviceDTO.getBrand());
        device.setModel(deviceDTO.getModel());
        device.setSerialNumber(deviceDTO.getSerialNumber());
        return convertToDTO(deviceRepository.save(device));
    }

    @Override
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    private DeviceDTO convertToDTO(Device device) {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(device.getId());
        deviceDTO.setBrand(device.getBrand());
        deviceDTO.setModel(device.getModel());
        deviceDTO.setSerialNumber(device.getSerialNumber());
        return deviceDTO;
    }

    private Device convertToEntity(DeviceDTO deviceDTO) {
        Device device = new Device();
        device.setBrand(deviceDTO.getBrand());
        device.setModel(deviceDTO.getModel());
        device.setSerialNumber(deviceDTO.getSerialNumber());
        return device;
    }
}
