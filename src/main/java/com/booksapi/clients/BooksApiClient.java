package com.booksapi.clients;

import com.booksapi.models.Book;
import com.booksapi.specs.BooksApiSpec;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class BooksApiClient {

    public Response getAllBooks() {
        return given()
                .spec(BooksApiSpec.getRequestSpec())
                .when()
                .get("/books");
    }

    public Response getBookById(int id) {
        return given()
                .spec(BooksApiSpec.getRequestSpec())
                .when()
                .get("/books/{id}", id);
    }

    public Response createBook(Book book) {
        return given()
                .spec(BooksApiSpec.getRequestSpec())
                .body(book)
                .when()
                .post("/books");
    }

    public Response updateBook(int id, Book book) {
        return given()
                .spec(BooksApiSpec.getRequestSpec())
                .body(book)
                .when()
                .put("/books/{id}", id);
    }

    public Response deleteBook(int id) {
        return given()
                .spec(BooksApiSpec.getRequestSpec())
                .when()
                .delete("/books/{id}", id);
    }

    public Response checkApiAvailability() {
        return given()
                .spec(BooksApiSpec.getRequestSpec())
                .when()
                .head("/books");
    }
}