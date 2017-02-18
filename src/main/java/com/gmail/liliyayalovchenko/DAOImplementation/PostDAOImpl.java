package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.PostDAO;
import com.gmail.liliyayalovchenko.Domains.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class PostDAOImpl implements PostDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Post> getAllPosts() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM Post a");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Post get(int id) {
        Session session = sessionFactory.getCurrentSession();
        Post post = session.load(Post.class, id);
        return post;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Post get(String title) {
        Session session = sessionFactory.getCurrentSession();
        Post post = (Post) session.createQuery
                ("SELECT a FROM Post a WHERE a.id =:var")
                .setParameter("var", title)
                .uniqueResult();
        return post;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Post> getAllPostAsc() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM Post a ORDER BY a.dateOfPublication");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Post> getAllPostsDesc() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM Post a ORDER BY a.dateOfPublication DESC ");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Post> getAllPostsNameUp() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM Post a ORDER BY a.title");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Post> getAllPostsNameDown() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM Post a ORDER BY a.title DESC ");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Post> getNextPosts(Integer startRow) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM Post a order by a.dateOfPublication desc");
        if (startRow == 1) {
            return query.setFirstResult(0).setMaxResults(3).list();
        }
        return query.setFirstResult(startRow - 1).setMaxResults(3).list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void save(Post article) {
        Session session = sessionFactory.getCurrentSession();
        session.save(article);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        Post post = (Post) session.createQuery
                ("SELECT a FROM Post a  WHERE a.id =:var")
                .setParameter("var", id)
                .uniqueResult();
        session.delete(post);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void save(int id, String title, String imagePath, String shortDescription, Date dateOfPublication,
                     String buttonText, String content, String metaDescription, String metaKeyWords, String metaTitle) {

        Session session = sessionFactory.getCurrentSession();
        Post post = session.load(Post.class, id);
        post.setTitle(title);
        post.setImagePath(imagePath);
        post.setShortDescription(shortDescription);
        post.setDateOfPublication(dateOfPublication);
        post.setButtonText(buttonText);
        post.setContent(content);
        post.setMetaDescription(metaDescription);
        post.setMetaKeyWords(metaKeyWords);
        post.setMetaTitle(metaTitle);
        session.update(post);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Post> getTwoLatest() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT i from Post i order by i.id DESC");
        query.setMaxResults(2);
        return query.list();
    }
}
