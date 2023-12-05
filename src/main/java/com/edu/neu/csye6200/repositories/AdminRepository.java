package com.edu.neu.csye6200.repositories;

import com.edu.neu.csye6200.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    public Admin getByEmailIdAndPassword(String emailId, String password);
}

