package com.bank.fintech.service;

import com.bank.fintech.Model.Client;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface ClientService {


    public Client saveClient(Client client);
    public String checkClientStatus(String panNo);
    public List<Client> getAllClients();
    public Client getClient(int id);
    public Client getClientByPan(String PanNo);
    public void deleteClient(int id);

    public Client updateClientStatus(int id, String status);

    public Map<String, Object> updateClientByStatus(int id , String status);
    public List<Client> getClientsByStatusAndRequestDate(String status, LocalDate fromDate, LocalDate toDate);

    public  List<Client> getAllClientsByRequestDate(LocalDate fromDate, LocalDate toDate);
}

