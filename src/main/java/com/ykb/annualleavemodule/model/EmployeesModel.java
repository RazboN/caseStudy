package com.ykb.annualleavemodule.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEES")
@Getter
@Setter
public class EmployeesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeId_generator")
    @Column(name = "ID")
    private Long e_Id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_EMPDETAIL")
    private EmployeeDetailsModel employeeDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_EMPTYPE")
    private EmployeeGroupsModel employeeType;

    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    @JoinColumn(name = "FK_EMPREQLEAVE")
    private List<RequestedLeaveModel> empRequestedLeaves;*/
}
