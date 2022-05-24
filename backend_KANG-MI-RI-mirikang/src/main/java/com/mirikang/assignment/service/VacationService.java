package com.mirikang.assignment.service;

import com.mirikang.assignment.dto.VacationRequest;
import com.mirikang.assignment.entity.Employee;
import com.mirikang.assignment.entity.Vacation;
import com.mirikang.assignment.entity.VacationType;
import com.mirikang.assignment.repository.EmployeeRepository;
import com.mirikang.assignment.repository.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class VacationService {

    private final VacationRepository vacationRepository;
    private final EmployeeRepository employeeRepository;

    public Vacation saveVacation(String accountId, VacationRequest vacationRequest) {

        Employee employee = getEmployee(accountId);

        Vacation vacation = Vacation.builder()
                .employee(employee)
                .startedAt(vacationRequest.getStartedAt())
                .endedAt(vacationRequest.getEndedAt())
                .type(VacationType.valueOf(vacationRequest.getVacationType()))
                .vacationCount(vacationRequest.getVacationCount())
                .comment(vacationRequest.getComment())
                .build();

        vacationRepository.save(vacation);

        employee.registerVacation(vacation);

        return vacation;
    }

    public Vacation cancelVacation(String accountId, long vacationId) {

        Employee employee = getEmployee(accountId);
        Vacation vacation = employee.cancelVacation(vacationId);

        return vacation;
    }

    private Employee getEmployee(String accountId) {
        Employee employee = employeeRepository.findByAccountIdEquals(accountId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("사용자를 찾을 수 업습니다. (아이디: %s)", accountId)));

        return employee;
    }

    public Page<Vacation> getVacationList(String accountId, Pageable pageable) {
        Page<Vacation> vacationList = vacationRepository.findVacationFetchJoin(accountId, pageable);

        return vacationList;
    }

}
