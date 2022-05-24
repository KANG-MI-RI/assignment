package com.mirikang.assignment.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Vacation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_id")
    @NotNull
    private Long id;

    @Column
    @NotNull
    private LocalDate startedAt;

    @Column
    private LocalDate endedAt;

    @JoinColumn(name = "employee_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private VacationType type;

    @Column
    private String comment;

    @Column
    private double vacationCount;

    @Builder
    public Vacation(Employee employee, LocalDate startedAt, LocalDate endedAt, VacationType type, String comment, double vacationCount) {
        this.employee = employee;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.type = type;
        this.comment = comment;
        this.vacationCount = vacationCount;
    }
}
