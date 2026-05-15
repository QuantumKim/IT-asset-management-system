package com.itams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itams.Borrow.Borrowing;
import com.itams.Borrow.BorrowingService;
import com.itams.device.Device;
import com.itams.device.DeviceService;

@SpringBootTest
public class BorrowingIntegrationTest {

    @Autowired
    private BorrowingService service;

    @Autowired
    private DeviceService deviceService;

    @Test
    void testBorrowDevice() {
        deviceService.save(new Device("Laptop", "SN-1001"));

        Borrowing borrowing = new Borrowing(
                "John Doe",
                "Laptop",
                "2026-05-15"
        );

        Borrowing saved = service.save(borrowing);

        Assertions.assertNotNull(saved.getId());
    }

    @Test
    void testBorrowMakesDeviceUnavailable() {
        deviceService.save(new Device("Tablet", "SN-3003"));

        service.save(new Borrowing("Alice", "Tablet", "2026-05-15"));

        var deviceOpt = deviceService.findByName("Tablet");
        Assertions.assertTrue(deviceOpt.isPresent());
        Assertions.assertEquals("BORROWED", deviceOpt.get().getStatus());
    }

    @Test
    void testBorrowUnavailableDeviceThrows() {
        deviceService.save(new Device("Phone", "SN-4004"));

        service.save(new Borrowing("Bob", "Phone", "2026-05-14"));

        Assertions.assertThrows(RuntimeException.class, () ->
            service.save(new Borrowing("Charlie", "Phone", "2026-05-15"))
        );
    }
}
