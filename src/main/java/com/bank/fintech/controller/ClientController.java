package com.bank.fintech.controller;

import com.bank.fintech.Model.Client;
import com.bank.fintech.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/client")
public class ClientController {

        @GetMapping("/healthCheck")
        public String health(){
            return "healthCheck";
        }

    @Autowired
    private ClientService clientService;
//
//    @PostMapping("/") public String addClient(@RequestBody Client client) {
//        clientService.saveClient(client);
//        return "New client added";
//
//    }

//    @PostMapping("/save")
//    public ResponseEntity<Client> createClient(@RequestBody Client client) {
//        return new ResponseEntity<Client>(clientService.saveClient(client), HttpStatus.CREATED);
//    }
@PostMapping("/save")
public ResponseEntity<Map<String, Object>> createClient(@RequestBody Client client) {
        Map<String, Object> response = new HashMap<>();
        try {
            Client savedClient = clientService.saveClient(client);
            response.put("code", 1);
            response.put("message", "Client created successfully");
            response.put("client", savedClient);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            response.put("code", -1);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
        public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<List<Client>>(clientService.getAllClients(),HttpStatus.OK);
       }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id) {
        return new ResponseEntity<Client>(clientService.getClient(id),HttpStatus.OK);

    }
//find Client By PanNo
    @GetMapping("/findbypan/{panNo}")
    public ResponseEntity<Client> getClientByPanNo(@PathVariable String panNo) {
        Client client = clientService.getClientByPan(panNo);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

@DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
        return new ResponseEntity<String>("Deleted Sucessfully",HttpStatus.OK);
}

//            @PostMapping("/update/{id}")
//    public ResponseEntity<Client> updateClient(@PathVariable int id , @RequestBody Client client) {
//        return new ResponseEntity<Client>(clientService.updateClient(id,client),HttpStatus.OK);
//    }
@PostMapping("/update")
        public ResponseEntity<Client> updateClientStatus(@RequestBody Client client) {
    Client updatedClient = clientService.updateClientStatus(client.getId(), client.getStatus());
    return new ResponseEntity<>(HttpStatus.OK);
}

//   @PostMapping("/u/updateByStatus") public String updateClientByStatus(@RequestBody Client client) {
//       return clientService.updateClientByStatus(client.getId(), client.getStatus());
//    }

    @PostMapping("/updateByStatus")
    public ResponseEntity<Map<String, Object>> updateClientByStatus(@RequestBody Client client) {
        Map<String, Object> response = clientService.updateClientByStatus(client.getId(), client.getStatus());
        return ResponseEntity.ok(response);
    }




    @GetMapping("/byStatusAndDate/{status}/{fromDate}/{toDate}")
    public ResponseEntity<List<Client>> getClientsByStatusAndDate(
            @PathVariable String status,
            @PathVariable String fromDate,
            @PathVariable String toDate) {

        // Convert String to LocalDate
        LocalDate startDate = LocalDate.parse(fromDate);
        LocalDate endDate = LocalDate.parse(toDate);

        // Fetch clients from the service
        List<Client> clients = clientService.getClientsByStatusAndRequestDate(status, startDate, endDate);

        // Return the response
        return ResponseEntity.ok(clients);
    }

}
