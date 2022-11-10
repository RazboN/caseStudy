package com.ykb.annualleavemodule.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE_GROUPS")
@Getter
@Setter
public class EmployeeGroupsModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eg_Id;

    @Column(name = "GROUP_CODE")
    private String groupCode;

    @Column(name = "GROUP_NAME")
    private String groupName;
}
