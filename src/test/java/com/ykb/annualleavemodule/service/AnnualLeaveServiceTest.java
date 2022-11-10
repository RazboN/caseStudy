package com.ykb.annualleavemodule.service;

import com.ykb.annualleavemodule.LeaveStatus;
import com.ykb.annualleavemodule.dto.LeaveRequestsDTO;
import com.ykb.annualleavemodule.helpers.LeaveDuration;
import com.ykb.annualleavemodule.model.EmployeeDetailsModel;
import com.ykb.annualleavemodule.model.EmployeeGroupsModel;
import com.ykb.annualleavemodule.model.EmployeesModel;
import com.ykb.annualleavemodule.model.RequestedLeaveModel;
import com.ykb.annualleavemodule.repository.EmployeeRepository;
import com.ykb.annualleavemodule.repository.RequestedLeaveRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AnnualLeaveServiceTest {

    @Autowired
    EmployeeRepository _repo;

    @Autowired
    RequestedLeaveRepository _requestRepo;

    @AfterEach
    void tearDown() {
        _repo.deleteAll();
        _requestRepo.deleteAll();
    }

    @Autowired
    private LeaveDuration _leaveDuration;

    @BeforeEach
    void setUp() {
        EmployeesModel emp1 = new EmployeesModel();
        EmployeeDetailsModel eDet1 = new EmployeeDetailsModel();
        EmployeeGroupsModel groupEmplyee = new EmployeeGroupsModel();

        EmployeesModel emp2 = new EmployeesModel();
        EmployeeDetailsModel eDet2 = new EmployeeDetailsModel();
        EmployeeGroupsModel groupEmplyee2 = new EmployeeGroupsModel();

        EmployeesModel emp3 = new EmployeesModel();
        EmployeeDetailsModel eDet3 = new EmployeeDetailsModel();
        EmployeeGroupsModel groupEmplyee3 = new EmployeeGroupsModel();

        emp1.setE_Id(1L);
        emp1.setName("Cem");
        emp1.setSurname("Cantekin");

        eDet1.setEd_Id(1L);
        eDet1.setRemainingLeave(15);
        eDet1.setStartToWork(LocalDate.parse("2021-01-01"));

        groupEmplyee.setEg_Id(1L);
        groupEmplyee.setGroupCode("employee");
        groupEmplyee.setGroupName("employee");

        emp1.setEmployeeType(groupEmplyee);
        emp1.setEmployeeDetails(eDet1);

        emp2.setE_Id(2L);
        emp2.setName("Can");
        emp2.setSurname("Cem");

        eDet2.setEd_Id(124L);
        eDet2.setRemainingLeave(15);
        eDet2.setStartToWork(LocalDate.parse("2021-01-01"));

        groupEmplyee2.setEg_Id(1L);
        groupEmplyee2.setGroupCode("employee");
        groupEmplyee2.setGroupName("employee");

        emp2.setEmployeeType(groupEmplyee);
        emp2.setEmployeeDetails(eDet2);

        emp3.setE_Id(3L);
        emp3.setName("Mert");
        emp3.setSurname("Cöm");

        eDet3.setEd_Id(125L);
        eDet3.setRemainingLeave(15);
        eDet3.setStartToWork(LocalDate.parse("2021-01-01"));

        groupEmplyee3.setEg_Id(2L);
        groupEmplyee3.setGroupCode("manager");
        groupEmplyee3.setGroupName("manager");

        emp3.setEmployeeType(groupEmplyee3);
        emp3.setEmployeeDetails(eDet3);

        RequestedLeaveModel newReq = new RequestedLeaveModel(
                LocalDate.parse("2022-01-01"),
                LocalDate.parse("2022-01-07"),
                LocalDate.parse("2021-01-06"),
                5,
                emp1);

        _repo.save(emp1);
        _repo.save(emp2);
        _repo.save(emp3);
        _requestRepo.save(newReq);

    }

    @Test
    void shouldSaveLeaveRequestAndChecksLeaveDurationIsRecordSavedAndHasId() throws Exception {
        EmployeesModel testEmpl =  _repo.findById(1L).get();
        int leaveDuration = _leaveDuration.leaveDurationCalculator("2021-10-10","2021-10-14");
        RequestedLeaveModel testRequest = new RequestedLeaveModel(
                LocalDate.parse("2021-10-15"),
                LocalDate.parse("2021-10-10"),
                LocalDate.parse("2021-10-14"),
                leaveDuration,
                testEmpl);

        RequestedLeaveModel savedReq = _requestRepo.save(testRequest);

        RequestedLeaveModel req = _requestRepo.findById(savedReq.getReq_id()).get();

        assertAll("isSavedReqNotNull, isSavedReqHasIdGenerated, isIdWasGeneratedForNewReq",
                () -> assertThat(leaveDuration).isEqualTo(4),
                () -> assertThat(savedReq).isNotNull().hasFieldOrProperty("req_id"),
                () -> assertThat(req.getReq_id()).isGreaterThan(0));
    }
    @Test
    void shouldGetsAllActiveStatusLeaveRequests() {

        List<RequestedLeaveModel> modelList = _requestRepo.findAllByStatus(LeaveStatus.WAITING_APPROVAL);

        List<LeaveRequestsDTO> activeLeaveRequests = modelList.stream().map(request -> {
            LeaveRequestsDTO objDTO =
                    new LeaveRequestsDTO(
                            request.getEmployee().getE_Id(),
                            request.getEmployee().getName(),
                            request.getEmployee().getSurname(),
                            request.getLeaveStartDate().toString(),
                            request.getLeaveEndDate().toString(),
                            request.getStartWorkingDate().toString()
                    );
            return objDTO;
        }).collect(Collectors.toList());

        assertAll("getsAllActiveRequests",
                () -> assertThat(modelList).isNotNull(),
                () -> assertThat(modelList).size().isGreaterThan(0),
                () -> assertThat(activeLeaveRequests).isNotNull(),
                () -> assertThat(activeLeaveRequests).size().isGreaterThan(0));
    }

    @Test
    void shouldGetsAllEmployees() {
        List<EmployeesModel> employees = _repo.findAll();

        assertAll("Gets recent employee list",
                () -> assertThat(employees).isNotNull(),
                () -> assertThat(employees).size().isGreaterThan(0));
    }
}