package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.OrderDAO;
import com.gmail.liliyayalovchenko.Domains.Order;
import com.gmail.liliyayalovchenko.Domains.ProductInCart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteOrder(int id) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.load(Order.class, id);
        session.delete(order);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Order getOrder(int id) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.load(Order.class, id);
        return order;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateOrderAmount(int id, ProductInCart productInCart, boolean b) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.load(Order.class, id);
        int newAmount = b ? order.getTotalAmount() + productInCart.getQuantity()*productInCart.getPrice()
                          : order.getTotalAmount() - productInCart.getQuantity()*productInCart.getPrice();

        order.setTotalAmount(newAmount);
        session.update(order);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveOrder(int id, Date date, int totalAmount,  String delivery, String comments) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.load(Order.class, id);
        order.setDate(date);
        order.setTotalAmount(totalAmount);
        order.setDelivery(delivery);
        order.setComments(comments);
        session.update(order);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.save(order);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Order> getOrders() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM Order a");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Order> getAllOrdersByClient(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery
                ("SELECT o FROM Order o where o.client.id =:var")
                .setParameter("var", id);
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Order> getSortedByDateUp() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT o FROM Order o order by o.date desc ");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Order> getSortedByDateDown() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT o FROM Order o order by o.date asc");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Order> getSortedByAmountDown() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT o FROM Order o order by o.totalAmount desc");
        return query.list();
    }
}
