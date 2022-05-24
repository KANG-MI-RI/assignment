package com.mirikang.assignment.repository;

import com.mirikang.assignment.entity.Vacation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {
    
    @EntityGraph(attributePaths = "employee")
    @Query("select v from Vacation v where v.employee.accountId = :accountId")
    Page<Vacation> findVacationFetchJoin(String accountId, Pageable pageable);
}
