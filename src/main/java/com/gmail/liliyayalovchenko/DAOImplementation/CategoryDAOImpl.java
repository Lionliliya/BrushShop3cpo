package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.CategoryDAO;
import com.gmail.liliyayalovchenko.Domains.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.query.Query;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Category getCategoryByName(String  categoryName) {
        Session session = sessionFactory.getCurrentSession();
        Category category = (Category) session.createQuery
                ("SELECT a FROM Category a WHERE a.name =:pattern")
                .setParameter("pattern", categoryName)
                .uniqueResult();
        return  category;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Category getCategoryById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.load(Category.class, id);
        return category;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveCategory(Category category) {
        sessionFactory.getCurrentSession().save(category);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveCategory(int id, String name, String info, String metaDescription, String metaKeyWords, String metaTitle) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.load(Category.class, id);
        category.setName(name);
        category.setInfo(info);
        category.setMetaDescription(metaDescription);
        category.setMetaKeyWords(metaKeyWords);
        category.setMetaTitle(metaTitle);
        session.update(category);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Category> getAllCategories() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT c FROM Category c");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.load(Category.class, id);
        session.delete(category);
    }
}
