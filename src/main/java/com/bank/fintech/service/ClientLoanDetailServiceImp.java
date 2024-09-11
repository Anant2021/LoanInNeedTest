package com.bank.fintech.service;

import com.bank.fintech.Model.ClientLoanDetail;
import com.bank.fintech.repository.ClientLoanDetailRepo;
import com.bank.fintech.service.ClientLoanDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientLoanDetailServiceImp implements ClientLoanDetailService {
    private final ClientLoanDetailRepo repository;

    public ClientLoanDetailServiceImp(ClientLoanDetailRepo repository) {
        this.repository = repository;
    }

//    @Override
//    public ClientLoanDetail saveClientDetail(ClientLoanDetail ClientLoanDetail) {
//        return null;
//    }
//
//    @Override
//    public ClientLoanDetail getClientLoanDetail(int Id) {
//        return null;
//    }
//
//    @Override
//    public ClientLoanDetail getClientLoanDetailByloanNo(String loanNo) {
//        return null;
//    }
//
//    @Override
//    public ClientLoanDetail updateClientDetail(String loanNo, ClientLoanDetail clientLoanDetail) {
//        return null;
//    }

    @Override
    public ClientLoanDetail saveClientDetail(ClientLoanDetail ClientLoanDetail) {
        return repository.save(ClientLoanDetail);
    }
//@Override
//public ResponseEntity<Map<String, Object>> saveClientDetails(ClientLoanDetail clientLoanDetail) {
//    ClientLoanDetail savedDetail = repository.save(clientLoanDetail);
//    // Create a response map to include the message and the saved entity
//    Map<String, Object> response = new HashMap<>();
//    response.put("message", "Saved successfully");
//    response.put("data", savedDetail);
//
//
//    return ResponseEntity.status(HttpStatus.CREATED).body(response); // Return the saved entity
//}
@Override
public ResponseEntity<Map<String, String>> saveClientDetails(ClientLoanDetail clientLoanDetail) {
    repository.save(clientLoanDetail);

    // Create a response map to include the success message
    Map<String, String> response = new HashMap<>();
    response.put("message", "Saved successfully");

    return ResponseEntity.status(HttpStatus.CREATED).body(response); // Return response with status 201
}


    @Override
    public List<ClientLoanDetail> findByLoanNo(String loanNo) {
        Optional<ClientLoanDetail> loanDetail = repository.findByLoanNo(loanNo);
        if (loanDetail.isPresent()) {
            if ("accepted".equalsIgnoreCase(loanDetail.get().getStatus())) {
                return repository.findAll(); // Return all records if status is "accepted"
            }
            return List.of(loanDetail.get()); // Return the specific loan detail if status is not "accepted"
        }
        return Collections.emptyList(); // Return an empty list if loanNo not found
    }
//    public ResponseEntity<Map<String, Object>> findByLoanId(String loanNo) {
//        Optional<ClientLoanDetail> loanDetail = repository.findByLoanNo(loanNo);
//        Map<String, Object> response = new HashMap<>();
//
//        if (loanDetail.isPresent()) {
//            if ("accepted".equalsIgnoreCase(loanDetail.get().getStatus())) {
//                response.put("status", "accepted");
//                response.put("data", repository.findAll()); // Return all records if status is "accepted"
//                return ResponseEntity.ok(response);
//            }  else if ("rejected".equalsIgnoreCase(loanDetail.get().getStatus())) {
//                response.put("status", "rejected");
//                response.put("message", "Loan has been rejected");
//                return ResponseEntity.ok(response);
//            } else if ("pending".equalsIgnoreCase(loanDetail.get().getStatus())) {
//                response.put("status", "pending");
//                response.put("message", "Loan is pending");
//                return ResponseEntity.ok(response);
//            } else if ("underprocess".equalsIgnoreCase(loanDetail.get().getStatus())) {
//                response.put("status", "underprocess");
//                response.put("message", "Loan is under process");
//                return ResponseEntity.ok(response);
//            } else if ("closed".equalsIgnoreCase(loanDetail.get().getStatus())) {
//                response.put("status", "closed");
//                response.put("message", "No running loans");
//                return ResponseEntity.ok(response);
//            }
//        }
//        return ResponseEntity.notFound().build(); // Return 404 if loanNo not found
//    }
public ResponseEntity<Map<String, Object>> findByLoanId(String loanNo) {
    Optional<ClientLoanDetail> loanDetail = repository.findByLoanNo(loanNo);
    Map<String, Object> response = new HashMap<>();

    if (loanDetail.isPresent()) {
        ClientLoanDetail detail = loanDetail.get(); // Get the loan detail object
        response.put("loanNo", detail.getLoanNo()); // Include the loan number in the response
        response.put("firstName", detail.getFirstName());
        response.put("lastName", detail.getLastName());
        response.put("panNo", detail.getPanNo());
        response.put("mobileNo", detail.getMobileNo());
        response.put("status", detail.getStatus());
        response.put("statusDate", detail.getStatusDate());
        response.put("branchName", detail.getBranchName());
        response.put("loanDisbursed", detail.getLoanDisbursed());
        response.put("roiPerDay", detail.getRoiPerDay());
        response.put("noOfDays", detail.getNoOfDays());
        response.put("realDays", detail.getRealDays());
        response.put("penaltyDays", detail.getPenaltyDays());
        response.put("penaltyInterest", detail.getPenaltyInterest());
        response.put("penaltyCharge", detail.getPenaltyCharge());
        response.put("paidAmount", detail.getPaidAmount());
        response.put("repaymentAmount", detail.getRepaymentAmount());
        response.put("repaymentDate", detail.getRepaymentDate());
        response.put("disbursementDate", detail.getDisbursementDate());
        response.put("adminAndGstRate", detail.getAdminAndGstRate());
        response.put("dueDateRepaymentAmount", detail.getDueDaterepaymentAmount());
        response.put("realInterest", detail.getRealInterest());

        // Add a message based on the status
        switch (detail.getStatus().toLowerCase()) {
            case "accepted":
                response.put("message", "Loan is accepted");
                break;
            case "rejected":
                response.put("message", "Loan has been rejected");
                break;
            case "pending":
                response.put("message", "Loan is pending");
                break;
            case "underprocess":
                response.put("message", "Loan is under process");
                break;
            case "closed":
                response.put("message", "No running loans");
                break;
            default:
                response.put("message", "Unknown loan status");
                break;
        }

        return ResponseEntity.ok(response); // Return the specific loan details
    }

    return ResponseEntity.notFound().build(); // Return 404 if loanNo not found
}

//
//    @Override
//    public ClientLoanDetail save(ClientLoanDetail clientLoanDetail) {
//        return repository.save(clientLoanDetail);
//    }
}