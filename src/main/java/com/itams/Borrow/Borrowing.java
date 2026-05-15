package com.itams.Borrow;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeName;
    private String deviceName;
    private String borrowDate;

    public Borrowing() {
    }

    public Borrowing(String employeeName, String deviceName, String borrowDate) {
        this.employeeName = employeeName;
        this.deviceName = deviceName;
        this.borrowDate = borrowDate;
    }

    public Long getId() {
        return id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }
}