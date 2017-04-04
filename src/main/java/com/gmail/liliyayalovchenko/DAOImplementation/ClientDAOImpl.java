package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.ClientDAO;
import com.gmail.liliyayalovchenko.Domains.Client;
import com.gmail.liliyayalovchenko.Domains.FeedBack;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Client> getClients() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT c FROM Client c");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Client> getSortedByName() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT c FROM Client c order by c.firstName");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Client> getSortedByEmail() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT c FROM Client c order by c.email");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Client getClient(int id) {
        Session session = sessionFactory.getCurrentSession();
        Client client = session.load(Client.class, id);
        return client;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Client findClientByPhone(String phone, String email) {
        Session session = sessionFactory.getCurrentSession();
        Client client = (Client) session.createQuery
                ("SELECT a FROM Client a WHERE  a.phoneNumber =:var2 and a.email =:var1")
                .setParameter("var2", phone)
                .setParameter("var1", email).uniqueResult();

        return client;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Client findClientByPhone(String phone) {
        Session session = sessionFactory.getCurrentSession();
        Client client = (Client) session.createQuery
                ("SELECT a FROM Client a WHERE  a.phoneNumber =:var2")
                .setParameter("var2", phone)
                .list().get(0);

        return client;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Client findClientByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Client client = (Client) session.createQuery
                ("SELECT a FROM Client a WHERE  a.email =:pattern")
                .setParameter("pattern", email)
                .uniqueResult();
        return client;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveClient(int id, String name, String phone, String email) {
        Session session = sessionFactory.getCurrentSession();
        Client client = session.load(Client.class, id);
        client.setFirstName(name);
        client.setPhoneNumber(phone);
        client.setEmail(email);
        session.update(client);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addClient(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.save(client);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void remove(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(client);
    }

}
