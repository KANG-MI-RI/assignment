package com.mirikang.assignment.service;

import com.mirikang.assignment.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    void getEmployee() {
        Employee employee = employeeService.getEmployee("tester1");

        assertThat(employee).isNotNull();
    }

    @Test
    void getEmployees() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<Employee> employees = employeeService.getEmployees(pageable);

        assertThat(employees.getContent().size() > 0).isTrue();
    }
}