package com.gmail.liliyayalovchenko.Domains;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Feedbacks")
@Proxy(lazy = false)
public class FeedBack implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "product_id")
    private Product product;

    private Date date;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne()
    @JoinColumn(name = "client_id")
    private Client client;

    private int evaluation;
    private String feedback;


    public FeedBack() {}

    public FeedBack(Product product, Date date, Client client, int evaluation, String feedback) {
        this.product = product;
        this.date = date;
        this.client = client;
        this.evaluation = evaluation;
        this.feedback = feedback;
    }

    public String getStarRate() {
        if (this.evaluation == 5) {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>";
        } else if (this.evaluation == 4) {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>";
        } else if (this.evaluation == 3) {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>";
        } else if (this.evaluation == 2) {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>";
        } else {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "FeedBack{" +
                "id=" + id +
                ", product=" + product +
                ", date=" + date +
                ", client=" + client +
                ", evaluation=" + evaluation +
                ", feedback='" + feedback + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeedBack)) return false;

        FeedBack feedBack = (FeedBack) o;

        if (evaluation != feedBack.evaluation) return false;
        if (client != null ? !client.equals(feedBack.client) : feedBack.client != null) return false;
        if (date != null ? !date.equals(feedBack.date) : feedBack.date != null) return false;
        if (feedback != null ? !feedback.equals(feedBack.feedback) : feedBack.feedback != null) return false;
        if (product != null ? !product.equals(feedBack.product) : feedBack.product != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + evaluation;
        result = 31 * result + (feedback != null ? feedback.hashCode() : 0);
        return result;
    }
}
