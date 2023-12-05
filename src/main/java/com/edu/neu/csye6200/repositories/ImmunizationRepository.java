package com.edu.neu.csye6200.repositories;

import com.edu.neu.csye6200.model.ImmunizationTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImmunizationRepository extends JpaRepository<ImmunizationTracker, Integer> {
    ImmunizationTracker findTopByStudentId(int studentId);

    ImmunizationTracker findByStudentId(int studentId);
}
