package com.itams;

import com.itams.Borrow.Borrowing;
import com.itams.Borrow.BorrowingService;
import com.itams.Maintenance.Maintenance;
import com.itams.Maintenance.MaintenanceService;
import com.itams.device.Device;
import com.itams.device.DeviceService;
import com.itams.user.Employee;
import com.itams.user.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FullFlowIntegrationTest {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BorrowingService borrowingService;

    @Autowired
    private MaintenanceService maintenanceService;

    @BeforeEach
    void setUp() {
        deviceService.save(new Device("Laptop-001", "SN-LAT-1001"));
        employeeService.save(new Employee("Jane Smith", "IT Department"));
    }

    @Test
    void testFullScenario_BorrowMaintenanceRepairAndReBorrow() {
        var deviceOpt = deviceService.findByName("Laptop-001");
        Assertions.assertTrue(deviceOpt.isPresent());
        Assertions.assertEquals("AVAILABLE", deviceOpt.get().getStatus());

        Borrowing borrowing = borrowingService.save(
                new Borrowing("Jane Smith", "Laptop-001", "2026-05-15")
        );
        Assertions.assertNotNull(borrowing.getId());

        deviceOpt = deviceService.findByName("Laptop-001");
        Assertions.assertEquals("BORROWED", deviceOpt.get().getStatus());

        Assertions.assertThrows(RuntimeException.class, () ->
                borrowingService.save(
                        new Borrowing("Someone Else", "Laptop-001", "2026-05-16")
                )
        );

        Maintenance maintenance = maintenanceService.save(
                new Maintenance("Laptop-001", "Keyboard not working", "In Progress")
        );
        Assertions.assertNotNull(maintenance.getId());

        deviceOpt = deviceService.findByName("Laptop-001");
        Assertions.assertEquals("IN_MAINTENANCE", deviceOpt.get().getStatus());

        Assertions.assertThrows(RuntimeException.class, () ->
                borrowingService.save(
                        new Borrowing("Another User", "Laptop-001", "2026-05-17")
                )
        );

        maintenanceService.updateRepairStatus(maintenance.getId(), "Repaired");

        deviceOpt = deviceService.findByName("Laptop-001");
        Assertions.assertEquals("AVAILABLE", deviceOpt.get().getStatus());

        Borrowing reBorrow = borrowingService.save(
                new Borrowing("Jane Smith", "Laptop-001", "2026-05-18")
        );
        Assertions.assertNotNull(reBorrow.getId());

        deviceOpt = deviceService.findByName("Laptop-001");
        Assertions.assertEquals("BORROWED", deviceOpt.get().getStatus());
    }
}
