package com.gmail.liliyayalovchenko.Domains;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Info")
@Proxy(lazy = false)
public class Post implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column
    private int id;
    @Column(nullable = false)
    private String title;

    private String imagePath;
    @Column(nullable = false)
    private String shortDescription;
    @Column(name="datePubl", nullable = false)
    private Date dateOfPublication;
    @Column(nullable = false)
    private String buttonText;
    @Column(nullable = false)
    private String content;
    private String metaTitle;
    @Column(name="KeyWord")
    private String metaKeyWords;
    @Column(name = "MetaDesc")
    private String metaDescription;

    public Post(String title, String imagePath, String shortDescription, Date dateOfPublication,
                String buttonText, String content, String metaDescription, String metaKeyWords, String metaTitle) {
        this.title = title;
        this.imagePath = imagePath;
        this.shortDescription = shortDescription;
        this.dateOfPublication = dateOfPublication;
        this.buttonText = buttonText;
        this.content = content;
        this.metaDescription = metaDescription;
        this.metaKeyWords = metaKeyWords;
        this.metaTitle = metaTitle;
    }

    public Post() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Date dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMetaKeyWords() {
        return metaKeyWords;
    }

    public void setMetaKeyWords(String metaKeyWords) {
        this.metaKeyWords = metaKeyWords;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", dateOfPublication=" + dateOfPublication +
                ", buttonText='" + buttonText + '\'' +
                ", content='" + content + '\'' +
                ", metaTitle='" + metaTitle + '\'' +
                ", metaKeyWords='" + metaKeyWords + '\'' +
                ", metaDescription='" + metaDescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;

        Post post = (Post) o;

        if (buttonText != null ? !buttonText.equals(post.buttonText) : post.buttonText != null) return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;
        if (dateOfPublication != null ? !dateOfPublication.equals(post.dateOfPublication) : post.dateOfPublication != null)
            return false;
        if (imagePath != null ? !imagePath.equals(post.imagePath) : post.imagePath != null) return false;
        if (metaDescription != null ? !metaDescription.equals(post.metaDescription) : post.metaDescription != null)
            return false;
        if (metaKeyWords != null ? !metaKeyWords.equals(post.metaKeyWords) : post.metaKeyWords != null) return false;
        if (metaTitle != null ? !metaTitle.equals(post.metaTitle) : post.metaTitle != null) return false;
        if (shortDescription != null ? !shortDescription.equals(post.shortDescription) : post.shortDescription != null)
            return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (dateOfPublication != null ? dateOfPublication.hashCode() : 0);
        result = 31 * result + (buttonText != null ? buttonText.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (metaTitle != null ? metaTitle.hashCode() : 0);
        result = 31 * result + (metaKeyWords != null ? metaKeyWords.hashCode() : 0);
        result = 31 * result + (metaDescription != null ? metaDescription.hashCode() : 0);
        return result;
    }
}
