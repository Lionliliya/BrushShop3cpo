package com.gmail.liliyayalovchenko.Services;

import com.gmail.liliyayalovchenko.DAO.ClientDAO;
import com.gmail.liliyayalovchenko.Domains.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Transactional
    public List<Client> getClients() {
        return clientDAO.getClients();
    }

    @Transactional
    public List<Client> getSortedByName() {
        return clientDAO.getSortedByName();
    }

    @Transactional
    public List<Client> getSortedByEmail() {
        return clientDAO.getSortedByEmail();
    }

    @Transactional
    public Client getClient(int id) {
        return clientDAO.getClient(id);
    }

    @Transactional
    public Client findClientByPhone(String phone, String email) {
        return clientDAO.findClientByPhone(phone, email);
    }

    @Transactional
    public Client findClientByEmail(String email) {
        return clientDAO.findClientByEmail(email);
    }

    @Transactional
    public void saveClient(int id, String name, String phone, String email) {
        clientDAO.saveClient(id, name, phone, email);
    }

    @Transactional
    public void addClient(Client client) {
        clientDAO.addClient(client);
    }

    @Transactional
    public void remove(Client client) {
        clientDAO.remove(client);
    }

}
