package com.example.wholesalesystem;

import java.sql.Timestamp;

public class Product {
    String name, brand, uid, description,date;
    double purprice, saleprice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public Product(String name, String brand, String uid, String description, String date, double purprice, double saleprice, int quantity, int categoryid) {
        this.name = name;
        this.brand = brand;
        this.uid = uid;
        this.description = description;
        this.date = date;
        this.purprice = purprice;
        this.saleprice = saleprice;
        this.quantity = quantity;
        this.categoryid = categoryid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public double getPurprice() {
        return purprice;
    }

    public void setPurprice(double purprice) {
        this.purprice = purprice;
    }

    public double getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(double saleprice) {
        this.saleprice = saleprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    int quantity, categoryid;

}
