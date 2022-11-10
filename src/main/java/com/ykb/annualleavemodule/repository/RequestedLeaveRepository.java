package com.ykb.annualleavemodule.repository;

import com.ykb.annualleavemodule.model.RequestedLeaveModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RequestedLeaveRepository extends JpaRepository<RequestedLeaveModel, Long> {
    public List<RequestedLeaveModel> findAllByStatus(String status);
}
