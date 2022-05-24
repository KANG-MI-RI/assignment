package com.mirikang.assignment.dto;

import com.mirikang.assignment.entity.Vacation;
import com.mirikang.assignment.entity.VacationType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VacationDto {
    private Long id;

    private LocalDate startedAt;

    private LocalDate endedAt;

    private VacationType type;

    private String comment;

    private String accountId;

    private double remainVacationCount;

    @Builder
    public VacationDto(Vacation vacation) {
        this.id = vacation.getId();
        this.startedAt = vacation.getStartedAt();
        this.endedAt = vacation.getEndedAt();
        this.type = vacation.getType();
        this.comment = vacation.getComment();
        this.accountId = vacation.getEmployee().getAccountId();
        this.remainVacationCount = vacation.getEmployee().getRemainVacationCount();
    }
}
