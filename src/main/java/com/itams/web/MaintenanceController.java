package com.itams.web;

import com.itams.Maintenance.Maintenance;
import com.itams.Maintenance.MaintenanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    private final MaintenanceService service;

    public MaintenanceController(MaintenanceService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("maintenanceRecords", service.getAll());
        return "maintenance/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("maintenance", new Maintenance());
        return "maintenance/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Maintenance maintenance) {
        service.save(maintenance);
        return "redirect:/maintenance";
    }

    @PostMapping("/update-status/{id}")
    public String updateStatus(@PathVariable Long id, @RequestParam String repairStatus) {
        service.updateRepairStatus(id, repairStatus);
        return "redirect:/maintenance";
    }
}
