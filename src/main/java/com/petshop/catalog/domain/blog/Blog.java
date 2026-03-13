package com.petshop.catalog.domain.blog;

import java.util.UUID;

public class Blog {
    private UUID id;
    private String title;
    private String date;
    private String url;

    private Blog(UUID id, String title, String date, String url) {
        this.setId(id);
        this.setTitle(title);
        this.setDate(date);
        this.setUrl(url);
    }

    public static Blog create(String title, String date, String url) {
        final UUID id = UUID.randomUUID();
        return new Blog(id, title, date, url);
    }

    public static Blog rehydrate(UUID id, String title, String date, String url) {
        return new Blog(id, title, date, url);
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

    private void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    private void setUrl(String url) {
        this.url = url;
    }
}