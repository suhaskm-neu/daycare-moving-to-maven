package com.edu.neu.csye6200.repositories;

import com.edu.neu.csye6200.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {
    Vaccine findByVaccineName(String name);
}
