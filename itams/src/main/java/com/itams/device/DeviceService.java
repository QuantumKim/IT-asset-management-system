package com.itams.device;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository repository;

    public DeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    public Device save(Device device) {
        return repository.save(device);
    }

    public List<Device> getAll() {
        return repository.findAll();
    }

    public Optional<Device> findByName(String deviceName) {
        return repository.findByDeviceName(deviceName);
    }

    public void updateStatus(Long id, String status) {
        repository.findById(id).ifPresent(device -> {
            device.setStatus(status);
            repository.save(device);
        });
    }
}
