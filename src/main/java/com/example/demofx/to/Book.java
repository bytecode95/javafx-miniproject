package com.example.demofx.to;

public class Book {
    private String id;
    private String name;
    private String isbn;
    private int qty;
    private double price;

    public Book() {
    }

    public Book(String id, String name, String isbn, int qty, double price) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.qty = qty;
        this.price = price;
    }

    public Book(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
