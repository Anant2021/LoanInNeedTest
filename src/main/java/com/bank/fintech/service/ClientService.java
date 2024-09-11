package com.bank.fintech.service;

import com.bank.fintech.Model.Client;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ClientService {
    public Client saveClient(Client client);
    public List<Client> getAllClients();
    public Client getClient(int id);
    public Client getClientByPan(String pan);
    public void deleteClient(int id);
//    public  Client updateClient(int id , Client client);
    public Client updateClientStatus(int id, String status);
//    public String updateClientByStatus(int id , String status);
    public Map<String, Object> updateClientByStatus(int id , String status);
    public List<Client> getClientsByStatusAndRequestDate(String status, LocalDate fromDate, LocalDate toDate);
}

