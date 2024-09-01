package org.example;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LibraryTest {
    private static final Map<String, Map<String, String>> LibraryBook = new HashMap<>();

    Library library;
    BookManager bm;
    BookRepo br;
    @BeforeEach
    void setUp() {
        bm  = new BookManagement();
        br = new viewBooks();
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

    @Test
    @Order(3)
    @DisplayName("User tries to borrow book")
    public void borrowBookTest() {
        assertTrue(bm.borrowBook("Five Point Someone", LibraryBook));
    }

    @DisplayName("Borrow Book From CSV")
    @ParameterizedTest
    @Order(4)
    @CsvFileSource(resources = "/Borrow.csv")
    public void borrowBookFromCsv(String title) {
        assertTrue(bm.borrowBook(title, LibraryBook));
    }

    @Test
    @Order(5)
    @DisplayName("User tries to borrow a book not in the library")
    public void borrowNonExistentBook() {
        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            bm.borrowBook("Some Non-Existent Book", LibraryBook);
        });

        assertEquals("Book with the given title not found.", exception.getMessage());
    }

    @Test
    @Order(6)
    @DisplayName("Get All Available Books")
    public void getAllAvailableBooks() {
        br.viewAvailableBooks(LibraryBook);
    }

    @Test
    @Order(7)
    @DisplayName("Display All Borrowed Books")
    public void getBorrowedBooks() {
        br.viewBorrowedBooks(LibraryBook);
    }
    @AfterEach
    void tearDown() {
        bm = null;
    }
}
