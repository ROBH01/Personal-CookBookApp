package com.example.cookbook;


public class Recipee {
    private String name;
    private int numOfViews;
    private String imageLoc;

    public Recipee() {
    }

    public Recipee(String name, int numOfViews, String imageLoc) {
        this.name = name;
        this.numOfViews = numOfViews;
        this.imageLoc = imageLoc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfViews() {
        return numOfViews;
    }

    public void setNumOfViews(int numOfViews) {
        this.numOfViews = numOfViews;
    }

    public String getImageLoc() {
        return imageLoc;
    }

    public void setImageLoc(String imageLoc) {
        this.imageLoc = imageLoc;
    }
}