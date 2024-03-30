package com.example.wholesalesystem.helper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void getName() {
        Product product = new Product();
        assertNull(product.getName());
    }

    @Test
    void setName() {
        Product product = new Product();
        product.setName("Test Name");
        assertEquals("Test Name", product.getName());
    }

    @Test
    void getBrand() {
        Product product = new Product();
        assertNull(product.getBrand());
    }

    @Test
    void setBrand() {
        Product product = new Product();
        product.setBrand("Test Brand");
        assertEquals("Test Brand", product.getBrand());
    }

    @Test
    void getUid() {
        Product product = new Product();
        assertNull(product.getUid());
    }

    @Test
    void setUid() {
        Product product = new Product();
        product.setUid("Test UID");
        assertEquals("Test UID", product.getUid());
    }

    @Test
    void getDescription() {
        Product product = new Product();
        assertNull(product.getDescription());
    }

    @Test
    void setDescription() {
        Product product = new Product();
        product.setDescription("Test Description");
        assertEquals("Test Description", product.getDescription());
    }

    @Test
    void getDate() {
        Product product = new Product();
        assertNull(product.getDate());
    }

    @Test
    void setDate() {
        Product product = new Product();
        product.setDate("Test Date");
        assertEquals("Test Date", product.getDate());
    }

    @Test
    void getPurprice() {
        Product product = new Product();
        assertEquals(0, product.getPurprice());
    }

    @Test
    void setPurprice() {
        Product product = new Product();
        product.setPurprice(10.0);
        assertEquals(10.0, product.getPurprice());
    }

    @Test
    void getSaleprice() {
        Product product = new Product();
        assertEquals(0, product.getSaleprice());
    }

    @Test
    void setSaleprice() {
        Product product = new Product();
        product.setSaleprice(20.0);
        assertEquals(20.0, product.getSaleprice());
    }

    @Test
    void getQuantity() {
        Product product = new Product();
        assertEquals(0, product.getQuantity());
    }

    @Test
    void setQuantity() {
        Product product = new Product();
        product.setQuantity(30.0);
        assertEquals(30.0, product.getQuantity());
    }

    @Test
    void getCategoryid() {
        Product product = new Product();
        assertEquals(0, product.getCategoryid());
    }

    @Test
    void setCategoryid() {
        Product product = new Product();
        product.setCategoryid(123);
        assertEquals(123, product.getCategoryid());
    }
}