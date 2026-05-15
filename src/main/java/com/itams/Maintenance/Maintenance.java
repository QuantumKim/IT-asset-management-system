package com.itams.Maintenance;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceName;
    private String issueDescription;
    private String repairStatus;

    public Maintenance() {
    }

    public Maintenance(String deviceName, String issueDescription, String repairStatus) {
        this.deviceName = deviceName;
        this.issueDescription = issueDescription;
        this.repairStatus = repairStatus;
    }

    public Long getId() {
        return id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public String getRepairStatus() {
        return repairStatus;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }
}