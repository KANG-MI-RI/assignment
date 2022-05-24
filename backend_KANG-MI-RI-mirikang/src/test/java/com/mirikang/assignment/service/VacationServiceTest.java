package com.mirikang.assignment.service;

import com.mirikang.assignment.dto.VacationRequest;
import com.mirikang.assignment.entity.Vacation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VacationServiceTest {
    @Autowired
    VacationService vacationService;


    @Test
    void saveVacation() {

        VacationRequest vacationRequest = new VacationRequest();
        vacationRequest.setVacationType("VACATION");
        vacationRequest.setStartedAt(LocalDate.parse("2022-05-02"));
        vacationRequest.setEndedAt(LocalDate.parse("2022-05-03"));
        vacationRequest.setComment("VacationService 연차 테스트");

        Vacation vacation = vacationService.saveVacation("tester1", vacationRequest);
        
        assertThat(vacation).isNotNull();
    }

    @Test
    void cancelVacation() {

        Vacation vacation = vacationService.cancelVacation("tester1", 1);

        assertThat(vacation).isNotNull();
    }
}