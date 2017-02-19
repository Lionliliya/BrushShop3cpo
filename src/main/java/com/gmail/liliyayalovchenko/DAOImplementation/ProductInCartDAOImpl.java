package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.ProductInCartDAO;
import com.gmail.liliyayalovchenko.Domains.ProductInCart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ProductInCartDAOImpl implements ProductInCartDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(ProductInCart product) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(product);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<ProductInCart> getProductsInCart() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM ProductInCart a");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveProductInCart(List<ProductInCart> productInCartList) {
        Session session = sessionFactory.getCurrentSession();
        productInCartList.forEach(session::save);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveProductInCart(ProductInCart productInCart) {
        Session session = sessionFactory.getCurrentSession();
        session.save(productInCart);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public ProductInCart getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        ProductInCart productInCart = session.load(ProductInCart.class, id);
        return productInCart;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<ProductInCart> getProductsInCart(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery
                ("select p from ProductInCart p where p.order.id = :var")
                .setParameter("var", id);
        return query.list();
    }
}
