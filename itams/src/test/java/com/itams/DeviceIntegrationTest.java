package com.itams;

import com.itams.device.Device;
import com.itams.device.DeviceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeviceIntegrationTest {

    @Autowired
    private DeviceService service;

    @Test
    void testAddDevice() {
        Device device = new Device("Laptop", "SN-1001");
        Device saved = service.save(device);

        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals("AVAILABLE", saved.getStatus());
    }

    @Test
    void testUpdateDeviceStatus() {
        Device device = new Device("Monitor", "SN-2002");
        Device saved = service.save(device);

        service.updateStatus(saved.getId(), "IN_MAINTENANCE");

        var updatedOpt = service.findByName("Monitor");
        Assertions.assertTrue(updatedOpt.isPresent());
        Assertions.assertEquals("IN_MAINTENANCE", updatedOpt.get().getStatus());
    }
}
