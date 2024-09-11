package com.bank.fintech.service;



import com.bank.fintech.Model.ClientLoanDetail;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ClientLoanDetailService {

    public ClientLoanDetail saveClientDetail(ClientLoanDetail ClientLoanDetail);
//    ResponseEntity<ClientLoanDetail> saveClientDetails(ClientLoanDetail clientLoanDetail);
//ResponseEntity<Map<String, Object>> saveClientDetails(ClientLoanDetail clientLoanDetail);
ResponseEntity<Map<String, String>> saveClientDetails(ClientLoanDetail clientLoanDetail);
//    public ClientLoanDetail getClientLoanDetail(int Id);
//    public ClientLoanDetail getClientLoanDetailByloanNo(String loanNo);
//    public ClientLoanDetail updateClientDetail(String loanNo, ClientLoanDetail clientLoanDetail);

    ResponseEntity<Map<String, Object>> findByLoanId(String loanNo);
    List<ClientLoanDetail> findByLoanNo(String loanNo);
}
