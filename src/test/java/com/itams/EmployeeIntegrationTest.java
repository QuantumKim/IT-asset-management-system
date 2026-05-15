package com.itams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itams.user.Employee;
import com.itams.user.EmployeeService;

@SpringBootTest
public class EmployeeIntegrationTest {

    @Autowired
    private EmployeeService service;

    @Test
    void testAddEmployee() {
        Employee employee = new Employee(
                "Jane Smith",
                "IT Department"
        );

        Employee saved = service.save(employee);

        Assertions.assertNotNull(saved.getId());
    }
}