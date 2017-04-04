package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.AdministratorDAO;
import com.gmail.liliyayalovchenko.Domains.Administrator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AdministratorDAOImpl implements AdministratorDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void removeAdmin(int id) {
        Session session = sessionFactory.getCurrentSession();
        Administrator administrator = session.load(Administrator.class, id);
        session.delete(administrator);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public String getAdminPassword(String role) {
        Session session = sessionFactory.getCurrentSession();
        Administrator administrator = (Administrator) session.createQuery
                ("SELECT a FROM Administrator a WHERE a.role =:var")
                .setParameter("var", role)
                .uniqueResult();
        return administrator.getPassword();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public String getAdminUsername (String role) {
        Session session = sessionFactory.getCurrentSession();
        Administrator administrator = (Administrator) session.createQuery
                ("SELECT a FROM Administrator a WHERE a.role =:var")
                .setParameter("var", role)
                .uniqueResult();
        return administrator.getUsername();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Administrator getAdminByRole(String role) {
        Session session = sessionFactory.getCurrentSession();
        Administrator administrator = (Administrator) session.createQuery
                ("SELECT a FROM Administrator a WHERE a.role =:var")
                .setParameter("var", role)
                .uniqueResult();
        return administrator;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Administrator getAdminById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Administrator administrator = session.load(Administrator.class, id);
        return administrator;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Administrator> getAllUsers() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("SELECT a FROM Administrator a");
        return query.list();
    }


    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveAdmin(Administrator administrator) {
        sessionFactory.getCurrentSession().save(administrator);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveAdmin(int id, String role, String password, String username, String domainName, String emailAddress,
                          String phoneNumber1, String phoneNumber2) {

        Session session = sessionFactory.getCurrentSession();
        Administrator administrator = session.load(Administrator.class, id);
        administrator.setRole(role);
        administrator.setPassword(password);
        administrator.setUsername(username);
        administrator.setDomainName(domainName);
        administrator.setEmailAddress(emailAddress);
        administrator.setPhoneNumber1(phoneNumber1);
        administrator.setPhoneNumber2(phoneNumber2);
        session.update(administrator);
    }

}
