package com.bank.fintech.controller;


import com.bank.fintech.Model.ClientLoanDetail;
import com.bank.fintech.repository.ClientLoanDetailRepo;
import com.bank.fintech.service.ClientLoanDetailService;
import com.bank.fintech.service.ClientLoanDetailServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/clientLoanDetails")
@CrossOrigin(origins = "*") // Allow CORS for all origins
public class ClientDetailController {
    private final ClientLoanDetailService service;

    public ClientDetailController(ClientLoanDetailService service) {
        this.service = service;
    }
        @GetMapping("/hc") public String HealthCheck(){return "hi inside client Detail Controller";}

    @GetMapping("/")
    public List<ClientLoanDetail> findByLoanNo(@PathVariable String loanNo) {
        return service.findByLoanNo(loanNo);
    }

    @GetMapping("/{loanNo}")
    public ResponseEntity<Map<String, Object>> findByLoanId(@PathVariable String loanNo) {
        return service.findByLoanId(loanNo); // Call the service to check status and return response
    }

//    @PostMapping
//    public ClientLoanDetail save(@RequestBody ClientLoanDetail clientLoanDetail) {
//        return service.saveClientDetail(clientLoanDetail);
//    }

//    @PostMapping
//    public ResponseEntity<ClientLoanDetail> saveClientDetails(@RequestBody ClientLoanDetail clientLoanDetail) {
//        return service.saveClientDetails(clientLoanDetail); // Call the service to save and return response
//    }
//@PostMapping
//public ResponseEntity<Map<String, Object>> saveClientDetail(@RequestBody ClientLoanDetail clientLoanDetail) {
//    return service.saveClientDetails(clientLoanDetail);}
@PostMapping
public ResponseEntity<Map<String, String>> saveClientDetail(@RequestBody ClientLoanDetail clientLoanDetail) {
    return service.saveClientDetails(clientLoanDetail); // Call the service to save and return response
}



}