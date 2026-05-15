package com.itams.device;

import jakarta.persistence.*;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceName;
    private String serialNumber;
    private String status = "AVAILABLE";

    public Device() {
    }

    public Device(String deviceName, String serialNumber) {
        this.deviceName = deviceName;
        this.serialNumber = serialNumber;
    }

    public Long getId() {
        return id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
