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
    public Client saveClient(Client client ) {

        Client existingPanClient = clientRepo.findByPanNo(client.getpanNo());

        // If an existing client is found, check their status
//        if (existingPanClient != null) {
//            String status = existingPanClient.getStatus();
//            if ("pending".equalsIgnoreCase(status)) {
//                throw new RuntimeException("Application not saved, as status is pending.");
//            } else if ("InProcess".equalsIgnoreCase(status)) {
//                throw new RuntimeException("Application already in process.");
//            } else if ("Completed".equalsIgnoreCase(status)) {
//                throw new RuntimeException("Case with these credentials is already closed.");
//            } else if ("rejected".equalsIgnoreCase(status)) {
//                LocalDate sixMonthsLater = existingPanClient.getRequestDate().plusMonths(2);
//                if (LocalDate.now().isBefore(sixMonthsLater)) {
//                    throw new RuntimeException("Client with PAN number " + client.getpanNo() +
//                            " was rejected less than 2 months ago. You can apply after " + sixMonthsLater);
//                }
//            }
//        }

//
//
//        // If no issues, save the new client entry
//        return clientRepo.save(client);
        Client existingAadharClient = clientRepo.findByAadharNo(client.getAadharNo());
        if (existingPanClient != null || existingAadharClient != null) {
            StringBuilder errorMessage = new StringBuilder();

            if (existingPanClient != null) {
                String panStatus = existingPanClient.getStatus();
                if ("pending".equalsIgnoreCase(panStatus)) {
                    errorMessage.append("Application not saved for PAN, as status is pending. ");
                } else if ("InProcess".equalsIgnoreCase(panStatus)) {
                    errorMessage.append("Application already in process for PAN. ");
                } else if ("Completed".equalsIgnoreCase(panStatus)) {
                    errorMessage.append("Case with these credentials is already closed for PAN. ");
                } else if ("rejected".equalsIgnoreCase(panStatus)) {
                    LocalDate sixMonthsLater = existingPanClient.getRequestDate().plusMonths(2);
                    if (LocalDate.now().isBefore(sixMonthsLater)) {
                        errorMessage.append("Client with PAN number " + client.getpanNo()+
                                " was rejected less than 2 months ago. You can apply after " + sixMonthsLater + ". ");
                    }
                }
            }

            if (existingAadharClient != null) {
                String aadharStatus = existingAadharClient.getStatus();
                if ("pending".equalsIgnoreCase(aadharStatus)) {
                    errorMessage.append("Application not saved for Aadhaar, as status is pending. ");
                } else if ("InProcess".equalsIgnoreCase(aadharStatus)) {
                    errorMessage.append("Application already in process for Aadhaar. ");
                } else if ("Completed".equalsIgnoreCase(aadharStatus)) {
                    errorMessage.append("Case with these credentials is already closed for Aadhaar. ");
                } else if ("rejected".equalsIgnoreCase(aadharStatus)) {
                    LocalDate sixMonthsLater = existingAadharClient.getRequestDate().plusMonths(2);
                    if (LocalDate.now().isBefore(sixMonthsLater)) {
                        errorMessage.append("Client with Aadhaar number " + client.getAadharNo() +
                                " was rejected less than 2 months ago. You can apply after " + sixMonthsLater + ". ");
                    }
                }
            }

            // Throw an exception with all collected error messages
            throw new RuntimeException(errorMessage.toString().trim());
        }

        // If no issues, save the new client entry
        return clientRepo.save(client);
    }



   @Override public String checkClientStatus(String panNo) {

        Client existingClients = clientRepo.findByPanNo(panNo);
        if (existingClients != null) {
            return existingClients.getStatus();
        }
        return null; // No existing client found
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


@Override
public  List<Client> getAllClientsByRequestDate(LocalDate fromDate, LocalDate toDate){
    if (fromDate.isAfter(toDate)) {
        throw new IllegalArgumentException("fromDate must be before toDate");
    }

    return clientRepo.getAllClientsByRequestDate(fromDate,toDate);
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
