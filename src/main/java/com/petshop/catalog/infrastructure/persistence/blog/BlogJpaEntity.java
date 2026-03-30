package com.petshop.catalog.infrastructure.persistence.blog;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "blog")
public class BlogJpaEntity {

    @Id
    private UUID id;
    private String title;
    private String date;
    private String url;
    private boolean isVisible;
    public BlogJpaEntity() {

    }

    public BlogJpaEntity(UUID id, String title, String date, String url, boolean isVisible) {
        this.setId(id);
        this.setTitle(title);
        this.setDate(date);
        this.setUrl(url);
        this.setVisible(isVisible);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getVisible() {
        return this.isVisible;
    }
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}