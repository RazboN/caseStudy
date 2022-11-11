package com.ykb.annualleavemodule.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ykb.annualleavemodule.LeaveStatus;
import com.ykb.annualleavemodule.model.EmployeeDetailsModel;
import com.ykb.annualleavemodule.model.EmployeeGroupsModel;
import com.ykb.annualleavemodule.model.EmployeesModel;
import com.ykb.annualleavemodule.model.RequestedLeaveModel;
import com.ykb.annualleavemodule.repository.EmployeeDetailsRepository;
import com.ykb.annualleavemodule.repository.EmployeeRepository;
import com.ykb.annualleavemodule.repository.RequestedLeaveRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * TODO: bu teste devam et !!(???)
 *
 * */

@ContextConfiguration(classes = {AnnualLeaveService.class})
@ExtendWith(SpringExtension.class)
class AnnualLeaveServiceTest {
    @Autowired
    private AnnualLeaveService annualLeaveService;

    @MockBean
    private EmployeeDetailsRepository employeeDetailsRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private RequestedLeaveRepository requestedLeaveRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testGetActiveRequests() {
        when(requestedLeaveRepository.findAllByStatus(LeaveStatus.WAITING_APPROVAL))
                .thenReturn(new ArrayList<>());
        assertTrue(annualLeaveService.getActiveRequests().isEmpty());
        verify(requestedLeaveRepository).findAllByStatus((String) any());
    }

    @Test
    void testGetActiveRequests2() {
        EmployeeDetailsModel employeeDetailsModel = new EmployeeDetailsModel();
        employeeDetailsModel.setEd_Id(123L);
        employeeDetailsModel.setRemainingLeave(1);
        employeeDetailsModel.setStartToWork(LocalDate.ofEpochDay(1L));

        EmployeeGroupsModel employeeGroupsModel = new EmployeeGroupsModel();
        employeeGroupsModel.setEg_Id(123L);
        employeeGroupsModel.setGroupCode("Group Code");
        employeeGroupsModel.setGroupName("Group Name");

        EmployeesModel employeesModel = new EmployeesModel();
        employeesModel.setE_Id(123L);
        employeesModel.setEmployeeDetails(employeeDetailsModel);
        employeesModel.setEmployeeType(employeeGroupsModel);
        employeesModel.setName("Name");
        employeesModel.setSurname("Doe");

        RequestedLeaveModel requestedLeaveModel = new RequestedLeaveModel();
        requestedLeaveModel.setDuration(1);
        requestedLeaveModel.setEmployee(employeesModel);
        requestedLeaveModel.setLeaveEndDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel.setLeaveStartDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel.setReq_id(1L);
        requestedLeaveModel.setStartWorkingDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel.setStatus("Status");

        ArrayList<RequestedLeaveModel> requestedLeaveModelList = new ArrayList<>();
        requestedLeaveModelList.add(requestedLeaveModel);
        when(requestedLeaveRepository.findAllByStatus((String) any())).thenReturn(requestedLeaveModelList);
        assertEquals(1, annualLeaveService.getActiveRequests().size());
        verify(requestedLeaveRepository).findAllByStatus((String) any());
    }

    @Test
    void testGetActiveRequests3() {
        EmployeeDetailsModel employeeDetailsModel = new EmployeeDetailsModel();
        employeeDetailsModel.setEd_Id(123L);
        employeeDetailsModel.setRemainingLeave(1);
        employeeDetailsModel.setStartToWork(LocalDate.ofEpochDay(1L));

        EmployeeGroupsModel employeeGroupsModel = new EmployeeGroupsModel();
        employeeGroupsModel.setEg_Id(123L);
        employeeGroupsModel.setGroupCode("Group Code");
        employeeGroupsModel.setGroupName("Group Name");

        EmployeesModel employeesModel = new EmployeesModel();
        employeesModel.setE_Id(123L);
        employeesModel.setEmployeeDetails(employeeDetailsModel);
        employeesModel.setEmployeeType(employeeGroupsModel);
        employeesModel.setName("Name");
        employeesModel.setSurname("Doe");

        RequestedLeaveModel requestedLeaveModel = new RequestedLeaveModel();
        requestedLeaveModel.setDuration(1);
        requestedLeaveModel.setEmployee(employeesModel);
        requestedLeaveModel.setLeaveEndDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel.setLeaveStartDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel.setReq_id(1L);
        requestedLeaveModel.setStartWorkingDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel.setStatus("Status");

        EmployeeDetailsModel employeeDetailsModel1 = new EmployeeDetailsModel();
        employeeDetailsModel1.setEd_Id(123L);
        employeeDetailsModel1.setRemainingLeave(1);
        employeeDetailsModel1.setStartToWork(LocalDate.ofEpochDay(1L));

        EmployeeGroupsModel employeeGroupsModel1 = new EmployeeGroupsModel();
        employeeGroupsModel1.setEg_Id(123L);
        employeeGroupsModel1.setGroupCode("Group Code");
        employeeGroupsModel1.setGroupName("Group Name");

        EmployeesModel employeesModel1 = new EmployeesModel();
        employeesModel1.setE_Id(123L);
        employeesModel1.setEmployeeDetails(employeeDetailsModel1);
        employeesModel1.setEmployeeType(employeeGroupsModel1);
        employeesModel1.setName("Name");
        employeesModel1.setSurname("Doe");

        RequestedLeaveModel requestedLeaveModel1 = new RequestedLeaveModel();
        requestedLeaveModel1.setDuration(1);
        requestedLeaveModel1.setEmployee(employeesModel1);
        requestedLeaveModel1.setLeaveEndDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel1.setLeaveStartDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel1.setReq_id(1L);
        requestedLeaveModel1.setStartWorkingDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel1.setStatus("Status");

        ArrayList<RequestedLeaveModel> requestedLeaveModelList = new ArrayList<>();
        requestedLeaveModelList.add(requestedLeaveModel1);
        requestedLeaveModelList.add(requestedLeaveModel);
        when(requestedLeaveRepository.findAllByStatus((String) any())).thenReturn(requestedLeaveModelList);
        assertEquals(2, annualLeaveService.getActiveRequests().size());
        verify(requestedLeaveRepository).findAllByStatus((String) any());
    }

    @Test
    @Disabled()
    void testGetActiveRequests4() {
    }

    @Test
    @Disabled()
    void testGetActiveRequests5() {
    }

    @Test
    @Disabled()
    void testGetActiveRequests6() {
    }

    @Test
    void testGetAllRequests() {
        when(requestedLeaveRepository.findAll((Sort) any())).thenReturn(new ArrayList<>());
        assertTrue(annualLeaveService.getAllRequests().isEmpty());
        verify(requestedLeaveRepository).findAll((Sort) any());
    }

    @Test
    void testGetAllRequests2() {
        EmployeeDetailsModel employeeDetailsModel = new EmployeeDetailsModel();
        employeeDetailsModel.setEd_Id(123L);
        employeeDetailsModel.setRemainingLeave(1);
        employeeDetailsModel.setStartToWork(LocalDate.ofEpochDay(1L));

        EmployeeGroupsModel employeeGroupsModel = new EmployeeGroupsModel();
        employeeGroupsModel.setEg_Id(123L);
        employeeGroupsModel.setGroupCode("status");
        employeeGroupsModel.setGroupName("status");

        EmployeesModel employeesModel = new EmployeesModel();
        employeesModel.setE_Id(123L);
        employeesModel.setEmployeeDetails(employeeDetailsModel);
        employeesModel.setEmployeeType(employeeGroupsModel);
        employeesModel.setName("status");
        employeesModel.setSurname("Doe");

        RequestedLeaveModel requestedLeaveModel = new RequestedLeaveModel();
        requestedLeaveModel.setDuration(1);
        requestedLeaveModel.setEmployee(employeesModel);
        requestedLeaveModel.setLeaveEndDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel.setLeaveStartDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel.setReq_id(1L);
        requestedLeaveModel.setStartWorkingDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel.setStatus("status");

        ArrayList<RequestedLeaveModel> requestedLeaveModelList = new ArrayList<>();
        requestedLeaveModelList.add(requestedLeaveModel);
        when(requestedLeaveRepository.findAll((Sort) any())).thenReturn(requestedLeaveModelList);
        assertEquals(1, annualLeaveService.getAllRequests().size());
        verify(requestedLeaveRepository).findAll((Sort) any());
    }

    @Test
    void testGetAllRequests3() {
        EmployeeDetailsModel employeeDetailsModel = new EmployeeDetailsModel();
        employeeDetailsModel.setEd_Id(123L);
        employeeDetailsModel.setRemainingLeave(1);
        employeeDetailsModel.setStartToWork(LocalDate.ofEpochDay(1L));

        EmployeeGroupsModel employeeGroupsModel = new EmployeeGroupsModel();
        employeeGroupsModel.setEg_Id(123L);
        employeeGroupsModel.setGroupCode("status");
        employeeGroupsModel.setGroupName("status");

        EmployeesModel employeesModel = new EmployeesModel();
        employeesModel.setE_Id(123L);
        employeesModel.setEmployeeDetails(employeeDetailsModel);
        employeesModel.setEmployeeType(employeeGroupsModel);
        employeesModel.setName("status");
        employeesModel.setSurname("Doe");

        RequestedLeaveModel requestedLeaveModel = new RequestedLeaveModel();
        requestedLeaveModel.setDuration(1);
        requestedLeaveModel.setEmployee(employeesModel);
        requestedLeaveModel.setLeaveEndDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel.setLeaveStartDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel.setReq_id(1L);
        requestedLeaveModel.setStartWorkingDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel.setStatus("status");

        EmployeeDetailsModel employeeDetailsModel1 = new EmployeeDetailsModel();
        employeeDetailsModel1.setEd_Id(123L);
        employeeDetailsModel1.setRemainingLeave(1);
        employeeDetailsModel1.setStartToWork(LocalDate.ofEpochDay(1L));

        EmployeeGroupsModel employeeGroupsModel1 = new EmployeeGroupsModel();
        employeeGroupsModel1.setEg_Id(123L);
        employeeGroupsModel1.setGroupCode("status");
        employeeGroupsModel1.setGroupName("status");

        EmployeesModel employeesModel1 = new EmployeesModel();
        employeesModel1.setE_Id(123L);
        employeesModel1.setEmployeeDetails(employeeDetailsModel1);
        employeesModel1.setEmployeeType(employeeGroupsModel1);
        employeesModel1.setName("status");
        employeesModel1.setSurname("Doe");

        RequestedLeaveModel requestedLeaveModel1 = new RequestedLeaveModel();
        requestedLeaveModel1.setDuration(1);
        requestedLeaveModel1.setEmployee(employeesModel1);
        requestedLeaveModel1.setLeaveEndDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel1.setLeaveStartDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel1.setReq_id(1L);
        requestedLeaveModel1.setStartWorkingDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel1.setStatus("status");

        ArrayList<RequestedLeaveModel> requestedLeaveModelList = new ArrayList<>();
        requestedLeaveModelList.add(requestedLeaveModel1);
        requestedLeaveModelList.add(requestedLeaveModel);
        when(requestedLeaveRepository.findAll((Sort) any())).thenReturn(requestedLeaveModelList);
        assertEquals(2, annualLeaveService.getAllRequests().size());
        verify(requestedLeaveRepository).findAll((Sort) any());
    }

    @Test
    @Disabled()
    void testGetAllRequests4() {
    }

    @Test
    @Disabled()
    void testGetAllRequests5() {
    }

    @Test
    @Disabled()
    void testGetAllRequests6() {
    }

    @Test
    void testSaveLeaveRequest() {
    }

    @Test
    void testGetEmployees() {
        ArrayList<EmployeesModel> employeesModelList = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employeesModelList);
        List<EmployeesModel> actualEmployees = annualLeaveService.getEmployees();
        assertSame(employeesModelList, actualEmployees);
        assertTrue(actualEmployees.isEmpty());
        verify(employeeRepository).findAll();
    }

    @Test
    void testCheckEmployeeExistsAndGetInfo() throws Exception {
        EmployeeDetailsModel employeeDetailsModel = new EmployeeDetailsModel();
        employeeDetailsModel.setEd_Id(123L);
        employeeDetailsModel.setRemainingLeave(1);
        employeeDetailsModel.setStartToWork(LocalDate.ofEpochDay(1L));

        EmployeeGroupsModel employeeGroupsModel = new EmployeeGroupsModel();
        employeeGroupsModel.setEg_Id(123L);
        employeeGroupsModel.setGroupCode("Group Code");
        employeeGroupsModel.setGroupName("Group Name");

        EmployeesModel employeesModel = new EmployeesModel();
        employeesModel.setE_Id(123L);
        employeesModel.setEmployeeDetails(employeeDetailsModel);
        employeesModel.setEmployeeType(employeeGroupsModel);
        employeesModel.setName("Name");
        employeesModel.setSurname("Doe");
        Optional<EmployeesModel> ofResult = Optional.of(employeesModel);
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(employeesModel, annualLeaveService.checkEmployeeExistsAndGetInfo(123L));
        verify(employeeRepository).findById((Long) any());
    }

    @Test
    @Disabled()
    void testCheckEmployeeExistsAndGetInfo2() throws Exception {
    }

    @Test
    void testIsEmployeeManager() throws Exception {
        EmployeeDetailsModel employeeDetailsModel = new EmployeeDetailsModel();
        employeeDetailsModel.setEd_Id(123L);
        employeeDetailsModel.setRemainingLeave(1);
        employeeDetailsModel.setStartToWork(LocalDate.ofEpochDay(1L));

        EmployeeGroupsModel employeeGroupsModel = new EmployeeGroupsModel();
        employeeGroupsModel.setEg_Id(123L);
        employeeGroupsModel.setGroupCode("Group Code");
        employeeGroupsModel.setGroupName("Group Name");

        EmployeesModel employeesModel = new EmployeesModel();
        employeesModel.setE_Id(123L);
        employeesModel.setEmployeeDetails(employeeDetailsModel);
        employeesModel.setEmployeeType(employeeGroupsModel);
        employeesModel.setName("Name");
        employeesModel.setSurname("Doe");
        Optional<EmployeesModel> ofResult = Optional.of(employeesModel);
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);
        assertFalse(annualLeaveService.isEmployeeManager(123L));
        verify(employeeRepository).findById((Long) any());
    }

    @Test
    @Disabled()
    void testIsEmployeeManager2() throws Exception {
    }

    @Test
    void testReviewLeaveRequest() throws Exception {
        EmployeeDetailsModel employeeDetailsModel = new EmployeeDetailsModel();
        employeeDetailsModel.setEd_Id(123L);
        employeeDetailsModel.setRemainingLeave(1);
        employeeDetailsModel.setStartToWork(LocalDate.ofEpochDay(1L));

        EmployeeGroupsModel employeeGroupsModel = new EmployeeGroupsModel();
        employeeGroupsModel.setEg_Id(123L);
        employeeGroupsModel.setGroupCode("Group Code");
        employeeGroupsModel.setGroupName("Group Name");

        EmployeesModel employeesModel = new EmployeesModel();
        employeesModel.setE_Id(123L);
        employeesModel.setEmployeeDetails(employeeDetailsModel);
        employeesModel.setEmployeeType(employeeGroupsModel);
        employeesModel.setName("Name");
        employeesModel.setSurname("Doe");

        RequestedLeaveModel requestedLeaveModel = new RequestedLeaveModel();
        requestedLeaveModel.setDuration(1);
        requestedLeaveModel.setEmployee(employeesModel);
        requestedLeaveModel.setLeaveEndDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel.setLeaveStartDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel.setReq_id(1L);
        requestedLeaveModel.setStartWorkingDate(LocalDate.ofEpochDay(1L));
        requestedLeaveModel.setStatus("Status");
        Optional<RequestedLeaveModel> ofResult = Optional.of(requestedLeaveModel);
        when(requestedLeaveRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(Exception.class, () -> annualLeaveService.reviewLeaveRequest(123L, 1));
        verify(requestedLeaveRepository).findById((Long) any());
    }

    @Test
    @Disabled()
    void testReviewLeaveRequest2() throws Exception {
    }
}

