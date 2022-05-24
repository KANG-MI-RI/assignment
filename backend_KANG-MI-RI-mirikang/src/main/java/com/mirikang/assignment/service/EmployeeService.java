package com.mirikang.assignment.service;

import com.mirikang.assignment.entity.Employee;
import com.mirikang.assignment.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee getEmployee(String accountId) {
        return employeeRepository.findByAccountIdEquals(accountId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("사용자를 찾을 수 없습니다. (아이디: %s)", accountId)));
    }

    public Page<Employee> getEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public void initVacationCountForNewYear() {
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(e -> e.initVacationCount());
    }
}
