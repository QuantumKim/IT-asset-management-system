package com.itams.Maintenance;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itams.device.DeviceService;

@Service
public class MaintenanceService {

    private final MaintenanceRepository repository;
    private final DeviceService deviceService;

    public MaintenanceService(MaintenanceRepository repository, DeviceService deviceService) {
        this.repository = repository;
        this.deviceService = deviceService;
    }

    public Maintenance save(Maintenance maintenance) {
        String deviceName = maintenance.getDeviceName();
        deviceService.findByName(deviceName).ifPresent(device ->
            deviceService.updateStatus(device.getId(), "IN_MAINTENANCE")
        );
        return repository.save(maintenance);
    }

    public Maintenance updateRepairStatus(Long id, String newRepairStatus) {
        var maintenance = repository.findById(id).orElseThrow(
            () -> new RuntimeException("Maintenance record not found: " + id)
        );
        maintenance.setRepairStatus(newRepairStatus);
        Maintenance updated = repository.save(maintenance);

        deviceService.findByName(maintenance.getDeviceName()).ifPresent(device -> {
            String newDeviceStatus = "Repaired".equalsIgnoreCase(newRepairStatus) ? "AVAILABLE" : "IN_MAINTENANCE";
            deviceService.updateStatus(device.getId(), newDeviceStatus);
        });

        return updated;
    }

    public List<Maintenance> getAll() {
        return repository.findAll();
    }
}
