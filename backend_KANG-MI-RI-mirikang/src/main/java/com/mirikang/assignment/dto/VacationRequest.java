package com.mirikang.assignment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mirikang.assignment.exception.NotValidPeriodException;
import com.mirikang.assignment.exception.NotValidRequestDateException;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
public class VacationRequest {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endedAt;

    private String vacationType;

    private String comment;

    private boolean isValidPeriod() {

        return this.startedAt.isEqual(this.endedAt) || this.startedAt.isBefore(this.endedAt);
    }

    private boolean isValidStartedDateRequest() {

        return (!DayOfWeek.SATURDAY.equals(this.startedAt.getDayOfWeek())
                && !DayOfWeek.SUNDAY.equals(this.startedAt.getDayOfWeek()));
    }

    public double getVacationCount() {
        double count = 0;

        switch (this.vacationType) {
            case "VACATION":
                if (!isValidPeriod()) {
                    throw new NotValidPeriodException();
                }

                count = getVacationCountWithoutWeekend();
                break;
            case "HALF_VACATION":
                if (!isValidStartedDateRequest()) {
                    throw new NotValidRequestDateException();
                }
                count = 0.5;
                break;
            case "QUARTER_VACATION":
                if (!isValidStartedDateRequest()) {
                    throw new NotValidRequestDateException();
                }
                count = 0.25;
                break;
        }
        return count;
    }

    public double getVacationCountWithoutWeekend() {

        long until = this.startedAt.until(this.endedAt, ChronoUnit.DAYS);
        double count = 0;

        for (int i = 0; i <= until; i++) {

            LocalDate date = this.startedAt.plusDays(i);

            if (!DayOfWeek.SATURDAY.equals(date.getDayOfWeek()) && !DayOfWeek.SUNDAY.equals(date.getDayOfWeek())) {
                count++;
            }
        }

        if (count == 0) {
            throw new NotValidRequestDateException();
        }

        return count;
    }
}

