package org.example;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibraryTest {
    private static final Map<String, Map<String, String>> LibraryBook = new HashMap<>();

    Library library;
    BookManager bm;

    @BeforeEach
    void setUp() {
        bm  = new BookManagement();
    }

    @BeforeAll
    static void setUpBeforeClass() {
        System.out.println("Setting up library test");
    }

    @Test
    @Order(1)
    @DisplayName("Add first Book")
    public void addFirstBook() {
        library = new Library("Five Point Someone", "Chetan Bhagat", "2004");
        assertTrue(bm.addBookDetails(library, LibraryBook));
    }

    @DisplayName("Add Books from CSV")
    @ParameterizedTest
    @Order(2)
    @CsvFileSource(resources = "/Book.csv")
    public void addBooksFromCsv(String title, String author, String publishyear) {
        library = new Library(title, author, publishyear);
        assertTrue(bm.addBookDetails(library, LibraryBook));
    }

    @AfterEach
    void tearDown() {
        bm = null;
    }
}
