package com.ghulam.model;

import com.ghulam.controller.ServletController;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "book_name")
    private String bookName;
    private String author;
    private int quantity;
    private Blob image;

    @Transient
    private String base64Image;

    public Book() {
    }

    public Book(String bookName,String author, int quantity) {
        this.bookName = bookName;
        this.author = author;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getBase64Image() {
        return ServletController.getBase64Image(image, base64Image);
    }
}