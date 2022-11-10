package com.ykb.annualleavemodule.repository;

import com.ykb.annualleavemodule.model.EmployeeDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetailsModel, Long> {
}
