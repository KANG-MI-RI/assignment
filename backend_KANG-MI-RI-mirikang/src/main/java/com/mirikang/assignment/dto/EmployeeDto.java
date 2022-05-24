package com.mirikang.assignment.dto;

import com.mirikang.assignment.entity.Employee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeDto {
    private Long id;
    private String accountId;
    private String name;
    private double remainVacationCount;

    public static EmployeeDto from(Employee employee) {
        return new EmployeeDto(employee.getId(),
                employee.getAccountId(),
                employee.getName(),
                employee.getRemainVacationCount());
    }
}
