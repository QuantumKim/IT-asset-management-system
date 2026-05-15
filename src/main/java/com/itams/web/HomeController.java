package com.itams.web;

import com.itams.Borrow.BorrowingService;
import com.itams.Maintenance.MaintenanceService;
import com.itams.device.DeviceService;
import com.itams.user.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final DeviceService deviceService;
    private final EmployeeService employeeService;
    private final BorrowingService borrowingService;
    private final MaintenanceService maintenanceService;

    public HomeController(DeviceService deviceService, EmployeeService employeeService,
                          BorrowingService borrowingService, MaintenanceService maintenanceService) {
        this.deviceService = deviceService;
        this.employeeService = employeeService;
        this.borrowingService = borrowingService;
        this.maintenanceService = maintenanceService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("deviceCount", deviceService.getAll().size());
        model.addAttribute("employeeCount", employeeService.getAll().size());
        model.addAttribute("borrowingCount", borrowingService.getAll().size());
        model.addAttribute("maintenanceCount", maintenanceService.getAll().size());
        return "index";
    }
}
