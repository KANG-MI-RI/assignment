package com.mirikang.assignment.scheduler;

import com.mirikang.assignment.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class VacationScheduler {

    private final EmployeeService employeeService;

    @Scheduled(cron = "0 0 0 1 1 ?")
    public void scheduleTaskUsingCronExpression() {
        log.info("모든 사용자의 연차를 15일로 초기화합니다.");
        employeeService.initVacationCountForNewYear();
    }
}

