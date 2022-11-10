package com.ykb.annualleavemodule.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeaveConfirmDTO {
    @ApiModelProperty(value = "Request id to be accept/reject")
    private Long id;

    @ApiModelProperty(value = "Id of the reviewer")
    private Long reviewerId;

    @ApiModelProperty(value = "Request review 0-reject, 1-accept")
    private int leaveResult;

    @ApiModelProperty(value = "Used language")
    private String language;
}
