package com.ykb.annualleavemodule.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "EMPLOYEE_DETAILS")
@Getter
@Setter
public class EmployeeDetailsModel {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long ed_Id;

    @Column(name = "REMAINING_LEAVE")
    private int remainingLeave;

    @Column(name = "START_WORKING_DATE")
    private LocalDate startToWork;
}
