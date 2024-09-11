package com.bank.fintech.service;

import com.bank.fintech.Model.Client;
import com.bank.fintech.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class ClientServiceImp implements ClientService{

    @Autowired
    private ClientRepository clientRepo;
    @Override
    public Client saveClient(Client client) {
        return clientRepo.save(client);
    }

    @Override public List<Client> getAllClients() {
        return clientRepo.findAll();

    }
    @Override
    public Client getClient(int id) {
        return clientRepo.findById(id).get();
    }
    @Override
    public Client getClientByPan(String PanNo)
    {
        return clientRepo.findByPanNo(PanNo);
    }

    //find Client By Pan and status
    public String getClientStatus(String panNo) {
        Client client = clientRepo.findByPanNo(panNo);
        if (client == null) {
            return "Client with PAN number " + panNo + " does not exist";
        } else {
            String status = client.getStatus();
            if (status.equalsIgnoreCase("rejected")) {
                return "Client with PAN number " + panNo + " is already rejected. Apply after 6 months";
            } else {
                return "Client with PAN number " + panNo + " exists";
            }
        }
    }

    @Override
    public List<Client> getClientsByStatusAndRequestDate(String status, LocalDate fromDate, LocalDate toDate) {
        // Ensure fromDate is before toDate for accurate filtering
        if (fromDate.isAfter(toDate)) {
            throw new IllegalArgumentException("fromDate must be before toDate");
        }

        return clientRepo.findByStatusAndRequestDate(status,fromDate,toDate);
    }


    public void deleteClient(int id) {
      Client client= clientRepo.findById(id).get();
        if(client !=null)
    {
        clientRepo.delete(client);
    }
    }
//


//    @Override
//    public Client updateClient(int id, String status) {
//        Client client = clientRepo.findById(id)
//                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + id));
//        client.setStatus(status);
//        return clientRepo.save(client);
//    }
//    @Override
//    public Client updateClient(int id, Client client) {
//        Client oldClient= clientRepo.findById(id).get();
//        if(oldClient!=null){
//            client.setId(id);
//        return clientRepo.save(client);
//        }
//        return null;
//    }
@Override
public Client updateClientStatus(int id, String status) {
    Client client = clientRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Client with id " + id + " not found"));
    client.setStatus(status);
    return clientRepo.save(client);
}
//    public String updateClientByStatus(int id, String status) {
//        Client client = clientRepo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Client with id " + id + " not found"));
//        client.setStatus(status);
//        clientRepo.save(client); // Save the updated client
//        return "Successfully updated Client with id " + id + " and status " + status;
//    }
@Override  public Map<String, Object> updateClientByStatus(int id, String status) {

    Client client = clientRepo.findById(id)
            .orElse(null); // Get client or null if not found

    if (client == null) {
        // Return error response
        Map<String, Object> response = new HashMap<>();
        response.put("code", -1);
        response.put("message", "Client with id " + id + " not found");
        return response;
    }

    client.setStatus(status);
    clientRepo.save(client); // Save the updated client

    // Return success response
    Map<String, Object> response = new HashMap<>();
    response.put("code", 1);
    response.put("message", "Successfully updated Client with id " + id + " and status " + status);
    return response;
}
}
