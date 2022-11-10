package com.ykb.annualleavemodule.repository;

import com.ykb.annualleavemodule.model.EmployeeGroupsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeGroupsRepository extends JpaRepository<EmployeeGroupsModel, Long> {
}
