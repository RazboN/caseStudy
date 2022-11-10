package com.ykb.annualleavemodule.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ykb.annualleavemodule.LeaveStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REQUESTED_LEAVES")
@Getter
@Setter
public class RequestedLeaveModel {
    public RequestedLeaveModel(LocalDate backToWork, LocalDate startDate, LocalDate endDate, int duration,
                               EmployeesModel requestOwner) {
        startWorkingDate = backToWork;
        leaveStartDate = startDate;
        leaveEndDate = endDate;
        this.duration = duration;
        employee = requestOwner;

    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long req_id;

    @Column(name = "START_TO_WORK")
    private LocalDate startWorkingDate;

    @Column(name = "LEAVE_START")
    private LocalDate leaveStartDate;

    @Column(name = "LEAVE_END")
    private LocalDate leaveEndDate;

    @Column(name = "DURATION")
    private int duration;
    @Column(name = "STATUS")
    private String status;
    {
        status = LeaveStatus.WAITING_APPROVAL;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_EMPID")
    private EmployeesModel employee;
}
