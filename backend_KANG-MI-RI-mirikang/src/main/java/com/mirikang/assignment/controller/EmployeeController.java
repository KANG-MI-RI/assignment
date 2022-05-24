package com.mirikang.assignment.controller;

import com.mirikang.assignment.dto.EmployeeDto;
import com.mirikang.assignment.dto.VacationDto;
import com.mirikang.assignment.dto.VacationRequest;
import com.mirikang.assignment.entity.Employee;
import com.mirikang.assignment.entity.Vacation;
import com.mirikang.assignment.service.EmployeeService;
import com.mirikang.assignment.service.VacationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final VacationService vacationService;

    @GetMapping
    public ResponseEntity getEmployees(Pageable pageable) {
        Page<EmployeeDto> employees = employeeService.getEmployees(pageable).map(EmployeeDto::from);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity getEmployee(@PathVariable String accountId) {

        Employee employee = employeeService.getEmployee(accountId);

        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{accountId}/vacation/{vacationId}")
    public ResponseEntity cancelVacation(@PathVariable String accountId, @PathVariable long vacationId) {

        Vacation vacation = vacationService.cancelVacation(accountId, vacationId);
        VacationDto vacationDto = new VacationDto(vacation);

        return ResponseEntity.ok(vacationDto);
    }

    @PostMapping("/{accountId}/vacation")
    public ResponseEntity requestVacation(@PathVariable String accountId,
                                          @Validated @RequestBody VacationRequest vacationRequest) {

        Vacation vacation = vacationService.saveVacation(accountId, vacationRequest);
        VacationDto vacationDto = new VacationDto(vacation);

        return ResponseEntity.ok(vacationDto);
    }

    @GetMapping("/{accountId}/vacation")
    public ResponseEntity getVacationList(@PathVariable String accountId, Pageable pageable) {

        Page<VacationDto> vacationList = vacationService.getVacationList(accountId, pageable).map(VacationDto::new);

        return ResponseEntity.ok(vacationList);
    }
}
