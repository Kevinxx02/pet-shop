package com.petshop.catalog.domain.blog;

import java.util.UUID;

public class Blog {
    private UUID id;
    private String title;
    private String date;
    private String url;
    private boolean isVisible;

    private Blog(
            UUID id,
            String title,
            String date,
            String url,
            boolean isVisible
    ) {
        this.setId(id);
        this.setTitle(title);
        this.setDate(date);
        this.setUrl(url);
        this.setVisible(isVisible);
    }

    public static Blog create(String title, String date, String url) {
        final UUID id = UUID.randomUUID();
        final boolean isVisible = true;
        return new Blog(id, title, date, url, isVisible);
    }

    public static Blog rehydrate(
            UUID id,
            String title,
            String date,
            String url,
            boolean isVisible
    ) {
        return new Blog(id, title, date, url, isVisible);
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

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public boolean getVisible() {
        return this.isVisible;
    }
}