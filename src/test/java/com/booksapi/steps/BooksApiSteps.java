package com.booksapi.steps;

import com.booksapi.clients.BooksApiClient;
import com.booksapi.models.Book;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class BooksApiSteps {

    private final BooksApiClient booksApiClient = new BooksApiClient();
    private Response response;
    private Book testBook;
    private int testBookId;

    @Given("^the Books API is available$")
    public void theBooksApiIsAvailable() {
        response = booksApiClient.checkApiAvailability();
        assertEquals(200, response.getStatusCode());
    }

    @Given("^I have a new book$")
    public void iHaveANewBook() {
        String name = "Book " + UUID.randomUUID();
        String author = "Author " + UUID.randomUUID();
        String publication = "Publisher " + (new Random().nextInt(100) + 1);
        String category = getRandomCategory();
        int pages = new Random().nextInt(1000) + 50;
        double price = (new Random().nextDouble() * 100) + 5;
        price = Math.round(price * 100.0) / 100.0;
        testBook = new Book(null, name, author, publication, category, pages, price);
    }

    private String getRandomCategory() {
        String[] categories = {"Programming", "Science", "History", "Fiction", "Non-fiction", "Biography", "Technology"};
        int index = new Random().nextInt(categories.length);
        return categories[index];
    }

    @When("^I create the book$")
    public void iCreateTheBook() {
        response = booksApiClient.createBook(testBook);
        assertEquals(200, response.getStatusCode());
        Book createdBook = response.as(Book.class);
        testBookId = createdBook.getId();
        testBook.setId(testBookId);
    }

    @Then("^the book is created successfully$")
    public void theBookIsCreatedSuccessfully() {
        assertNotNull(testBookId, "The book ID should not be null.");
        Book createdBook = response.as(Book.class);
        testBook.setId(createdBook.getId());
        assertEquals(testBook.getId(), createdBook.getId(), "The ID should match.");
        assertEquals(testBook.getName(), createdBook.getName(), "The name should match.");
        assertEquals(testBook.getAuthor(), createdBook.getAuthor(), "The author should match.");
        assertEquals(testBook.getPublication(), createdBook.getPublication(), "The publication should match.");
        assertEquals(testBook.getCategory(), createdBook.getCategory(), "The category should match.");
        assertEquals(testBook.getPages(), createdBook.getPages(), "The pages should match.");
        // Use a delta for floating-point comparison to handle precision issues
        assertEquals(testBook.getPrice(), createdBook.getPrice(), 0.001, "The price should match within tolerance.");
    }

    @And("^the book can be retrieved by ID$")
    public void theBookCanBeRetrievedByID() {
        response = booksApiClient.getBookById(testBookId);
        assertEquals(200, response.getStatusCode());
        Book retrievedBook = response.as(Book.class);
        assertEquals(testBook, retrievedBook);
    }

    @When("^I read all books$")
    public void iReadAllBooks() {
        response = booksApiClient.getAllBooks();
        assertEquals(200, response.getStatusCode());
    }

    @Then("^I receive a list of books$")
    public void iReceiveAListOfBooks() {
        Book[] books = response.as(Book[].class);
        assertTrue(books.length > 0);
    }

    @Given("^a book exists with ID$")
    public void aBookExistsWithID() {
        iHaveANewBook();
        iCreateTheBook();
    }

    @When("^I read the book by ID$")
    public void iReadTheBookByID() {
        response = booksApiClient.getBookById(testBookId);
    }

    @Then("^I receive the correct book details$")
    public void iReceiveTheCorrectBookDetails() {
        assertEquals(200, response.getStatusCode());
        Book retrievedBook = response.as(Book.class);
        assertEquals(testBook, retrievedBook);
    }

    @Given("^I have updated details for the book$")
    public void iHaveUpdatedDetailsForTheBook() {
        double price = (new Random().nextDouble() * 100) + 5;
        price = Math.round(price * 100.0) / 100.0;
        testBook.setPrice(price);
    }

    @When("^I update the book$")
    public void iUpdateTheBook() {
        response = booksApiClient.updateBook(testBookId, testBook);
        assertEquals(200, response.getStatusCode());
    }

    @Then("^the book is updated successfully$")
    public void theBookIsUpdatedSuccessfully() {
        Book updatedBook = response.as(Book.class);
        assertEquals(testBook.getPrice(), updatedBook.getPrice(), "The price should be updated.");
        assertEquals(testBook.getId(), updatedBook.getId(), "The ID should remain the same.");
        assertEquals(testBook.getName(), updatedBook.getName(), "The name should remain the same.");
        assertEquals(testBook.getAuthor(), updatedBook.getAuthor(), "The author should remain the same.");
        assertEquals(testBook.getPublication(), updatedBook.getPublication(), "The publication should remain the same.");
        assertEquals(testBook.getCategory(), updatedBook.getCategory(), "The category should remain the same.");
        assertEquals(testBook.getPages(), updatedBook.getPages(), "The pages should remain the same.");
    }

    @And("^the updated book can be retrieved by ID$")
    public void theUpdatedBookCanBeRetrievedByID() {
        response = booksApiClient.getBookById(testBookId);
        Book updatedBook = response.as(Book.class);
        assertEquals(testBook, updatedBook);
    }

    @When("^I delete the book$")
    public void iDeleteTheBook() {
        response = booksApiClient.deleteBook(testBookId);
    }

    @Then("^the book is deleted successfully$")
    public void theBookIsDeletedSuccessfully() {
        assertEquals(200, response.getStatusCode());
        Book deletedBook = response.as(Book.class);
        assertEquals(testBook.getId(), deletedBook.getId(), "The ID should match.");
        assertEquals(testBook.getName(), deletedBook.getName(), "The name should match.");
        assertEquals(testBook.getAuthor(), deletedBook.getAuthor(), "The author should match.");
        assertEquals(testBook.getPublication(), deletedBook.getPublication(), "The publication should match.");
        assertEquals(testBook.getCategory(), deletedBook.getCategory(), "The category should match.");
        assertEquals(testBook.getPages(), deletedBook.getPages(), "The pages should match.");
        assertEquals(testBook.getPrice(), deletedBook.getPrice(), 0.001, "The price should match.");
    }

    @And("^the book cannot be retrieved by ID$")
    public void theBookCannotBeRetrievedByID() {
        response = booksApiClient.getBookById(testBookId);
        assertEquals(404, response.getStatusCode());
    }
}