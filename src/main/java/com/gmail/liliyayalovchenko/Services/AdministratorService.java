package com.gmail.liliyayalovchenko.Services;

import com.gmail.liliyayalovchenko.DAO.AdministratorDAO;
import com.gmail.liliyayalovchenko.Domains.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AdministratorService {

    @Autowired
    private AdministratorDAO administratorDAO;

    @Transactional
    public String getAdminPassword(String role) {
        return administratorDAO.getAdminPassword(role);
    }

    @Transactional
    public String getAdminUsername (String role) {
        return administratorDAO.getAdminUsername(role);
    }

    @Transactional
    public Administrator getAdminByRole(String role) {
        return administratorDAO.getAdminByRole(role);
    }

    @Transactional
    public Administrator getAdminById(int id) {
        return administratorDAO.getAdminById(id);
    }

    @Transactional
    public List<Administrator> getAllUsers() {
        return administratorDAO.getAllUsers();
    }

    @Transactional
    public void saveAdmin(Administrator administrator) {
        administratorDAO.saveAdmin(administrator);
    }

    @Transactional
    public void saveAdmin(int id, String role, String password,
                          String username, String domainName, String emailAddress,
                          String phoneNumber1, String phoneNumber2) {
        administratorDAO.saveAdmin(id, role, password, username,
                domainName, emailAddress, phoneNumber1, phoneNumber2);
    }

    @Transactional
    public void removeAdmin(int id) {
        administratorDAO.removeAdmin(id);
    }
}
