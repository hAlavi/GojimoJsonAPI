package com.gojimo.gojimojsonapi.model;

import android.graphics.Color;

import java.io.Serializable;

/**
 * Subject domain class
 * @author RouhAlavi
 * @version 0.1.0
 */
public class Subject implements Serializable{
    private String id;
    private String title;
    private int colour;

    public Subject(String id, String title, String colour) {
        this.id = id;
        this.title = title;
        setColour(colour);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title){
        this.title= title;
    }
    public String getTitle(){
        return title;
    }
    public void setColour(String colour){
        try{
            this.colour= Color.parseColor(colour);
        }
        catch(Exception ex){
            this.colour= Color.parseColor("black");
        }
    }

    public int getColour(){
        return colour;
    }

    public String toString(){
        return "<strong>"+/*String.format("#%06X", (0xFFFFFF & colour))+"\"'>" +*/title/*.substring(0,3).toUpperCase()*/+"</strong>";
    }
}
