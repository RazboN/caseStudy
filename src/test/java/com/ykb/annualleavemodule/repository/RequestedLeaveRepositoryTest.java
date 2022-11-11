package com.ykb.annualleavemodule.repository;

import com.ykb.annualleavemodule.LeaveStatus;
import com.ykb.annualleavemodule.model.EmployeesModel;
import com.ykb.annualleavemodule.model.RequestedLeaveModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {RequestedLeaveRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.ykb.annualleavemodule.model"})
@DataJpaTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
class RequestedLeaveRepositoryTest {
    @MockBean
    private RequestedLeaveRepository _requestedLeaveRepo;

    @BeforeEach
    void setUp() {
        EmployeesModel emp = new EmployeesModel(
                    1L,
                    "Cem",
                    "Can",
                    null,
                    null);

        RequestedLeaveModel testRequest = new RequestedLeaveModel(
                        LocalDate.parse("2022-01-06"),
                        LocalDate.parse("2022-01-02"),
                        LocalDate.parse("2022-01-05"),
                        4,
                        emp
                );
        List<RequestedLeaveModel> testResultList = new ArrayList<>();
        testResultList.add(testRequest);

        Mockito.when(_requestedLeaveRepo.findAllByStatus(LeaveStatus.WAITING_APPROVAL))
                .thenReturn(testResultList);
    }

    @Test
    void testFindAllByStatus() {
        List<RequestedLeaveModel> testResult =_requestedLeaveRepo.findAllByStatus(LeaveStatus.WAITING_APPROVAL);
        verify(_requestedLeaveRepo).findAllByStatus(LeaveStatus.WAITING_APPROVAL);
        assertAll("testFindAllByStatus",
                () -> assertThat(testResult).isNotNull(),
                () -> assertThat(testResult.size()).isGreaterThan(0));
    }
}

