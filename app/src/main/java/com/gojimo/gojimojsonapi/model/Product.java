package com.gojimo.gojimojsonapi.model;

import java.io.Serializable;

/**
 * Product domain class
 * @author RouhAlavi
 * @version 0.1.0
 */
public class Product implements Serializable{
    private String title;
    private String id;
    private String type;

    public Product(String id, String title, String type) {
        this.title = title;
        this.id = id;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
