package com.bank.fintech.repository;

import com.bank.fintech.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    Client findByPanNo(String panNo);

    Client findByPanNoAndStatus(String panNo, String status);

    @Query("SELECT c FROM Client c WHERE c.status = ?1 AND c.requestDate BETWEEN ?2 AND ?3")
    List<Client> findByStatusAndRequestDate(String status, LocalDate fromDate, LocalDate toDate);
    @Query("SELECT c FROM Client c WHERE c.requestDate BETWEEN ?1 AND ?2")
    List<Client> getAllClientsByRequestDate(LocalDate fromDate, LocalDate toDate);

}
