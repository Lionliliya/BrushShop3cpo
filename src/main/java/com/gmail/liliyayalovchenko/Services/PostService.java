package com.gmail.liliyayalovchenko.Services;

import com.gmail.liliyayalovchenko.DAO.PostDAO;
import com.gmail.liliyayalovchenko.Domains.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class PostService {

    @Autowired
    private PostDAO postDAO;

    @Transactional
    public List<Post> getAllPosts() {
        return postDAO.getAllPosts();
    }

    @Transactional
    public Post get(int id) {
        return postDAO.get(id);
    }

    @Transactional
    public Post get(String title) {
        return postDAO.get(title);
    }

    @Transactional
    public List<Post> getAllPostAsc() {
        return postDAO.getAllPostAsc();
    }

    @Transactional
    public List<Post> getAllPostsDesc() {
        return postDAO.getAllPostsDesc();
    }

    @Transactional
    public void save(Post article) {
        postDAO.save(article);
    }

    @Transactional
    public void remove(int id) {
        postDAO.remove(id);
    }

    @Transactional
    public List<Post> getTwoLatest() {
        return postDAO.getTwoLatest();
    }

    @Transactional
    public void save(int id, String title, String imagePath,
                     String shortDescription, Date dateOfPublication,
              String buttonText, String content, String metaDescription,
                     String metaKeyWords, String metaTitle) {
        postDAO.save(id, title, imagePath, shortDescription, dateOfPublication,
                buttonText, content, metaDescription, metaKeyWords, metaTitle);
    }

    @Transactional
    public List<Post> getAllPostsNameUp() {
        return postDAO.getAllPostsNameUp();
    }

    @Transactional
    public List<Post> getAllPostsNameDown() {
        return postDAO.getAllPostsNameDown();
    }

    @Transactional
    public List<Post> getNextPosts(Integer starRow) {
        return postDAO.getNextPosts(starRow);
    }
}
