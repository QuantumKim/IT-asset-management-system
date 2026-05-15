package com.itams.web;

import com.itams.device.Device;
import com.itams.device.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService service;

    public DeviceController(DeviceService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("devices", service.getAll());
        return "devices/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("device", new Device());
        return "devices/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Device device) {
        service.save(device);
        return "redirect:/devices";
    }
}
