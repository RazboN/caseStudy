package com.ykb.annualleavemodule.controller;

import com.ykb.annualleavemodule.model.EmployeeDetailsModel;
import com.ykb.annualleavemodule.model.EmployeeGroupsModel;
import com.ykb.annualleavemodule.model.EmployeesModel;
import com.ykb.annualleavemodule.model.RequestedLeaveModel;
import com.ykb.annualleavemodule.repository.EmployeeRepository;
import com.ykb.annualleavemodule.repository.RequestedLeaveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AnnualLeaveControllerTest {

    @Autowired
    EmployeeRepository _repo;

    @Autowired
    RequestedLeaveRepository _requestRepo;

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

        _repo.save(emp1);
        _repo.save(emp2);
        _repo.save(emp3);

        _repo.save(emp1);
        _repo.save(emp2);
        _repo.save(emp3);

        RequestedLeaveModel newReq = new RequestedLeaveModel(
                LocalDate.parse("2022-01-01"),
                LocalDate.parse("2022-01-07"),
                LocalDate.parse("2021-01-06"),
                5,
                emp1);

        _requestRepo.save(newReq);
    }
}