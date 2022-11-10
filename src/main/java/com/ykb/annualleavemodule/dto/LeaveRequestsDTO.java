package com.ykb.annualleavemodule.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@ApiModel(value = "Annual Leave Module Model API Documentation", description = "Model")
public class LeaveRequestsDTO {
    @ApiModelProperty(value = "Employee id of requester")
    private Long employeeId;

    @ApiModelProperty(value = "Employee name of requester")
    private String employeeName;

    @ApiModelProperty(value = "Employee surnam of requester")
    private String employeeSurname;

    @ApiModelProperty(value = "Starting date of annual leave request")
    private String startDate;

    @ApiModelProperty(value = "Ending day of annual leave request")
    private String endDate;

    @ApiModelProperty(value = "Returning date from annual leave")
    private String backToWorkDate;
}
