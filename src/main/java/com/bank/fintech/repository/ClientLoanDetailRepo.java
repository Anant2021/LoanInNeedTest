package com.bank.fintech.repository;

import com.bank.fintech.Model.Client;
import com.bank.fintech.Model.ClientLoanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository public interface ClientLoanDetailRepo extends JpaRepository<ClientLoanDetail, Integer> {
    Optional<ClientLoanDetail> findByLoanNo(String loanNo);
    List<ClientLoanDetail> findAll(); // To fetch all records
}
