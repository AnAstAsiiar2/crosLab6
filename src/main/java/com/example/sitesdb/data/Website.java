package com.example.sitesdb.data;

import java.sql.Date;

public class Website {
    private int id;
    private String name;
    private String url;
    private String category;
    private String description;
    private int pages;
    private int visitors;
    private int yearFounded;

    // Конструктор
    public Website(int id, String name, String url, String category, String description, int pages, int visitors, int yearFounded) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.category = category;
        this.description = description;
        this.pages = pages;
        this.visitors = visitors;
        this.yearFounded = yearFounded;
    }

    // Геттери та сеттери
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getVisitors() {
        return visitors;
    }

    public void setVisitors(int visitors) {
        this.visitors = visitors;
    }

    public int getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(int yearFounded) {
        this.yearFounded = yearFounded;
    }

    @Override
    public String toString() {
        return "Website{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", pages=" + pages +
                ", visitors=" + visitors +
                ", yearFounded=" + yearFounded +
                '}';
    }
}
