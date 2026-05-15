package com.itams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itams.Maintenance.Maintenance;
import com.itams.Maintenance.MaintenanceService;
import com.itams.device.Device;
import com.itams.device.DeviceService;

@SpringBootTest
public class MaintenanceIntegrationTest {

    @Autowired
    private MaintenanceService service;

    @Autowired
    private DeviceService deviceService;

    @Test
    void testMaintenanceLog() {
        deviceService.save(new Device("Printer", "SN-5005"));

        Maintenance maintenance = new Maintenance(
                "Printer",
                "Paper jam issue",
                "In Progress"
        );

        Maintenance saved = service.save(maintenance);

        Assertions.assertNotNull(saved.getId());
    }

    @Test
    void testMaintenanceMarksDeviceUnavailable() {
        deviceService.save(new Device("Scanner", "SN-6006"));

        service.save(new Maintenance("Scanner", "Calibration issue", "In Progress"));

        var deviceOpt = deviceService.findByName("Scanner");
        Assertions.assertTrue(deviceOpt.isPresent());
        Assertions.assertEquals("IN_MAINTENANCE", deviceOpt.get().getStatus());
    }

    @Test
    void testRepairMarksDeviceAvailableAgain() {
        deviceService.save(new Device("Server", "SN-7007"));

        Maintenance maintenance = service.save(
                new Maintenance("Server", "Overheating", "In Progress")
        );

        service.updateRepairStatus(maintenance.getId(), "Repaired");

        var deviceOpt = deviceService.findByName("Server");
        Assertions.assertTrue(deviceOpt.isPresent());
        Assertions.assertEquals("AVAILABLE", deviceOpt.get().getStatus());
    }
}
