package com.ykb.annualleavemodule.service;

import com.ykb.annualleavemodule.LeaveStatus;
import com.ykb.annualleavemodule.dto.LeaveRequestsDTO;
import com.ykb.annualleavemodule.model.EmployeeDetailsModel;
import com.ykb.annualleavemodule.model.EmployeesModel;
import com.ykb.annualleavemodule.model.RequestedLeaveModel;
import com.ykb.annualleavemodule.repository.EmployeeDetailsRepository;
import com.ykb.annualleavemodule.repository.RequestedLeaveRepository;
import com.ykb.annualleavemodule.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AnnualLeaveService {
    @Autowired
    private RequestedLeaveRepository _requestLeaveRepo;
    @Autowired
    private EmployeeRepository _employeeRepo;

    @Autowired
    private EmployeeDetailsRepository _employeeDetailsRepo;

    public List<LeaveRequestsDTO> getActiveRequests(){
        List<LeaveRequestsDTO> activeLeaveRequests = new ArrayList<>();

        List<RequestedLeaveModel> modelList = _requestLeaveRepo.findAllByStatus(LeaveStatus.WAITING_APPROVAL);

        activeLeaveRequests = modelList.stream().map(request -> {
            LeaveRequestsDTO objDTO =
                    new LeaveRequestsDTO(
                            request.getEmployee().getE_Id(),
                            request.getEmployee().getName(),
                            request.getEmployee().getSurname(),
                            request.getLeaveStartDate().toString(),
                            request.getLeaveEndDate().toString(),
                            request.getStartWorkingDate().toString()
                            );
            return objDTO;
        }).collect(Collectors.toList());

        return activeLeaveRequests;
    }

    public List<LeaveRequestsDTO> getAllRequests(){
        List<LeaveRequestsDTO> activeLeaveRequests = new ArrayList<>();

        List<RequestedLeaveModel> modelList =
                _requestLeaveRepo.findAll(Sort.by(Sort.Direction.ASC, "status"));

        activeLeaveRequests = modelList.stream().map(request -> {
            LeaveRequestsDTO objDTO =
                    new LeaveRequestsDTO(
                            request.getEmployee().getE_Id(),
                            request.getEmployee().getName(),
                            request.getEmployee().getSurname(),
                            request.getLeaveStartDate().toString(),
                            request.getLeaveEndDate().toString(),
                            request.getStartWorkingDate().toString()
                    );
            return objDTO;
        }).collect(Collectors.toList());

        return activeLeaveRequests;
    }

    public RequestedLeaveModel saveLeaveRequest(RequestedLeaveModel newRequest){
        _requestLeaveRepo.save(newRequest);

        log.info("Leave request saved - {}", newRequest);

        return newRequest;
    }

    public List<EmployeesModel> getEmployees(){
        List<EmployeesModel> employeeDetails = new ArrayList<>();

        employeeDetails = _employeeRepo.findAll();

        log.info("getEmployees - {}", employeeDetails);

        return employeeDetails;
    }

    public  EmployeesModel checkEmployeeExistsAndGetInfo(Long employeeId) throws Exception {
        EmployeesModel employee = _employeeRepo.findById(employeeId).get();

        if(null == employee){
            log.info("ID {}  çalışan bulunamadı!", employeeId);

            throw new Exception("msg.employeeNotFound");
        }

        log.info("checkEmployeeExistsAndGetInfo - {}", employee);
        return employee;
    }

    public boolean isEmployeeManager(Long employeeId) throws Exception {
        EmployeesModel requesterEmployee = checkEmployeeExistsAndGetInfo(employeeId);

        return requesterEmployee.getEmployeeType().getGroupCode().equals("manager");
    }

    public RequestedLeaveModel reviewLeaveRequest(Long requestId, int reviewDecision) throws Exception{

        RequestedLeaveModel requestToBeReviewed = _requestLeaveRepo.findById(requestId).get();

        if(null == requestToBeReviewed){
            log.error("{} id li, kayıtlı izin bulunamadı.", requestId);
            throw new Exception("msg.savedRequestNorFound");
        }

        if(!requestToBeReviewed.getStatus().equals("Waiting Approval")){
            log.error("{} izin onaylanmış.", requestToBeReviewed);
            throw new Exception("msg.alreadyReviewed");
        }

        if(reviewDecision == 0){
            requestToBeReviewed.setStatus(LeaveStatus.REJECTED);
        }
        else {
            requestToBeReviewed.setStatus(LeaveStatus.APPROVED);
        }

        EmployeeDetailsModel requestOwnerDetails = requestToBeReviewed.getEmployee()
                .getEmployeeDetails();

        requestOwnerDetails.setRemainingLeave(requestOwnerDetails.getRemainingLeave()
                - requestToBeReviewed.getDuration());

        log.info("{} izin hakkı düzenlendi.", requestToBeReviewed.getEmployee());
        _employeeDetailsRepo.save(requestOwnerDetails);

        log.info("{} izin değerlendirildi.", requestToBeReviewed);
        return _requestLeaveRepo.save(requestToBeReviewed);
    }
}
