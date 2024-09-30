package com.booksapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer id;
    private String name;
    private String author;
    private String publication;
    private String category;
    private Integer pages;
    private Double price;
}