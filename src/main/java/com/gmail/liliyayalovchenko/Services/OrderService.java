package com.gmail.liliyayalovchenko.Services;

import com.gmail.liliyayalovchenko.DAO.OrderDAO;
import com.gmail.liliyayalovchenko.Domains.Order;
import com.gmail.liliyayalovchenko.Domains.ProductInCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Transactional
    public Order getOrder(int id) {
        return orderDAO.getOrder(id);
    }

    @Transactional
    public void saveOrder(int id, Date date, int totalAmount, String delivery, String comments) {
        orderDAO.saveOrder(id, date, totalAmount, delivery, comments);
    }

    @Transactional
    public void deleteOrder(int id) {
        orderDAO.deleteOrder(id);
    }

    @Transactional
    public void saveOrder(Order order) {
        orderDAO.saveOrder(order);
    }

    @Transactional
    public List<Order> getOrders() {
        return orderDAO.getOrders();
    }

    @Transactional
    public void updateOrderAmount(int id, ProductInCart productInCart, boolean b) {
        orderDAO.updateOrderAmount(id, productInCart, b);
    }

    @Transactional
    public List<Order> getSortedByDateUp() {
        return orderDAO.getSortedByDateUp();
    }

    @Transactional
    public List<Order> getSortedByDateDown() {
        return orderDAO.getSortedByDateDown();
    }

    @Transactional
    public List<Order> getSortedByAmountDown() {
         return orderDAO.getSortedByAmountDown();
    }

    @Transactional
    public List<Order> getAllOrdersByClient(int id) {
        return orderDAO.getAllOrdersByClient(id);
    }

}
