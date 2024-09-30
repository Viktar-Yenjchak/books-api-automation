Feature: Books API CRUD Operations

  Background:
    Given the Books API is available

  Scenario: Create a new book
    Given I have a new book
    When I create the book
    Then the book is created successfully
    And the book can be retrieved by ID

  Scenario: Read all books
    When I read all books
    Then I receive a list of books

  Scenario: Read a book by ID
    Given a book exists with ID
    When I read the book by ID
    Then I receive the correct book details

  Scenario: Update a book
    Given a book exists with ID
    And I have updated details for the book
    When I update the book
    Then the book is updated successfully
    And the updated book can be retrieved by ID

  Scenario: Delete a book
    Given a book exists with ID
    When I delete the book
    Then the book is deleted successfully
    And the book cannot be retrieved by ID