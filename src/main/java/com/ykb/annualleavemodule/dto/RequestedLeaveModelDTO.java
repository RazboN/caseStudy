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
public class RequestedLeaveModelDTO {
    @ApiModelProperty(value = "Employee id of requester")
    private String employeeId;

    @ApiModelProperty(value = "Starting date of annual leave request")
    private String startDate;

    @ApiModelProperty(value = "Ending day of annual leave request")
    private String endDate;

    @ApiModelProperty(value = "Returning date from annual leave")
    private String backToWorkDate;

    @ApiModelProperty(value = "Used language")
    private String language;
}
