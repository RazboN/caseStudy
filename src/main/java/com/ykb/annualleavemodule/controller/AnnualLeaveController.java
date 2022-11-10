package com.ykb.annualleavemodule.controller;

import com.ykb.annualleavemodule.GlobalMessages;
import com.ykb.annualleavemodule.dto.LeaveConfirmDTO;
import com.ykb.annualleavemodule.dto.LeaveRequestsDTO;
import com.ykb.annualleavemodule.dto.RequestedLeaveModelDTO;
import com.ykb.annualleavemodule.helpers.LeaveDuration;
import com.ykb.annualleavemodule.model.EmployeesModel;
import com.ykb.annualleavemodule.model.RequestedLeaveModel;
import com.ykb.annualleavemodule.service.AnnualLeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@ResponseBody
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/annualLeave")
@Api(value = "Annual Leave Module API Documentation")
public class AnnualLeaveController implements ErrorController {

    @Autowired
    private AnnualLeaveService _service;

    @Autowired
    private LeaveDuration _leaveDuration;

    /** POSTMAPPING **/

    @PostMapping("/requestLeave")
    @ApiOperation(value = "Requesting annual leave method")
    public ResponseEntity requestAnnualLeave(@RequestBody RequestedLeaveModelDTO reqObj, @RequestParam String lang){

        if(null == reqObj){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        try{
            EmployeesModel requestOwner =
                    _service.checkEmployeeExistsAndGetInfo(Long.valueOf(reqObj.getEmployeeId()));

            RequestedLeaveModel newRequest = new RequestedLeaveModel(
                    LocalDate.parse(reqObj.getBackToWorkDate()),
                    LocalDate.parse(reqObj.getStartDate()),
                    LocalDate.parse(reqObj.getEndDate()),
                    _leaveDuration.leaveDurationCalculator(reqObj.getStartDate(), reqObj.getEndDate()),
                    requestOwner
            );

            if(requestOwner.getEmployeeDetails().getRemainingLeave() - newRequest.getDuration() < 0)
                return new ResponseEntity(GlobalMessages.getLocaleMessage("msg.notenoughleaveright",lang),
                        HttpStatus.NOT_ACCEPTABLE);

            _service.saveLeaveRequest(newRequest);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity(GlobalMessages.getLocaleMessage(ex.getMessage(),lang),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reviewLeave")
    @ApiOperation(value = "Reviewing annual request method")
    public ResponseEntity reviewLeave(@RequestBody LeaveConfirmDTO reviewObj, @RequestParam String lang) {
        try{
            if(!_service.isEmployeeManager(reviewObj.getReviewerId()))
                return new ResponseEntity(GlobalMessages.getLocaleMessage("msg.mustBeManager",lang),
                        HttpStatus.UNAUTHORIZED);

            return new ResponseEntity(_service.reviewLeaveRequest(reviewObj.getId(),
                    reviewObj.getLeaveResult()), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity(GlobalMessages.getLocaleMessage(ex.getMessage(),lang),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /** GETMAPPING **/

    @GetMapping("/getRequests")
    @ApiOperation(value = "Getting active requests method")
    public ResponseEntity getLeaveRequests(@RequestParam String empId, @RequestParam String lang){
        log.info("RequestParam {}", Long.valueOf(empId));

        try{
            boolean isEmpManager = _service.isEmployeeManager(Long.valueOf(empId));

            log.info("Is requester manager {}", isEmpManager);

            if(!isEmpManager)
                return new ResponseEntity(GlobalMessages.getLocaleMessage("msg.mustBeManager", lang),
                        HttpStatus.NON_AUTHORITATIVE_INFORMATION);

            List<LeaveRequestsDTO> leaveRequests = _service.getAllRequests();
            log.info("Leave requests {}", leaveRequests);

            return new ResponseEntity(leaveRequests, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error("getActiveLeaveRequests {}", ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}