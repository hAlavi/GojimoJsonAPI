package com.gojimo.gojimojsonapi.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
* Qualification domain class
* @author RouhAlavi
* @version 0.1.0
*/
public class Qualification implements Serializable {
	private String title;
    private String id;
    private Date createdAt;
    private Date updatedAt;
    private ArrayList<Subject> subjects;
    private ArrayList<Product> defaultProducts;

	public Qualification() {
	}

    public Qualification(String title, String createdAt, String  updatedAt,
                         ArrayList<Subject> subjects, ArrayList<Product> products) {
		this.title = title;
        this.subjects = subjects;
        this.defaultProducts = products;
        //
        SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            this.createdAt = format.parse(createdAt);
            this.updatedAt = format.parse(updatedAt);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            this.createdAt = new Date();
            //
            this.updatedAt = new Date();
            //
            e.printStackTrace();
        }


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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public ArrayList<Product> getDefaultProducts() {
        return defaultProducts;
    }

    public void setDefaultProducts(ArrayList<Product> defaultProducts) {
        this.defaultProducts = defaultProducts;
    }

    public String getSubjectsFormatted(){
        String s="Contains ";
        if (subjects.size()<=0)
            s="No subject included";
        else
            for (Subject sb: subjects){
                s+=sb.toString()+", ";
            }

        return s;
    }
}
