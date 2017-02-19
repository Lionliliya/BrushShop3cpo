package com.gmail.liliyayalovchenko.Services;

import com.gmail.liliyayalovchenko.DAO.FeedBackDAO;
import com.gmail.liliyayalovchenko.Domains.FeedBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class FeedBackService {

    @Autowired
    private FeedBackDAO feedBackDAO;

    @Transactional
    public void saveFeedBack(FeedBack feedBack) {
        feedBackDAO.saveFeedBack(feedBack);
    }

    @Transactional
    public List<FeedBack> getAllFeedBacks() {
        return feedBackDAO.getAllFeedBacks();
    }

    @Transactional
    public List<FeedBack> getFeedBacksByClientId(int clientId) {
        return feedBackDAO.getFeedBacksByClientId(clientId);
    }

    @Transactional
    public List<FeedBack> getFeedBacksByProductId(int productId) {
        return feedBackDAO.getFeedBacksByProductId(productId);
    }

    @Transactional
    public FeedBack getFeedBackById(int id) {
        return feedBackDAO.getFeedBackById(id);
    }

    @Transactional
    public void delete(FeedBack feedBack) {
        feedBackDAO.delete(feedBack);
    }

    @Transactional
    public void saveFeedBack(int id, Date data, int evaluation, String feedback) {
        feedBackDAO.saveFeedBack(id, data, evaluation, feedback);
    }

    @Transactional
    public List<FeedBack> getAllFeedBacksDateUp() {
        return feedBackDAO.getAllFeedBacksDateUp();
    }

    @Transactional
    public List<FeedBack> getAllFeedBacksDateDown() {
        return feedBackDAO.getAllFeedBacksDateDown();
    }

    @Transactional
    public List<FeedBack> getAllFeedBacksRateDown() {
        return feedBackDAO.getAllFeedBacksRateDown();
    }

    @Transactional
    public List<FeedBack> getAllFeedBacksRateUp() {
        return feedBackDAO.getAllFeedBacksRateUp();
    }

    @Transactional
    public void remove(FeedBack feedBackById) {
        feedBackDAO.remove(feedBackById);
    }
}
