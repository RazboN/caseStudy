package com.ykb.annualleavemodule;

import com.ykb.annualleavemodule.controller.AnnualLeaveController;
import com.ykb.annualleavemodule.model.EmployeeDetailsModel;
import com.ykb.annualleavemodule.model.EmployeeGroupsModel;
import com.ykb.annualleavemodule.model.EmployeesModel;
import com.ykb.annualleavemodule.repository.EmployeeRepository;
import com.ykb.annualleavemodule.service.AnnualLeaveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class AnnualLeaveModuleApplicationTests {

    @Autowired
    EmployeeRepository _repo;

    @MockBean
    private AnnualLeaveService _service;

    @InjectMocks
    private AnnualLeaveController _controller;

    @Autowired
    private WebApplicationContext appContext;
    private MockMvc mockMvc;

    @Autowired
    JdbcTemplate jdbcTemplate;

	/*@BeforeEach
    public void initDatabase(){
        this.mockMvc = webAppContextSetup(appContext).build();

        EmployeesModel emp1 = new EmployeesModel();
        EmployeeDetailsModel eDet1 = new EmployeeDetailsModel();
        EmployeeGroupsModel groupEmplyee = new EmployeeGroupsModel();

        EmployeesModel emp2 = new EmployeesModel();
        EmployeeDetailsModel eDet2 = new EmployeeDetailsModel();
        EmployeeGroupsModel groupEmplyee2 = new EmployeeGroupsModel();

        EmployeesModel emp3 = new EmployeesModel();
        EmployeeDetailsModel eDet3 = new EmployeeDetailsModel();
        EmployeeGroupsModel groupEmplyee3 = new EmployeeGroupsModel();

        emp1.setE_Id(123L);
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

        emp2.setE_Id(124L);
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

        emp3.setE_Id(125L);
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
    }

    @Test
    @DisplayName(value = "Get all requests via employee")
    public void getLeaveRequestsViaEmployee() throws Exception {
        mockMvc.perform(get("/api/annualLeave/getRequests")
                        .param("empId", "2")
                        .param("lang", "tr"))
                .andExpect(status().isNonAuthoritativeInformation());
    }

    @Test
    @DisplayName(value = "Get all requests by manager")
    public void getLeaveRequestsViaManager() throws Exception {
        mockMvc.perform(get("/api/annualLeave/getRequests")
                        .param("empId", "3")
                        .param("lang", "tr"))
                .andExpect(status().isOk());
    }*/
}
