package com.ykb.annualleavemodule.repository;

import com.ykb.annualleavemodule.LeaveStatus;
import com.ykb.annualleavemodule.model.EmployeesModel;
import com.ykb.annualleavemodule.model.RequestedLeaveModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.jpa.properties.javax.persistence.validation.mode=none"})
class RequestedLeaveRepositoryTest {

    @Autowired
    private RequestedLeaveRepository _repo;

    @BeforeEach
    void setUp() {
        EmployeesModel emp1 = new EmployeesModel();
        emp1.setE_Id(1L);
        emp1.setName("Cem");
        emp1.setSurname("Cantekin");

        RequestedLeaveModel newReq = new RequestedLeaveModel(
                LocalDate.parse("2022-01-01"),
                LocalDate.parse("2022-01-07"),
                LocalDate.parse("2021-01-06"),
                5,
                emp1);

        _repo.save(newReq);
    }

    @AfterEach
    void tearDown() {
        _repo.deleteAll();
    }

    @Test
    void shouldFindAllLeaveRequestsByStatus() {
        List<RequestedLeaveModel> leaveRequests = _repo.findAllByStatus(LeaveStatus.WAITING_APPROVAL);

        assertAll("Fids leave requests by status",
                () -> assertThat(leaveRequests).isNotNull(),
                () -> assertThat(leaveRequests).size().isGreaterThan(0));

    }
}