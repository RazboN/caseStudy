package com.ykb.annualleavemodule.repository;

import com.ykb.annualleavemodule.model.EmployeesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeesModel, Long> {
}
