package com.mirikang.assignment.entity;

import com.mirikang.assignment.exception.NoRemainedVacationCountException;
import com.mirikang.assignment.exception.UnableToCancelException;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    @NotNull
    private String accountId;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private double remainVacationCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Vacation> vacationList = new ArrayList<>();

    public void registerVacation(Vacation vacation) {

        if (remainVacationCount <= 0) {
            throw new NoRemainedVacationCountException();
        }

        if (remainVacationCount < vacation.getVacationCount()) {
            throw new NoRemainedVacationCountException();
        }

        this.remainVacationCount -= vacation.getVacationCount();
        this.vacationList.add(vacation);
    }

    public Vacation cancelVacation(long vacationId) {

        Vacation vacation = this.vacationList.stream()
                .filter(x -> x.getId() == vacationId)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("휴가 정보를 찾을 수 없습니다. (아이디: %d)", vacationId)));

        if (vacation.getStartedAt().isBefore(LocalDate.now())) {

            throw new UnableToCancelException();
        }

        double vacationCount = vacation.getVacationCount();
        this.remainVacationCount += vacationCount;

        this.vacationList.remove(vacation);

        return vacation;
    }

    public void initVacationCount() {
        this.remainVacationCount = 15;
    }
}
