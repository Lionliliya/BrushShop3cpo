package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.FeedBackDAO;
import com.gmail.liliyayalovchenko.Domains.FeedBack;
import com.gmail.liliyayalovchenko.Domains.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class FeedBackDAOImpl implements FeedBackDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveFeedBack(FeedBack feedBack) {
        Session session = sessionFactory.getCurrentSession();
        session.save(feedBack);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(FeedBack feedBack) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(feedBack);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<FeedBack> getAllFeedBacks() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM FeedBack a");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<FeedBack> getFeedBacksByClientId(int clientId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery
                ("SELECT a FROM FeedBack a WHERE a.client.id =:pattern")
                .setParameter("pattern", clientId);

        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<FeedBack> getFeedBacksByProductId(int productId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery
                ("SELECT a FROM FeedBack a WHERE a.product.id =:pattern")
                .setParameter("pattern", productId);
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public FeedBack getFeedBackById(int id) {
        Session session = sessionFactory.getCurrentSession();
        FeedBack feedBack = (FeedBack) session.createQuery
                ("SELECT a FROM FeedBack a WHERE a.id =:pattern")
                .setParameter("pattern", id)
                .uniqueResult();
        return feedBack;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveFeedBack(int id, Date date, int evaluation, String feedback) {
        Session session = sessionFactory.getCurrentSession();
        FeedBack feedBack = session.load(FeedBack.class, id);
        feedBack.setDate(date);
        feedBack.setEvaluation(evaluation);
        feedBack.setFeedback(feedback);
        session.update(feedBack);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<FeedBack> getAllFeedBacksDateUp() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM FeedBack a order by a.date desc");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<FeedBack> getAllFeedBacksDateDown() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM FeedBack a order by a.date");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<FeedBack> getAllFeedBacksRateDown() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM FeedBack a order by a.evaluation desc");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<FeedBack> getAllFeedBacksRateUp() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM FeedBack a order by a.evaluation");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void remove(FeedBack feedBackById) {
        Session session = sessionFactory.getCurrentSession();
        Product product = feedBackById.getProduct();
        product.removeFeedBack(feedBackById);
        session.delete(feedBackById);
    }
}
