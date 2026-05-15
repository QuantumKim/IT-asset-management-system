package com.itams.Borrow;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itams.device.DeviceService;

@Service
public class BorrowingService {

    private final BorrowingRepository repository;
    private final DeviceService deviceService;

    public BorrowingService(BorrowingRepository repository, DeviceService deviceService) {
        this.repository = repository;
        this.deviceService = deviceService;
    }

    public Borrowing save(Borrowing borrowing) {
        String deviceName = borrowing.getDeviceName();
        var deviceOpt = deviceService.findByName(deviceName);
        if (deviceOpt.isEmpty()) {
            throw new RuntimeException("Device not found: " + deviceName);
        }
        var device = deviceOpt.get();
        if (!"AVAILABLE".equals(device.getStatus())) {
            throw new RuntimeException("Device is not available: " + deviceName);
        }
        deviceService.updateStatus(device.getId(), "BORROWED");
        return repository.save(borrowing);
    }

    public List<Borrowing> getAll() {
        return repository.findAll();
    }
}